package farmsystem.backend.domain.trade.service;

import farmsystem.backend.domain.profile.entity.Profile;
import farmsystem.backend.domain.profile.entity.ProfileType;
import farmsystem.backend.domain.profile.repository.ProfileRepository;
import farmsystem.backend.domain.stock.entity.Stock;
import farmsystem.backend.domain.stock.repository.StockRepository;
import farmsystem.backend.domain.trade.dto.request.LiveTradeRequest;
import farmsystem.backend.domain.trade.dto.request.VirtualTradeRequest;
import farmsystem.backend.domain.trade.dto.response.BuyTradeResponse;
import farmsystem.backend.domain.trade.dto.response.SellTradeResponse;
import farmsystem.backend.domain.trade.entity.Trade;
import farmsystem.backend.domain.trade.entity.TradeType;
import farmsystem.backend.domain.trade.repository.TradeRepository;
import farmsystem.backend.global.error.ErrorCode;
import farmsystem.backend.global.error.exception.EntityNotFoundException;
import farmsystem.backend.global.error.exception.InternalServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TradeService {

    private final TradeRepository tradeRepository;
    private final StockRepository stockRepository;
    private final ProfileRepository profileRepository;

    @Transactional
    public Object createVirtualTrade(VirtualTradeRequest request) {
        Stock stock = stockRepository.findByCode(request.stockCode())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.STOCK_NOT_FOUND));

        Profile profile = profileRepository.findById(request.profileId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.PROFILE_NOT_FOUND));

        if (profile.getType() == ProfileType.LIVE) {
            throw new InternalServerException(ErrorCode.INVALID_PROFILE_TYPE);
        }

        /**
         * TODO - 거래 비밀번호 검증
         *      시점모드 시작일자 이후와 거래 날짜? 비교
         */

        evaluateBalance(request.price(), request.amount(), profile, request.type(), stock);
        Trade savedTrade = tradeRepository.save(request.toEntity(profile, stock));

        return createTradeResponse(savedTrade, profile, stock, request.type());
    }

    @Transactional
    public Object createLiveTrade(Long memberId, LiveTradeRequest request) {
        Stock stock = stockRepository.findByCode(request.stockCode())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.STOCK_NOT_FOUND));

        Profile profile = profileRepository.findByMemberIdAndType(memberId, ProfileType.LIVE)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.PROFILE_NOT_FOUND));

        evaluateBalance(request.price(), request.amount(), profile, request.type(), stock);
        Trade savedTrade = tradeRepository.save(request.toEntity(profile, stock));

        return createTradeResponse(savedTrade, profile, stock, request.type());
    }

    // balance 계산
    public void evaluateBalance(int price, int amount, Profile profile, TradeType type, Stock stock) {
        int totalPrice = price * amount;

        if (type == TradeType.BUY) {
            if (profile.getBalance() < totalPrice) {
                throw new InternalServerException(ErrorCode.INSUFFICIENT_BALANCE);
            }
            profile.decreaseBalance(totalPrice);
        } else {
            validateHolding(profile, stock, amount);
            profile.increaseBalance(totalPrice);
        }
    }

    // SELL일 경우 해당 종목 현재 보유 수량 계산
    private void validateHolding(Profile profile, Stock stock, int sellAmount) {
        int holdingAmount = tradeRepository.sumAmountByProfileAndStock(profile, stock);

        if (holdingAmount < sellAmount) {
            throw new InternalServerException(ErrorCode.INSUFFICIENT_STOCK);
        }
    }

    private Object createTradeResponse(Trade trade, Profile profile, Stock stock, TradeType type) {
        int holdingAmount = tradeRepository.sumAmountByProfileAndStock(profile, stock);

        if (type == TradeType.BUY) {
            int totalBuyPrice = tradeRepository.sumBuyPriceByProfileAndStock(profile, stock);
            int totalBuyAmount = tradeRepository.sumBuyAmountByProfileAndStock(profile, stock);
            int averagePrice = totalBuyAmount > 0 ? totalBuyPrice / totalBuyAmount : 0; // 평단가 계산
            return BuyTradeResponse.of(trade, holdingAmount, averagePrice);
        } else {
            return SellTradeResponse.of(trade, holdingAmount);
        }
    }
}
