package farmsystem.backend.domain.portfolio.dto;

public record HoldingDto(
        String stockCode,
        String stockName,
        int quantity,
        long buyPrice,
        int buyAmount
) {
}