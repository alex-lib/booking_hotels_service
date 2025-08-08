package com.service.bookinghotels.repositories;
import com.service.bookinghotels.entities.statistics.EventType;
import com.service.bookinghotels.entities.statistics.StatisticEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StatisticRepository extends MongoRepository<StatisticEvent, String> {

    List<StatisticEvent> findByEventType(EventType eventType);
}