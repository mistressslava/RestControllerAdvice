package de.neuefische.springexceptionhandlingtask;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @GetMapping("{species}")
    String getAnimalSpecies(@PathVariable String species) {
        if (!species.equals("dog")) {
            throw new SpeciesIsIllegalException("Only 'dog' is allowed");
        }
        return species;
    }

    @GetMapping
    String getAllAnimals() {
        throw new NoSuchElementException("No Animals found");
    }

    @ExceptionHandler(SpeciesIsIllegalException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String HandlerIllegalArgumentException(SpeciesIsIllegalException e, HttpServletRequest request) {
        ErrorMessage body = new ErrorMessage(
                Instant.now(),
                HttpStatus.NOT_ACCEPTABLE.value(),
                HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );
        return "Error: " + body.message() +
                "\n[Reason: " + body.error() +
                "\nStatuscode: " + body.status() +
                "\nTime: " + body.timestamp() +
                "\nPath: " + body.path() +
                "]";

    }

}
