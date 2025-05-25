package farmsystem.backend.domain.portfolio.service;

import farmsystem.backend.domain.portfolio.dto.HoldingDto;
import farmsystem.backend.domain.portfolio.dto.PortfolioResponse;
import farmsystem.backend.domain.profile.entity.Profile;
import farmsystem.backend.domain.profile.repository.ProfileRepository;
import farmsystem.backend.domain.trade.repository.TradeRepository;
import farmsystem.backend.global.error.ErrorCode;
import farmsystem.backend.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PortfolioService {

    private final TradeRepository tradeRepository;
    private final ProfileRepository profileRepository;

    public List<PortfolioResponse> getPortfolio(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.PROFILE_NOT_FOUND));
        List<HoldingDto> holdings = tradeRepository.findHoldingsByProfile(profile);
        long totalQuantity = holdings.stream()
                .mapToLong(HoldingDto::quantity)
                .sum();
        return holdings.stream()
                .map(holding -> {
                    double ratio = totalQuantity == 0 ? 0.0 : (double) holding.quantity() / totalQuantity;
                    return PortfolioResponse.from(holding, ratio);
                })
                .collect(Collectors.toList());
    }
}