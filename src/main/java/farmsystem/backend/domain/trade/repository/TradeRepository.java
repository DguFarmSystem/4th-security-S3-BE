package farmsystem.backend.domain.trade.repository;

import farmsystem.backend.domain.profile.entity.Profile;
import farmsystem.backend.domain.stock.entity.Stock;
import farmsystem.backend.domain.trade.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    @Query("SELECT COALESCE(SUM(CASE WHEN t.type = 'BUY' THEN t.amount ELSE -t.amount END), 0) " +
            "FROM Trade t " +
            "WHERE t.profile = :profile AND t.stock = :stock")
    int sumAmountByProfileAndStock(@Param("profile") Profile profile, @Param("stock") Stock stock);
}
