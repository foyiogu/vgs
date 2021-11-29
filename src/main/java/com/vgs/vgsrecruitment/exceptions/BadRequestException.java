package com.vgs.vgsrecruitment.exceptions;


public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
