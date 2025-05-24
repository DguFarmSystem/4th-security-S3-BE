package farmsystem.backend.domain.portfolio.controller;

import farmsystem.backend.domain.portfolio.dto.PortfolioResponse;
import farmsystem.backend.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Portfolio", description = "포트폴리오 API")
public interface PortfolioApi {

    @Operation(
            summary = "포트폴리오 조회",
            description = "내 포트폴리오를 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "포트폴리오 조회 성공",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = PortfolioResponse.class)))),
            @ApiResponse(responseCode = "404", description = "포트폴리오를 찾을 수 없음")
    })
    ResponseEntity<SuccessResponse<?>> getPortfolio(@PathVariable Long profileId);
}
