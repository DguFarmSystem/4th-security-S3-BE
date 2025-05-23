package farmsystem.backend.domain.member.authorize;

import farmsystem.backend.domain.member.entity.Member;
import farmsystem.backend.domain.member.repository.MemberRepository;
import farmsystem.backend.global.error.ErrorCode;
import farmsystem.backend.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(()-> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        return new CustomUserDetails(member);
    }
}
