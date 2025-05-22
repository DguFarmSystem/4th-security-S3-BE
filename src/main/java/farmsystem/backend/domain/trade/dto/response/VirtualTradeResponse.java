package farmsystem.backend.domain.trade.dto.response;

import farmsystem.backend.domain.trade.entity.Trade;
import farmsystem.backend.domain.trade.entity.TradeType;
import lombok.Builder;

@Builder
public record VirtualTradeResponse(
        Long tradeId,
        Long profileId,
        Long stockId,
        int price,
        int amount,
        TradeType type
) {
    public static VirtualTradeResponse from(Trade trade) {
        return VirtualTradeResponse.builder()
                .tradeId(trade.getId())
                .profileId(trade.getProfile().getId())
                .stockId(trade.getStock().getId())
                .price(trade.getPrice())
                .amount(trade.getAmount())
                .type(trade.getType())
                .build();
    }
}
