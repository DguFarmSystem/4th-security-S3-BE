package farmsystem.backend.domain.member.service;

import farmsystem.backend.domain.member.dto.request.MemberLoginRequest;
import farmsystem.backend.domain.member.dto.request.MemberSignupRequest;
import farmsystem.backend.domain.member.dto.response.MemberLoginResponse;
import farmsystem.backend.domain.member.dto.response.MemberResponse;
import farmsystem.backend.domain.member.entity.Member;
import farmsystem.backend.domain.member.repository.MemberRepository;
import farmsystem.backend.domain.profile.entity.Profile;
import farmsystem.backend.domain.profile.entity.ProfileType;
import farmsystem.backend.global.common.JwtUtil;
import farmsystem.backend.global.error.ErrorCode;
import farmsystem.backend.global.error.exception.ConflictException;
import farmsystem.backend.global.error.exception.EntityNotFoundException;
import farmsystem.backend.global.error.exception.InvalidValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public MemberResponse signUp(MemberSignupRequest memberSignupRequest) {
        if(memberRepository.existsByEmail(memberSignupRequest.email())){
            throw new ConflictException(ErrorCode.MEMBER_EMAIL_EXIST);
        }

        String encodedPassword = passwordEncoder.encode(memberSignupRequest.password());

        Member memberToSave = memberSignupRequest.toEntity(encodedPassword);
        Profile profile = Profile.builder()
                .member(memberToSave)
                .name(memberToSave.getUsername())
                .type(ProfileType.LIVE)
                .balance(1000000)
                .startDate(LocalDate.now())
                .build();
        memberToSave.addProfile(profile);

        Member member = memberRepository.save(memberToSave);
        return MemberResponse.from(member);
    }

    public MemberLoginResponse login(MemberLoginRequest memberLoginRequest) {
        Member member = memberRepository.findByEmail(memberLoginRequest.email())
                .orElseThrow(()->new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        if(!passwordEncoder.matches(memberLoginRequest.password(), member.getPassword())){
            throw new InvalidValueException(ErrorCode.MEMBER_WRONG_PASSWORD);
        }

        String token = jwtUtil.generateToken(member.getEmail());

        return MemberLoginResponse.from(member,token);
    }
}
