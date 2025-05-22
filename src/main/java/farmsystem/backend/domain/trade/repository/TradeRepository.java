package farmsystem.backend.domain.trade.repository;

import farmsystem.backend.domain.trade.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
