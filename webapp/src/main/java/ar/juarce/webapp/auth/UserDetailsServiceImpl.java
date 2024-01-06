package ar.juarce.webapp.auth;

import ar.juarce.interfaces.UserService;
import ar.juarce.models.Role;
import ar.juarce.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * @param username the id identifying the user whose data is required.
     * @return a {@link UserDetails} object containing the user's data.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userService.findById(Long.parseLong(username)).orElseThrow(() -> new BadCredentialsException("Bad credentials")); // TODO throw custom exception
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getId().toString())
                .password("")
                .disabled(!user.isEnabled()) // TODO spring da vuelta el valor en el builder y termina siendo enabled en vez de disabled (raro)
                .authorities(getAuthorities(user))
                .build();
    }

    private Collection<GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream()
                .map(Role::getRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
