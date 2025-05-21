package farmsystem.backend.domain.member.entity;

import farmsystem.backend.domain.common.entity.BaseTimeEntity;
import farmsystem.backend.domain.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 15, nullable = false)
    private String phoneNumb;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Builder
    public Member(String username, String phoneNumb, String email, String password) {
        this.username = username;
        this.phoneNumb = phoneNumb;
        this.email = email;
        this.password = password;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Profile> profiles = new ArrayList<>();

    public void addProfile(Profile profile) {
        profiles.add(profile);
    }
}
