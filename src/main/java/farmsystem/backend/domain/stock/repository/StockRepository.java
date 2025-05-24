package farmsystem.backend.domain.stock.repository;

import farmsystem.backend.domain.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByCode(String code);
}
