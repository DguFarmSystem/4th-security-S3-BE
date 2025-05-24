package farmsystem.backend.domain.portfolio.dto;

import lombok.Builder;

@Builder
public record PortfolioResponse(
        String stockCode,
        String stockName,
        int quantity,
        double ratio,
        long buyPrice
) {
    public static PortfolioResponse from(HoldingDto holding, double ratio) {
        return PortfolioResponse.builder()
                .stockCode(holding.stockCode())
                .stockName(holding.stockName())
                .quantity(holding.quantity())
                .ratio(ratio)
                .buyPrice(holding.getAveragePrice())
                .build();
    }
}
