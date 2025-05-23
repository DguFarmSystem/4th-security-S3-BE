package farmsystem.backend.global.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    // Common
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "C001", "잘못된 요청입니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C002", "ENUM 입력값이 올바르지 않습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "C003", "리소스 접근 권한이 없습니다."),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "C004", "액세스 토큰의 형식이 올바르지 않습니다. Bearer 타입을 확인해 주세요."),
    INVALID_ACCESS_TOKEN_VALUE(HttpStatus.UNAUTHORIZED, "C005", "액세스 토큰의 값이 올바르지 않습니다."),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "C006", "액세스 토큰이 만료되었습니다. 재발급 받아주세요."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "C007", "리프레시 토큰의 형식이 올바르지 않습니다."),
    INVALID_REFRESH_TOKEN_VALUE(HttpStatus.UNAUTHORIZED, "C008", "리프레시 토큰의 값이 올바르지 않습니다."),
    EXPIRED_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "C009", "리프레시 토큰이 만료되었습니다. 다시 로그인해 주세요."),
    NOT_MATCH_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "C010", "일치하지 않는 리프레시 토큰입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "C011", "리소스 접근 권한이 없습니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "C012", "해당 리소스를 찾을 수 없습니다."),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "C013", "엔티티를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C014", "잘못된 HTTP method 요청입니다."),
    CONFLICT(HttpStatus.CONFLICT, "C015", "이미 존재하는 리소스입니다."),
    FILE_SIZE_EXCEEDED(HttpStatus.PAYLOAD_TOO_LARGE, "C016", "이미지 최대 크기를 초과하였습니다."),
    INVALID_IMAGE_FORMAT(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "C017", "지원하지 않는 이미지 형식입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C018", "서버 내부 오류입니다."),
    FILE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "C019", "파일 업로드에 실패하였습니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "C020", "리프레시 토큰이 존재하지 않습니다."),

    // Auth
    JSON_PARSING_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "A001", "JSON 파싱에 실패하였습니다."),
    NO_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "A002", "저장된 리프레시 토큰이 없습니다."),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "A003", "아이디나 비밀번호가 잘못되었습니다."),

    // Member
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "M001", "해당 회원을 찾을 수 없습니다."),

    // Stock
    STOCK_NOT_FOUND(HttpStatus.NOT_FOUND, "S001", "해당 종목을 찾을 수 없습니다."),

    // Trade
    INSUFFICIENT_BALANCE(HttpStatus.BAD_REQUEST, "T001", "잔액이 부족합니다."),
    INSUFFICIENT_STOCK(HttpStatus.BAD_REQUEST, "T002", "보유 수량이 부족합니다."),
    INVALID_PROFILE_TYPE(HttpStatus.BAD_REQUEST, "T003", "LIVE 프로필입니다."),

    // Profile
    PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "P001", "해당 프로필을 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
