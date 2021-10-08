package bts.auction.api.producer.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Getter
@NoArgsConstructor
public class TimerDto {
    String nft;
    Long time;
}
