package farmsystem.backend.domain.trade.service;

import farmsystem.backend.domain.profile.entity.Profile;
import farmsystem.backend.domain.profile.repository.ProfileRepository;
import farmsystem.backend.domain.stock.entity.Stock;
import farmsystem.backend.domain.stock.repository.StockRepository;
import farmsystem.backend.domain.trade.dto.request.TradeRequest;
import farmsystem.backend.domain.trade.dto.response.TradeResponse;
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
    public TradeResponse creatTrade(TradeRequest request) {
        Stock stock = stockRepository.findById(request.stockId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.STOCK_NOT_FOUND));

        Profile profile = profileRepository.findById(request.profileId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.PROFILE_NOT_FOUND));

        /**
         * TODO - 거래 비밀번호 검증
         *      시점모드 시작일자 이후와 거래 날짜? 비교
         *      종목id 대신 종목 코드 입력 받도록
         */

        int totalPrice = request.price() * request.amount();

        if (request.type() == TradeType.BUY) {
            if (profile.getBalance() < totalPrice) {
                throw new InternalServerException(ErrorCode.INSUFFICIENT_BALANCE);
            }
            profile.decreaseBalance(totalPrice);
        } else {
            profile.increaseBalance(totalPrice);
        }

        Trade savedTrade =tradeRepository.save(request.toEntity(profile, stock));
        return TradeResponse.from(savedTrade);
    }
}
