package dev.shouteck.crypto_trading_bot;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AggregatedTickerService {

    private final TickerRepository tickerRepository;

    public AggregatedTickerService(TickerRepository tickerRepository) {
        this.tickerRepository = tickerRepository;
    }

    public List<AggregatedPrice> getAggregatedBestPrices() {
        List<TickerEntity> tickerEntities = tickerRepository.findAll();

        Map<String, List<TickerEntity>> groupedBySymbol = tickerEntities.stream()
                .collect(Collectors.groupingBy(t -> t.getId().getSymbol().toLowerCase()));

        List<AggregatedPrice> result = new ArrayList<>();
        for (Map.Entry<String, List<TickerEntity>> entry : groupedBySymbol.entrySet()) {
            String symbol = entry.getKey();
            List<TickerEntity> tickersForSymbol = entry.getValue();

            Map<BigDecimal, BigDecimal> aggregatedBids = aggregateOrders(tickersForSymbol, true);
            Map<BigDecimal, BigDecimal> aggregatedAsks = aggregateOrders(tickersForSymbol, false);

            BigDecimal bestBidPrice = getBestPrice(aggregatedBids, true);
            BigDecimal bestBidQuantity = aggregatedBids.getOrDefault(bestBidPrice, BigDecimal.ZERO);

            BigDecimal bestAskPrice = getBestPrice(aggregatedAsks, false);
            BigDecimal bestAskQuantity = aggregatedAsks.getOrDefault(bestAskPrice, BigDecimal.ZERO);

            result.add(new AggregatedPrice(
                    symbol.toUpperCase(), bestBidPrice, bestBidQuantity, bestAskPrice, bestAskQuantity
            ));
        }

        return result;
    }

    private Map<BigDecimal, BigDecimal> aggregateOrders(List<TickerEntity> tickerEntities, boolean isBid) {
        Map<BigDecimal, BigDecimal> aggregatedOrders = new HashMap<>();

        for (TickerEntity tickerEntity : tickerEntities) {
            BigDecimal price = isBid ? tickerEntity.getBidPrice() : tickerEntity.getAskPrice();
            BigDecimal quantity = isBid ? tickerEntity.getBidQuantity() : tickerEntity.getAskQuantity();

            aggregatedOrders.put(price, aggregatedOrders.getOrDefault(price, BigDecimal.ZERO).add(quantity));
        }

        return aggregatedOrders;
    }

    private BigDecimal getBestPrice(Map<BigDecimal, BigDecimal> aggregatedOrders, boolean isBid) {
        return aggregatedOrders.keySet().stream()
                .reduce((p1, p2) -> isBid ? p1.min(p2) : p1.max(p2)) // Min for bids, Max for asks
                .orElse(BigDecimal.ZERO); // Default to zero if no prices
    }
}
