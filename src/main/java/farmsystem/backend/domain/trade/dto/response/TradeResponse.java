package farmsystem.backend.domain.trade.dto.response;

public interface TradeResponse {
    String stockCode();
    String stockName();
    int holdingAmount();
}
