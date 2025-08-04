package com.service.bookinghotels.aop.user;
import com.service.bookinghotels.exceptions.UserAccessException;
import com.service.bookinghotels.security.AppUserPrincipal;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.List;

@Aspect
@Component
public class CheckUserAccessAspect {

    @Before("@annotation(CheckUserAccess) && args(id, user,..)")
    public void checkAccess(Long id, UserDetails user) {
        AppUserPrincipal principal = (AppUserPrincipal) user;
        List<String> roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (roles.stream().noneMatch(role -> role.equals("ROLE_ADMIN") ||
                (role.equals("ROLE_USER") && principal.getId().equals(id)))) {
            throw new UserAccessException("Impossible to update/delete/get user because id belongs other user or role is not admin");
        }
    }
}