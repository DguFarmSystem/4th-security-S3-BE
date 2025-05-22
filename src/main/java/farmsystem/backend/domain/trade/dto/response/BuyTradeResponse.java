package farmsystem.backend.domain.trade.dto.response;

import farmsystem.backend.domain.trade.entity.Trade;
import lombok.Builder;

@Builder
public record BuyTradeResponse(
        String stockCode,
        String stockName,
        int holdingAmount,
        int averagePrice
) {
    public static BuyTradeResponse of(Trade trade, int holdingAmount, int averagePrice) {
        return BuyTradeResponse.builder()
                .stockCode(trade.getStock().getCode())
                .stockName(trade.getStock().getName())
                .holdingAmount(holdingAmount)
                .averagePrice(averagePrice)
                .build();
    }
}
