package it.linksmt.rental.exception;

import it.linksmt.rental.enums.ErrorCode;
import lombok.Getter;

@Getter
public class RentalApiException extends RuntimeException {
    private final ErrorCode errorCode;

    public RentalApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public RentalApiException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}