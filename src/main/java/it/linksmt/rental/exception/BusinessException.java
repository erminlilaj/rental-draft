package it.linksmt.rental.exception;

import it.linksmt.rental.enums.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RentalApiException {
    public BusinessException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}