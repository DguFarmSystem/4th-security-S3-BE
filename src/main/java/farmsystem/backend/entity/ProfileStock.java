package farmsystem.backend.entity;

import java.sql.Timestamp;

import farmsystem.backend.type.ActionType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ProfileStock {
  @Id
  private Long id;

  @ManyToOne
  @JoinColumn(name = "profile_id")
  private Profile profile;

  @ManyToOne
  @JoinColumn(name = "stock_id")
  private Stock stock;

  @Column(nullable = false)
  private Integer price;

  @Column(nullable = false)
  private Integer amount;

  @Enumerated(EnumType.STRING)
  private ActionType type;

  @Column(nullable = false)
  private Timestamp created_at;

  @Column(nullable = false)
  private Timestamp updated_at;
}
