package farmsystem.backend.domain.trade.controller;

import farmsystem.backend.domain.member.authorize.CustomUserDetails;
import farmsystem.backend.domain.trade.dto.request.LiveTradeRequest;
import farmsystem.backend.domain.trade.dto.request.VirtualTradeRequest;
import farmsystem.backend.domain.trade.service.TradeService;
import farmsystem.backend.global.common.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trades")
public class TradeController implements TradeApi {

    private final TradeService tradeService;

    @PostMapping("/virtual")
    public ResponseEntity<SuccessResponse<?>> createVirtualTrade(@Valid @RequestBody VirtualTradeRequest request) {
        return SuccessResponse.created(tradeService.createVirtualTrade(request));
    }

    @PostMapping("/live")
    public ResponseEntity<SuccessResponse<?>> createLiveTrade(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                             @Valid @RequestBody LiveTradeRequest request) {
        Long memberId = userDetails.getId();
        return SuccessResponse.created(tradeService.createLiveTrade(memberId, request));
    }
}
