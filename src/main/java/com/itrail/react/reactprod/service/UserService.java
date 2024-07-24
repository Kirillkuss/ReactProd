package com.itrail.react.reactprod.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itrail.react.reactprod.entity.User;
import com.itrail.react.reactprod.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
 
    private final UserRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    
    public Mono<User> findByLogin( String username ){
        return usersRepository.findByLogin( username );
    }

    public Flux<String> getPassword(){
        return usersRepository.getPassword();
    }

    public boolean checkUserPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


}
