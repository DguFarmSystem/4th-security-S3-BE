package farmsystem.backend.domain.portfolio.dto;

import lombok.Builder;

@Builder
public record PortfolioResponse(
        String stockCode,
        String stockName,
        long quantity,
        double ratio,
        double averagePrice
) {
    public static PortfolioResponse from(HoldingDto holding, double ratio) {
        return PortfolioResponse.builder()
                .stockCode(holding.stockCode())
                .stockName(holding.stockName())
                .quantity(holding.quantity())
                .ratio(ratio)
                .averagePrice(holding.getAveragePrice())
                .build();
    }
}
