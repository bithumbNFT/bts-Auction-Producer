package bts.auction.api.producer.controller;

import bts.auction.api.producer.domain.Auction;
import bts.auction.api.producer.service.AuctionService;
import bts.auction.api.producer.service.KafkaProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Auction Producer Controller")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auction")
public class AuctionController {
    private final KafkaProducer kafkaProducer;
    private final AuctionService auctionService;

    @ApiOperation(value = "NFT 경매 매수 참여 정보 Publishing")
    @PostMapping("/publish")
    public void produceMessage(@RequestBody Auction auction) {
        Auction produceAuction = new Auction();
        produceAuction.setNft_id(auction.getNft_id());
        produceAuction.setEmail(auction.getEmail());
        produceAuction.setAuctionPrice(auction.getAuctionPrice());
        //카프카 브로커로 메시지 전송
        kafkaProducer.sendMessage(produceAuction);
        //몽고 DB에 데이터 저장
    }
}
