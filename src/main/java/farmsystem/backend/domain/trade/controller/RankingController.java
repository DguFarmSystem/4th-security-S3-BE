package farmsystem.backend.domain.trade.controller;

import farmsystem.backend.domain.trade.service.RankingService;
import farmsystem.backend.global.common.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ranking")
public class RankingController implements RankingApi {

    private final RankingService rankingService;

    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getAssetRanking() {
        return SuccessResponse.ok(rankingService.getAssetRanking());
    }
}
