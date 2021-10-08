package bts.auction.api.producer.controller;

import bts.auction.api.producer.Repository.TimeRepository;
import bts.auction.api.producer.domain.Auction;
import bts.auction.api.producer.domain.Timer;
import bts.auction.api.producer.dto.AuctionDto;
import bts.auction.api.producer.dto.TimerDto;
import bts.auction.api.producer.service.AuctionService;
import bts.auction.api.producer.service.KafkaProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Api(value = "Auction Producer Controller")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auction")
public class AuctionController {
    private final KafkaProducer kafkaProducer;
    private final AuctionService auctionService;
    private final TimeRepository timeRepository;

    @ApiOperation(value = "경매 시작 시, 경매 마감 시간 설정")
    @PostMapping("/start")
    public String Auctionstart(@RequestBody TimerDto timeDto){
        LocalTime localTime = LocalTime.now().plusMinutes(timeDto.getTime()); //현재 시간 + 설정 시간
        System.out.println(timeDto);
        Time endtime = Time.valueOf(localTime);
        Timer time = Timer.builder().NftId(timeDto.getNft())
                                  .time(endtime).build();
        timeRepository.save(time);
        log.debug("========Auction Start========");
        return "timetable insert";
    }

    @ApiOperation(value = "NFT 경매 매수 참여 정보 Publishing")
    @PostMapping("/publish")
    public String produceMessage(@RequestBody AuctionDto auctiondto) {
        Auction produceAuction = new Auction();
        Timer time = timeRepository.findById(auctiondto.getNft_id()).orElse(null);
        System.out.println(time);
        produceAuction.setNft_id(auctiondto.getNft_id());
        produceAuction.setEmail(auctiondto.getEmail());
        produceAuction.setAuctionPrice(auctiondto.getAuctionPrice());
        produceAuction.setTime(time.getTime());
        //카프카 브로커로 메시지 전송
        kafkaProducer.sendMessage(produceAuction);
        //몽고 DB에 데이터 저장
        log.debug("========Produce Message========");
        return "kafka produce";
    }
}
