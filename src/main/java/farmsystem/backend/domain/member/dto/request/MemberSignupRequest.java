package farmsystem.backend.domain.member.dto.request;


import farmsystem.backend.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;

public record MemberSignupRequest(
        @NotBlank
        String username,
        @NotBlank
        String phoneNumb,
        @NotBlank
        String email,
        @NotBlank
        String password
) {
        public Member toEntity(String encodedPassword){
                return Member.builder()
                        .username(username)
                        .phoneNumb(phoneNumb)
                        .email(email)
                        .password(encodedPassword).build();
        }
}