package farmsystem.backend.global.error.exception;

import farmsystem.backend.global.error.ErrorCode;

public class InternalServerException extends BusinessException {
    public InternalServerException(ErrorCode errorCode) {
        super(errorCode);
    }
}