package com.example.yallahride.Exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(Long id, Class<?>entity){
        super("The " + entity.getSimpleName().toLowerCase() + " with ID " + " does not exist in the database records");
    }
}
