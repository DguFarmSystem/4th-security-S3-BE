package farmsystem.backend.domain.profile.repository;

import farmsystem.backend.domain.member.entity.Member;
import farmsystem.backend.domain.profile.entity.Profile;
import farmsystem.backend.domain.profile.entity.ProfileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> findAllByMember(Member member);

    Optional<Profile> findByMemberIdAndType(Long memberId, ProfileType type);

    List<Profile> findByType(ProfileType type);
}
