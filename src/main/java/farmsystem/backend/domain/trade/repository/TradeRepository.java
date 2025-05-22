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
    int sumAmountByProfileAndStock(@Param("profile") Profile profile, @Param("stock") Stock stock); // 해당 프로필에서 해당 종목 보유 수량

    @Query("SELECT COALESCE(SUM(CASE WHEN t.type = 'BUY' THEN t.price * t.amount ELSE 0 END), 0) " +
            "FROM Trade t " +
            "WHERE t.profile = :profile AND t.stock = :stock")
    int sumBuyPriceByProfileAndStock(@Param("profile") Profile profile, @Param("stock") Stock stock); // 해당 프로필이 해당 종목을 매수한 총 금액

    @Query("SELECT COALESCE(SUM(CASE WHEN t.type = 'BUY' THEN t.amount ELSE 0 END), 0) " +
            "FROM Trade t " +
            "WHERE t.profile = :profile AND t.stock = :stock")
    int sumBuyAmountByProfileAndStock(@Param("profile") Profile profile, @Param("stock") Stock stock); // 해당 프로필이 해당 종목을 매수한 총 수량
}
