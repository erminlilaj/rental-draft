package it.linksmt.rental.service;

import it.linksmt.rental.dto.CreateUserRequest;
import it.linksmt.rental.dto.LoginUserRequest;
import it.linksmt.rental.entity.UserEntity;
import it.linksmt.rental.repository.UserRepository;
import it.linksmt.rental.security.SecurityBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public UserEntity signUp(CreateUserRequest createUserRequest) {
        UserEntity user = new UserEntity();
                user.setUsername(createUserRequest.getUsername());
                user.setName(createUserRequest.getName());
                user.setSurname(createUserRequest.getSurname());
                user.setEmail(createUserRequest.getEmail());
                String salt="salt";
                String password=createUserRequest.getPassword()+salt;
                user.setPassword(passwordEncoder.encode(password));
                user.setAge(createUserRequest.getAge());
                user.setUserType(createUserRequest.getUserType());
        return userRepository.save(user);
    }
public UserEntity authenticate(LoginUserRequest loginUserRequest){
    String salt="salt";
    String password=loginUserRequest.getPassword()+salt;
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserRequest.getUsername(),
                        password
                )
        );
        return userRepository.findByUsername(loginUserRequest.getUsername())
                .orElseThrow();
}

    public boolean isAdmin(SecurityBean currentUser) {
        return currentUser.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }


}
