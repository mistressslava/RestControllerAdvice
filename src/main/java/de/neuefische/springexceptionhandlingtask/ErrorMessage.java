package de.neuefische.springexceptionhandlingtask;

import java.time.Instant;
import java.util.NoSuchElementException;

public record ErrorMessage(Instant timestamp,
                           int status,
                           String error,
                           String message,
                           String path) {

}
