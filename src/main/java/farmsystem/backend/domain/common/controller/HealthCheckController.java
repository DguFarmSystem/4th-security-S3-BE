package farmsystem.backend.domain.common.controller;

import farmsystem.backend.global.common.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthCheckController implements HealthCheckApi {

    // 서버 상태 확인 API
    @GetMapping("/")
    public ResponseEntity<SuccessResponse<?>> backendServer() {
        return SuccessResponse.ok("Hello Server!");
    }
}
