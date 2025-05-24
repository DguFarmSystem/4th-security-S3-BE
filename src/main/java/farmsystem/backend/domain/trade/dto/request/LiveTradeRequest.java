package farmsystem.backend.domain.trade.dto.request;

import farmsystem.backend.domain.profile.entity.Profile;
import farmsystem.backend.domain.stock.entity.Stock;
import farmsystem.backend.domain.trade.entity.Trade;
import farmsystem.backend.domain.trade.entity.TradeType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LiveTradeRequest(
        @NotNull
        String stockCode,
        @NotNull @Min(1)
        int price,
        @NotNull @Min(1)
        int amount,
        @NotNull
        TradeType type,
        @NotNull
        String password
) {
    public Trade toEntity(Profile profile, Stock stock) {
        return Trade.builder()
                .profile(profile)
                .stock(stock)
                .price(price)
                .amount(amount)
                .type(type)
                .build();
    }
}
