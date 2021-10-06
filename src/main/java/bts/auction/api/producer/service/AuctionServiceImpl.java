package bts.auction.api.producer.service;

import bts.auction.api.producer.domain.Auction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    @Override
    public Mono<Auction> save(Auction auction) {
        return null;
    }
}
