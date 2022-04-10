package com.emredulger.exception;

public class EntityInheritanceViolationException extends RuntimeException {

    public EntityInheritanceViolationException(String message, String className, String extendableClass) {
        super(message + '\n' + className + " class must extend the " + extendableClass);
    }
}
