package farmsystem.backend.domain.portfolio.controller;

import farmsystem.backend.domain.portfolio.service.PortfolioService;
import farmsystem.backend.global.common.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/portfolios")
@RequiredArgsConstructor
public class PortfolioController implements PortfolioApi {

    private final PortfolioService portfolioService;

    @GetMapping("/{profileId}")
    public ResponseEntity<SuccessResponse<?>> getPortfolio(@PathVariable Long profileId) {
        return SuccessResponse.ok(portfolioService.getPortfolio(profileId));
    }
}
