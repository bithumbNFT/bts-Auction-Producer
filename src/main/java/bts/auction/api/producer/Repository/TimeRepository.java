package bts.auction.api.producer.Repository;

import bts.auction.api.producer.domain.Timer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Timer, String> {
}
