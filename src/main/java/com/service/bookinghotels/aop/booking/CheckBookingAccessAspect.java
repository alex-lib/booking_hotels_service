package com.service.bookinghotels.aop.booking;
import com.service.bookinghotels.exceptions.UserAccessException;
import com.service.bookinghotels.security.AppUserPrincipal;
import com.service.bookinghotels.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class CheckBookingAccessAspect {

    private final BookingService bookingService;

    @Before("@annotation(CheckBookingAccess) && args(id, user,..)")
    public void checkAccess(Long id, UserDetails user) {
        AppUserPrincipal principal = (AppUserPrincipal) user;
        List<String> roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (roles.stream().noneMatch(role -> role.equals("ROLE_ADMIN") ||
                (role.equals("ROLE_USER") && principal.getId().equals(bookingService.getBookingById(id).getUser().getId())))) {
            throw new UserAccessException("Impossible to update/delete/get booking because booking belongs other user or role is not admin");
        }
    }
}