package bts.auction.api.producer.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class AuctionDto {
    private String nft_id;
    private String email;
    private Integer auctionPrice;
}
