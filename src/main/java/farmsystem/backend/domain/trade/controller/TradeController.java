package farmsystem.backend.domain.trade.controller;

import farmsystem.backend.domain.trade.dto.request.TradeRequest;
import farmsystem.backend.domain.trade.service.TradeService;
import farmsystem.backend.global.common.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trades")
public class TradeController implements TradeApi {

    private final TradeService tradeService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> creatTrade(@Valid @RequestBody TradeRequest request) {
        return SuccessResponse.created(tradeService.creatTrade(request));
    }
}
