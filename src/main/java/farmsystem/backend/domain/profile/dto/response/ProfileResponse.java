package farmsystem.backend.domain.profile.dto.response;

import farmsystem.backend.domain.member.dto.response.MemberResponse;
import farmsystem.backend.domain.profile.entity.Profile;
import farmsystem.backend.domain.profile.entity.ProfileType;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record ProfileResponse(
        long id,
        MemberResponse member,
        ProfileType type,
        String name,
        long balance,
        LocalDate startDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ProfileResponse from(Profile profile) {
        return ProfileResponse.builder()
                .id(profile.getId())
                .member(MemberResponse.from(profile.getMember()))
                .type(profile.getType())
                .name(profile.getName())
                .balance(profile.getBalance())
                .startDate(profile.getStartDate())
                .createdAt(profile.getCreatedAt())
                .updatedAt(profile.getUpdatedAt())
                .build();
    }
}
