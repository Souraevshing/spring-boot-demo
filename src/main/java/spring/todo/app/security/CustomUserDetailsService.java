package spring.todo.app.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.todo.app.repository.UserRepository;
import spring.todo.app.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    // calling loadUserByUsername() and passing username to fetch username or emailId from database
    // calling getByUsernameOrEmail() of UserRepository interface and passing both username and emailId since we want to use username or emailId to authorize login

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.getByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Username doesn't exist!"));

        // creating Set of type GrantedAuthority to get user roles from table user_roles and map the incoming roles from getName() setter method
        // and then mapping roles of type SimpleGrantedAuthority

        Set<GrantedAuthority> authority = user
                .getRoles()
                .stream()
                .map((role -> new SimpleGrantedAuthority(role.getName())))
                .collect(Collectors.toSet());

        // returning User of type org.springframework.security.core.userdetails by passing usernameorEmail, password, and authority object.
        // passing usernameOrEmail as field coming from loadUserByUsername method.
        //by returning usernameOrEmail and password we will be able to login user with roles ADMIN USER.

        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.getPassword(),
                authority
        );
    }
}
