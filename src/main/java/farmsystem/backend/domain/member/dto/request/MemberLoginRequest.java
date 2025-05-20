package farmsystem.backend.domain.member.dto.request;

import farmsystem.backend.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;

public record MemberLoginRequest(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
        public Member toEntity(){
                return Member.builder()
                        .email(email)
                        .password(password)
                        .build();
        }
}
