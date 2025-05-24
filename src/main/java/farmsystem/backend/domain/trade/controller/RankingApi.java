package farmsystem.backend.domain.trade.controller;

import farmsystem.backend.domain.trade.dto.response.SellTradeResponse;
import farmsystem.backend.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface RankingApi {

    @Operation(
            summary = "자산 랭킹",
            description = "자산 랭킹을 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "자산 랭킹 조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SellTradeResponse.class))),

    })
    ResponseEntity<SuccessResponse<?>> getAssetRanking();
}
