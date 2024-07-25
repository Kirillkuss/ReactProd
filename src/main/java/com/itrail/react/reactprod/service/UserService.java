package com.itrail.react.reactprod.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Base64;
import com.itrail.react.reactprod.entity.User;
import com.itrail.react.reactprod.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${spring.security.secret}")
    private String secret;
 
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Генерация соли
     * @return String
     */
    private String generateSalt() {
        byte[] saltBytes = new byte[32];
        new SecureRandom().nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }
    /**
     * Поиск по логину
     * @param username
     * @return Mono<User>
     */
    public Mono<User> findByLogin( String username ){
        return userRepository.findByLogin( username );
    }
    /**
     * Проверка паролья при авторизации
     * @param rawPassword - декодированный пароль
     * @param salt - соль
     * @param encodedPassword - закодированный пароль
     * @return boolean
     */
    public boolean checkUserPassword(String rawPassword, String salt, String encodedPassword) {
        return passwordEncoder.matches( secret + rawPassword + salt, encodedPassword);
    }
    /**
     * Проверка размера и кол-во символов для пароля
     * @param password
     * @return boolean
     */
    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*[A-Za-z].*") && password.matches(".*\\d.*");
    }
    /**
     * Add user
     * @param user - user
     * @return Mono<User>
     */ public Mono<Object> addUser(User user) {
        if (!isValidPassword( user.getPassword())) throw new  IllegalArgumentException("Password does not meet complexity requirements!");
        return userRepository.findByLogin(user.getUsername())
                             .flatMap(existingUser -> {
                                    return Mono.error(new IllegalArgumentException("Not unique username, please specify another!"));
                              })
                              .switchIfEmpty(Mono.defer(() -> {
                                    String salt = generateSalt();
                                    user.setRole( "test");
                                    user.setPassword( passwordEncoder.encode( secret + user.getPassword() + salt ));
                                    user.setSalt(salt);
                                    return userRepository.save(user);
                                }));
    }

}
