package de.neuefische.springexceptionhandlingtask;

public class BrandIsIllegalException extends RuntimeException {
    public BrandIsIllegalException(String message) {
        super(message);
    }
}
