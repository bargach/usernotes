package ru.test.usernotes.services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.test.usernotes.entities.User;

import static java.util.Collections.singleton;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username " + username + " is not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}
