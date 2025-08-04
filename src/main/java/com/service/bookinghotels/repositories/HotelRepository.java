package com.service.bookinghotels.repositories;
import com.service.bookinghotels.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {

    @Query("SELECT h FROM Hotel h WHERE h.name = :name")
    Hotel getHotelByName(String name);
}