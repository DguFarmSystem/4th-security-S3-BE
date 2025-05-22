package farmsystem.backend.domain.trade.controller;

import farmsystem.backend.domain.trade.dto.request.VirtualTradeRequest;
import farmsystem.backend.domain.trade.dto.response.VirtualTradeResponse;
import farmsystem.backend.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface TradeApi {

    @Operation(
            summary = "시점 모드 거래",
            description = "거래를 진행합니다.  \n" +
                    "- profileId: 시점 접속 시 받은 id  \n" +
                    "- stockCode, amount, price: 구매하려는 종목 코드, 수량, 단가 \n" +
                    "- type: BUY / SELL"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "가상 거래 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VirtualTradeResponse.class))),
            @ApiResponse(responseCode = "404", description = "프로필/종목을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "잔액이 부족함"),

    })
    ResponseEntity<SuccessResponse<?>> creatVirtualTrade(@Valid @RequestBody VirtualTradeRequest request);

    @Operation(
            summary = "시점 모드 거래",
            description = "거래를 진행합니다.  \n" +
                    "- stockCode, amount, price: 구매하려는 종목 코드, 수량, 단가 \n" +
                    "- type: BUY / SELL"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "실시간 거래 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VirtualTradeResponse.class))),
            @ApiResponse(responseCode = "404", description = "프로필/종목을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "잔액이 부족함")
    })
    ResponseEntity<SuccessResponse<?>> creatLiveTrade(@Valid @RequestBody VirtualTradeRequest request);
}
