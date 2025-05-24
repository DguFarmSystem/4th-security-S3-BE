package farmsystem.backend.domain.trade.entity;

import farmsystem.backend.domain.common.entity.BaseTimeEntity;
import farmsystem.backend.domain.profile.entity.Profile;
import farmsystem.backend.domain.stock.entity.Stock;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trade extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TradeType type;

    @Builder
    public Trade(Profile profile, Stock stock, int price, int amount, TradeType type) {
        this.profile = profile;
        this.stock = stock;
        this.price = price;
        this.amount = amount;
        this.type = type;
    }
}
