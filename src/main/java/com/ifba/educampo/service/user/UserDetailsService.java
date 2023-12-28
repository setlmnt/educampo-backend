package com.ifba.educampo.service.user;

import com.ifba.educampo.annotation.Log;
import com.ifba.educampo.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Log
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Finding user by username {}", username);
        UserDetails user = userRepository.findByUsernameAndDeletedFalse(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }
}