package farmsystem.backend.global.error.exception;

import farmsystem.backend.global.error.ErrorCode;

public class IllegalArgumentException extends BusinessException {
    public IllegalArgumentException() {
        super(ErrorCode.BAD_REQUEST);
    }
    public IllegalArgumentException(ErrorCode errorCode) {super(errorCode);}
}
