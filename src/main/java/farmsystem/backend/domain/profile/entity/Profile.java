package farmsystem.backend.domain.profile.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import farmsystem.backend.domain.common.entity.BaseTimeEntity;
import farmsystem.backend.domain.member.entity.Member;
import farmsystem.backend.domain.trade.entity.Trade;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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

    private LocalDate startDate;

    @Builder
    public Profile(Member member, String name, ProfileType type, long balance, LocalDate startDate) {
        this.member = member;
        this.name = name;
        this.type = type;
        this.balance = balance;
        this.startDate = startDate;
    }

    @OneToMany(mappedBy = "profile")
    private List<Trade> trades = new ArrayList<>();
}
