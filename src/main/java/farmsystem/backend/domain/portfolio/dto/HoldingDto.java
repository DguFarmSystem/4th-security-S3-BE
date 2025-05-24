package farmsystem.backend.domain.portfolio.dto;

public record HoldingDto(
        String stockCode,
        String stockName,
        int quantity,
        long buyPrice,
        int buyAmount
) {
    public double getAveragePrice() {
        return buyAmount == 0 ? 0 : (double) buyPrice / buyAmount;
    }
}