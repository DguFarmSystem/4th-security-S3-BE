package farmsystem.backend.domain.member.dto.response;

import farmsystem.backend.domain.member.entity.Member;
import lombok.Builder;

@Builder
public record MemberLoginResponse(
        long id,
        String username,
        String phoneNumb,
        String email,
        String token
) {
    public static MemberLoginResponse from(Member member,String token) {
        return MemberLoginResponse.builder()
                .id(member.getId())
                .username(member.getUsername())
                .phoneNumb(member.getPhoneNumb())
                .email(member.getEmail())
                .token(token)
                .build();
    }
}