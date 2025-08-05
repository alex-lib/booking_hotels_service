package com.service.bookinghotels.services.specifications;
import com.service.bookinghotels.entities.Room;
import com.service.bookinghotels.entities.UnavailableDate;
import com.service.bookinghotels.web.dto.room.RoomFilter;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;
import java.math.BigDecimal;
import java.time.LocalDate;

public interface RoomSpecification {

    static Specification<Room> withFilter(RoomFilter roomFilter) {
        return Specification.where(byRoomId(roomFilter.getRoomId()))
                .and(byRoomName(roomFilter.getRoomName()))
                .and(byRoomsHotelId(roomFilter.getHotelId()))
                .and(byPersonsPerRoom(roomFilter.getPeoplePerRoom()))
                .and(byMaxRoomPrice(roomFilter.getMaxPrice()))
                .and(byMinRoomPrice(roomFilter.getMinPrice()))
                .and(byCheckInAndCheckOutDates(roomFilter.getCheckInDate(), roomFilter.getCheckOutDate()));
    }

    static Specification<Room> byRoomId(Long roomId) {
        return ((root, query, criteriaBuilder) -> {
            if (roomId == null) return null;
            return criteriaBuilder.equal(root.get("id"), roomId);
        });
    }

    static Specification<Room> byRoomName(String roomName) {
        return ((root, query, criteriaBuilder) -> {
            if (roomName == null) return null;
            return criteriaBuilder.equal(root.get("name"), roomName);
        });
    }

    static Specification<Room> byRoomsHotelId(Long hotelId) {
        return ((root, query, criteriaBuilder) -> {
            if (hotelId == null) return null;
            return criteriaBuilder.equal(root.get("hotel").get("id"), hotelId);
        });
    }

    static Specification<Room> byPersonsPerRoom(Integer personsPerRoom) {
        return ((root, query, criteriaBuilder) -> {
            if (personsPerRoom == null) return null;
            return criteriaBuilder.equal(root.get("maxPeoplePerRoom"), personsPerRoom);
        });
    }

    static Specification<Room> byMinRoomPrice(BigDecimal minPrice) {
        return ((root, query, criteriaBuilder) -> {
            if (minPrice == null) return null;
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
        });
    }

    static Specification<Room> byMaxRoomPrice(BigDecimal maxPrice) {
        return ((root, query, criteriaBuilder) -> {
            if (maxPrice == null) return null;
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
        });
    }

    static Specification<Room> byCheckInAndCheckOutDates(LocalDate checkInDate, LocalDate checkOutDate) {
        return (root, query, cb) -> {
            if (checkInDate == null || checkOutDate == null) return null;
            Subquery<UnavailableDate> subquery = query.subquery(UnavailableDate.class);
            Root<UnavailableDate> unavailableRoot = subquery.from(UnavailableDate.class);
            subquery.select(unavailableRoot);
            Predicate sameRoom = cb.equal(unavailableRoot.get("room"), root);
            Predicate dateOverlap = cb.and(
                    cb.greaterThanOrEqualTo(unavailableRoot.get("date"), checkInDate),
                    cb.lessThan(unavailableRoot.get("date"), checkOutDate)
            );
            subquery.where(cb.and(sameRoom, dateOverlap));
            return cb.not(cb.exists(subquery));
        };
    }
}