package farmsystem.backend.domain.profile.dto.request;

import farmsystem.backend.domain.member.entity.Member;
import farmsystem.backend.domain.profile.entity.Profile;
import farmsystem.backend.domain.profile.entity.ProfileType;

import java.time.LocalDate;

public record CreateProfileRequest(
        String name,
        long balance,
        LocalDate startDate
) {
    public Profile toEntity(Member member) {
        return Profile.builder()
                .member(member)
                .name(name)
                .type(ProfileType.VIRTUAL)
                .balance(balance)
                .startDate(startDate)
                .build();
    }
}
