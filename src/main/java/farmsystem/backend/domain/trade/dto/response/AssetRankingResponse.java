package farmsystem.backend.domain.trade.dto.response;

import lombok.Builder;

@Builder
public record AssetRankingResponse(
        String name,
        double asset
) {
    public static AssetRankingResponse of(String name, double asset) {
        return AssetRankingResponse.builder()
                .name(name)
                .asset(asset)
                .build();
    }
}
