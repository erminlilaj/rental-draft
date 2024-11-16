package it.linksmt.rental.exception;

import it.linksmt.rental.enums.ErrorCode;
import lombok.Getter;

@Getter
public class AuthenticationException extends RentalApiException {
    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AuthenticationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
