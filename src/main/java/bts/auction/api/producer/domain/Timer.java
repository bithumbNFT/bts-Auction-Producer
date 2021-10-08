package bts.auction.api.producer.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@Getter
@NoArgsConstructor
@Entity(name = "timetable")
public class Timer {
    @Id
    String NftId;
    Time time;

    @Builder
    public Timer(String nftId, Time time)
    {
        this.NftId = nftId;
        this.time = time;
    }

}
