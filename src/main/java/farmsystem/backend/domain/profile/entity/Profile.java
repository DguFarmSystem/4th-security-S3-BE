package farmsystem.backend.domain.profile.entity;

import java.util.Date;

import farmsystem.backend.domain.common.entity.BaseTimeEntity;
import farmsystem.backend.domain.member.entity.Member;
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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private ProfileType type;

    @Column(nullable = false)
    private Long balance;

    private Date startDate;
}
