package com.service.bookinghotels.services.specifications;
import com.service.bookinghotels.entities.Hotel;
import com.service.bookinghotels.web.dto.hotel.HotelFilter;
import org.springframework.data.jpa.domain.Specification;

public interface HotelSpecification {

    static Specification<Hotel> withFilter(HotelFilter hotelFilter) {
        return Specification.where(byHotelId(hotelFilter.getId()))
                .and(byHotelTitle(hotelFilter.getTitle()))
                .and(byHotelName(hotelFilter.getName()))
                .and(byHotelCity(hotelFilter.getCity()))
                .and(byHotelAddress(hotelFilter.getAddress()))
                .and(byHotelDistanceFromCityCentre(hotelFilter.getDistanceFromCityCentre()))
                .and(byHotelRating(hotelFilter.getRating()))
                .and(byHotelGradesCount(hotelFilter.getGradesCount()));
    }

     static Specification<Hotel> byHotelId(Long id) {
        return ((root, query, criteriaBuilder) -> {
            if (id == null) return null;
            return criteriaBuilder.equal(root.get("id"), id);
        });
    }

     static Specification<Hotel> byHotelTitle(String title) {
        return ((root, query, criteriaBuilder) -> {
            if (title == null) return null;
            return criteriaBuilder.equal(root.get("title"), title);
        });
    }

    static Specification<Hotel> byHotelName(String name) {
        return ((root, query, criteriaBuilder) -> {
            if (name == null) return null;
            return criteriaBuilder.equal(root.get("name"), name);
        });
    }

    static Specification<Hotel> byHotelCity(String city) {
        return ((root, query, criteriaBuilder) -> {
            if (city == null) return null;
            return criteriaBuilder.equal(root.get("city"), city);
        });
    }

    static Specification<Hotel> byHotelAddress(String address) {
        return ((root, query, criteriaBuilder) -> {
            if (address == null) return null;
            return criteriaBuilder.equal(root.get("address"), address);
        });
    }

    static Specification<Hotel> byHotelDistanceFromCityCentre(Double distanceFromCityCentre) {
        return ((root, query, criteriaBuilder) -> {
            if (distanceFromCityCentre == null) return null;
            return criteriaBuilder.equal(root.get("distanceFromCityCentre"), distanceFromCityCentre);
        });
    }

    static Specification<Hotel> byHotelRating(Double rating) {
        return ((root, query, criteriaBuilder) -> {
            if (rating == null) return null;
            return criteriaBuilder.equal(root.get("rating"), rating);
        });
    }

    static Specification<Hotel> byHotelGradesCount(Integer gradesCount) {
        return ((root, query, criteriaBuilder) -> {
            if (gradesCount == null) return null;
            return criteriaBuilder.equal(root.get("gradesCount"), gradesCount);
        });
        }
    }