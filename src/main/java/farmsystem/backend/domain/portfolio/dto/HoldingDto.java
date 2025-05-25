package farmsystem.backend.domain.portfolio.dto;

public record HoldingDto(
        String stockCode,
        String stockName,
        long quantity,
        long buyPrice,
        long buyAmount
) {
    public double getAveragePrice() {
        return buyAmount == 0 ? 0 : (double) buyPrice / buyAmount;
    }
}