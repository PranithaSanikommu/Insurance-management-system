
package com.project.insurance_claim_management.service;



import java.util.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.project.insurance_claim_management.model.User;
import com.project.insurance_claim_management.repository.UserRepository;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        Optional<User> userOpt = userRepository.findByEmail(email);
        User dbUser;

        if (userOpt.isEmpty()) {
            dbUser = new User();
            dbUser.setEmail(email);
            dbUser.setName(name);
            dbUser.setRole("ROLE_USER");
            userRepository.save(dbUser);
        } else {
            dbUser = userOpt.get();
        }

        // ✅ This adds the user's role to the authorities
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(dbUser.getRole()));

        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), "email");
    }
}