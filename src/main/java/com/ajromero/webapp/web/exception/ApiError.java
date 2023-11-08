package com.ajromero.webapp.web.exception;



import java.util.HashMap;
import java.util.Map;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class ApiError {
    private HttpStatus status;
    private String message;
    private Map<String, String> errors;

    public ApiError(final HttpStatus status, final String message,
                    final Map<String, String> errors) {
        this(status,message);
        this.errors = errors;
    }

    public ApiError(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
        errors = new HashMap<>();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Map<String, String> getErrors() {
        return new HashMap<>(errors);
    }

    public void setErrors(final Map<String, String> errors) {
        this.errors = errors;
    }
}
