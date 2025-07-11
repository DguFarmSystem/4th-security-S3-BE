package farmsystem.backend.domain.common.controller;

import farmsystem.backend.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "HealthCheck API", description = "서버 상태 체크 API")
public interface HealthCheckApi {

    @Operation(
            summary = "서버 상태 확인 API",
            description = "서버 실행 상태를 확인하는 API입니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "서버가 정상적으로 작동 중"),
            @ApiResponse(responseCode = "404", description = "서버를 찾을 수 없음")
    })
    ResponseEntity<SuccessResponse<?>> backendServer();
}
