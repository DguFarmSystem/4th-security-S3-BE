package farmsystem.backend.domain.trade.dto.response;

import farmsystem.backend.domain.trade.entity.Trade;
import farmsystem.backend.domain.trade.entity.TradeType;
import lombok.Builder;

@Builder
public record LiveTradeResponse(
        Long tradeId,
        Long stockId,
        int price,
        int amount,
        TradeType type
) {
    public static LiveTradeResponse from(Trade trade) {
        return LiveTradeResponse.builder()
                .tradeId(trade.getId())
                .stockId(trade.getStock().getId())
                .price(trade.getPrice())
                .amount(trade.getAmount())
                .type(trade.getType())
                .build();
    }
}
