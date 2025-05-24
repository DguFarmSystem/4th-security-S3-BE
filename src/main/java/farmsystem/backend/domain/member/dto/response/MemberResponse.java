package farmsystem.backend.domain.member.dto.response;

import farmsystem.backend.domain.member.entity.Member;
import lombok.Builder;

@Builder
public record MemberResponse(
        long id,
        String username,
        String phoneNumb,
        String email
) {
    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .username(member.getUsername())
                .phoneNumb(member.getPhoneNumb())
                .email(member.getEmail())
                .build();
    }
}