package com.jwtauth.ExceptionHandling;

//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.CONFLICT)
public class NoUserFoundException extends RuntimeException {
//    String message;

//    public NoUserFoundException(String message){
//        this.message = message;s
//    }

//    @Override
//    public String getMessage() {
//        return message;
//    }

//    public void setMessage(String message) {
//        this.message = message;
//    }

    public NoUserFoundException(String message) {
        super(message);
    }
}
