package farmsystem.backend.global.error.exception;

import farmsystem.backend.global.error.ErrorCode;

public class ForbiddenException extends BusinessException {
    public ForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }
    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}