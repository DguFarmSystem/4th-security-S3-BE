package farmsystem.backend.domain.trade.controller;

import farmsystem.backend.domain.trade.dto.request.LiveTradeRequest;
import farmsystem.backend.domain.trade.dto.request.VirtualTradeRequest;
import farmsystem.backend.domain.trade.service.TradeService;
import farmsystem.backend.global.common.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trades")
public class TradeController implements TradeApi {

    private final TradeService tradeService;

    @PostMapping("/virtual")
    public ResponseEntity<SuccessResponse<?>> creatVirtualTrade(@Valid @RequestBody VirtualTradeRequest request) {
        return SuccessResponse.created(tradeService.creatVirtualTrade(request));
    }

    @PostMapping("/live")
    public ResponseEntity<SuccessResponse<?>> creatLiveTrade(@RequestParam Long memberId,
                                                             @Valid @RequestBody LiveTradeRequest request) {
        return SuccessResponse.created(tradeService.creatLiveTrade(memberId, request));
    }
}
