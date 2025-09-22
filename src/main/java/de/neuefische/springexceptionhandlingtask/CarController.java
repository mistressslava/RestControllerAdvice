package de.neuefische.springexceptionhandlingtask;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @GetMapping("{brand}")
    public String getCarBrand(@PathVariable String brand) {
        if (!brand.equals("porsche")) {
            throw new BrandIsIllegalException("Only 'porsche' allowed");
        }
        return brand;
    }

    @GetMapping
    public List<String> getAllCars() {
        throw new NoSuchElementException("No Cars found");
    }

    @ExceptionHandler(BrandIsIllegalException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String HandlerIllegalArgumentException(BrandIsIllegalException e, HttpServletRequest request) {
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
