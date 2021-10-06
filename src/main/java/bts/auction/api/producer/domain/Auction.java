package bts.auction.api.producer.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiParam;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "auction")
@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Auction implements Serializable {
    @ApiParam(value = "경매 Id", required = true)
    private @Id
    String a_id;
    @ApiParam(value = "NFT Id", required = true)
    private String nft_id;
    @ApiParam(value = "매수 참여자 email", required = true)
    private String email;
    @ApiParam(value = "현재 매수 호가", required = true)
    private Integer auctionPrice;

    @Builder
    public Auction(String nft_id, String email, int auctionPrice) {
        this.nft_id = nft_id;
        this.email = email;
        this.auctionPrice = auctionPrice;
    }
}
