package farmsystem.backend.domain.profile.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import farmsystem.backend.domain.common.entity.BaseTimeEntity;
import farmsystem.backend.domain.member.entity.Member;
import farmsystem.backend.domain.trade.entity.Trade;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 10, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProfileType type;

    @Column(nullable = false)
    private long balance;

    private Date startDate;

    @OneToMany(mappedBy = "profile")
    private List<Trade> trades = new ArrayList<>();
}
