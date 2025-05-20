package farmsystem.backend.domain.profile.service;

import farmsystem.backend.domain.member.entity.Member;
import farmsystem.backend.domain.member.repository.MemberRepository;
import farmsystem.backend.domain.profile.dto.response.ProfileResponse;
import farmsystem.backend.domain.profile.dto.request.CreateProfileRequest;
import farmsystem.backend.domain.profile.entity.Profile;
import farmsystem.backend.domain.profile.repository.ProfileRepository;
import farmsystem.backend.global.error.ErrorCode;
import farmsystem.backend.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {

    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    @Transactional
    public ProfileResponse createProfile(Long memberId, CreateProfileRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
        Profile savedProfile = profileRepository.save(request.toEntity(member));
        return ProfileResponse.from(savedProfile);
    }

    public List<ProfileResponse> getProfileList(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
        List<Profile> profiles = profileRepository.findAllByMember(member);
        return profiles.stream()
                .map(ProfileResponse::from)
                .toList();
    }
}
