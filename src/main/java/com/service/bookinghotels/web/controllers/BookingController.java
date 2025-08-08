package com.service.bookinghotels.web.controllers;
import com.service.bookinghotels.aop.booking.CheckBookingAccess;
import com.service.bookinghotels.entities.Booking;
import com.service.bookinghotels.mappers.booking.BookingMapper;
import com.service.bookinghotels.services.BookingService;
import com.service.bookinghotels.web.dto.booking.BookingRequest;
import com.service.bookinghotels.web.dto.booking.BookingResponse;
import com.service.bookinghotels.web.dto.booking.BookingsListResponse;
import com.service.bookinghotels.web.dto.kafkadto.BookingRoomEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    @Value("${app.kafka.bookingRoomTopic}")
    private String topicName;

    private final KafkaTemplate<String, BookingRoomEvent> kafkaTemplateBookingRoomEvent;

    private final BookingMapper bookingMapper;

    private final BookingService bookingService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public BookingResponse createBooking(@RequestBody BookingRequest bookingRequest,
                                         @AuthenticationPrincipal UserDetails user) {
        Booking newBooking = bookingService.createBooking(bookingMapper.bookingRequestToBooking(bookingRequest, user));
        kafkaTemplateBookingRoomEvent.send(topicName, bookingMapper.bookingToBookingRoomEvent(newBooking, user));
        return bookingMapper.bookingToBookingResponse(newBooking);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @CheckBookingAccess
    public BookingResponse findBookingById(@PathVariable Long id,
                                           @AuthenticationPrincipal UserDetails user) {
        return bookingMapper.bookingToBookingResponse(bookingService.getBookingById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @CheckBookingAccess
    public BookingResponse updateBooking(@PathVariable Long id,
                                         @AuthenticationPrincipal UserDetails user,
                                         @RequestBody BookingRequest bookingRequest) {
        return bookingMapper.bookingToBookingResponse(bookingService
                .updateBooking(id, bookingMapper.bookingRequestToBooking(id, bookingRequest, user)));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @CheckBookingAccess
    public void deleteBooking(@PathVariable Long id,
                              @AuthenticationPrincipal UserDetails user) {
        bookingService.deleteBooking(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/find-all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BookingsListResponse findAllBookings() {
        return bookingMapper.bookingListToBookingsListResponse(bookingService.getAllBookings());
    }
}