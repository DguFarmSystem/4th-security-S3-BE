package farmsystem.backend.domain.trade.service;

import farmsystem.backend.domain.profile.entity.Profile;
import farmsystem.backend.domain.profile.entity.ProfileType;
import farmsystem.backend.domain.profile.repository.ProfileRepository;
import farmsystem.backend.domain.trade.dto.response.AssetRankingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankingService {

    private final ProfileRepository profileRepository;

    // 자산 랭킹 조회
    public List<AssetRankingResponse> getAssetRanking() {
        List<Profile> profiles = profileRepository.findByType(ProfileType.LIVE);

        profiles.sort(Comparator.comparingLong(Profile::getBalance).reversed()); // 정렬

        return profiles.stream()
                .map(profile -> AssetRankingResponse.of(profile.getMember().getUsername(), profile.getBalance()))
                .collect(Collectors.toList());
    }
}
