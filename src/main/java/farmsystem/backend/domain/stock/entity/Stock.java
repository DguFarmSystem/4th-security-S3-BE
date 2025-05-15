package farmsystem.backend.domain.stock.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Stock {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;
}
