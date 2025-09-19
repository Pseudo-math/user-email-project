package ru.aidar.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.aidar.entity.UserCredentional;
import ru.aidar.model.CustomUserDetails;
import ru.aidar.repository.UserCredentionalRepository;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserCredentionalRepository userCredentionalRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredentional> user = userCredentionalRepository.findByName(username);
        UserCredentional userCredentional = user.get();
        CustomUserDetails customUserDetails = new CustomUserDetails(userCredentional.getPassword(), userCredentional.getName());
        return customUserDetails;
    }
}
