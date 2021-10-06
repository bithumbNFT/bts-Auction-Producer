package bts.auction.api.producer.service;

import bts.auction.api.producer.domain.Auction;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private static final String TOPIC = "auction";
    private final KafkaTemplate<String, Auction> kafkaTemplate;

    public void sendMessage(Auction message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
