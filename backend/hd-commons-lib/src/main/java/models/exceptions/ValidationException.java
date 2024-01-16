package models.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class ValidationException extends StandardError{

    private List<FieldError> errors;

    @Getter
    @AllArgsConstructor
    private static class FieldError{
        private String fieldError;
        private String message;
    }

    public void addError(final String fieldError, final String message){
        this.errors.add(new FieldError(fieldError, message));
    }
}
