package farmsystem.backend.domain.trade.dto.response;

import farmsystem.backend.domain.trade.entity.Trade;
import lombok.Builder;

@Builder
public record SellTradeResponse(
        String stockCode,
        String stockName,
        int holdingAmount
) implements TradeResponse {
    public static SellTradeResponse of(Trade trade, int holdingAmount) {
        return SellTradeResponse.builder()
                .stockCode(trade.getStock().getCode())
                .stockName(trade.getStock().getName())
                .holdingAmount(holdingAmount)
                .build();
    }
}
