package ar.juarce.webapp.auth;

import ar.juarce.interfaces.PrayerRequestService;
import ar.juarce.interfaces.UserService;
import ar.juarce.models.PrayerRequest;
import ar.juarce.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * <a href="https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html#_migrating_expressions">migrating_expressions</a>
 * <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/AntPathMatcher.html">AntPathMatcher</a>
 */
@Component()
public class AccessControl {

    private final UserService userService;
    private final PrayerRequestService prayerRequestService;

    @Autowired
    public AccessControl(UserService userService,
                         PrayerRequestService prayerRequestService) {
        this.userService = userService;
        this.prayerRequestService = prayerRequestService;
    }

    public UserDetails getAuthenticatedUserDetails(Authentication authentication) {
        if (!(authentication.getPrincipal() instanceof UserDetails)) {
            return null;
        }
        return (UserDetails) authentication.getPrincipal();
    }

    public User getAuthenticatedUser(Authentication authentication) {
        final UserDetails userDetails = getAuthenticatedUserDetails(authentication);
        if (userDetails == null) {
            return null;
        }
        return userService.findById(Long.parseLong(userDetails.getUsername())).orElse(null);
    }

    public boolean isAuthenticatedUser(Authentication authentication, Long id) {
        final User user = getAuthenticatedUser(authentication);
        return user != null && user.getId().equals(id);
    }

    public boolean isNotPrayerRequester(Authentication authentication, Long prayerRequestId) {
        final Optional<PrayerRequest> prayerRequest = prayerRequestService.findById(prayerRequestId);
        if (prayerRequest.isEmpty()) {
            return true; // Controller will handle this through NotFoundException
        }

        final User user = getAuthenticatedUser(authentication);
        return user != null && !user.equals(prayerRequest.get().getRequester());
    }
}
