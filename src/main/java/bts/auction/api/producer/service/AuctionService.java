package bts.auction.api.producer.service;

import bts.auction.api.producer.domain.Auction;
import reactor.core.publisher.Mono;

public interface AuctionService {
    Mono<Auction> save(Auction auction);
}
