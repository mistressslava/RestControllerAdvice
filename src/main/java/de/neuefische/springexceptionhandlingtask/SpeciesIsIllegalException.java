package de.neuefische.springexceptionhandlingtask;

public class SpeciesIsIllegalException extends RuntimeException {
    public SpeciesIsIllegalException(String message) {
        super(message);
    }
}
