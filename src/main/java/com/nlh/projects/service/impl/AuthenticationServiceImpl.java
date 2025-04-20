package com.nlh.projects.service.impl;


import com.nlh.projects.payloads.auth.request.SignUpRequest;
import com.nlh.projects.payloads.auth.response.MessageResponse;
import com.nlh.projects.repository.RoleRepository;
import com.nlh.projects.repository.UserRepository;
import com.nlh.projects.repository.entity.ERole;
import com.nlh.projects.repository.entity.Role;
import com.nlh.projects.repository.entity.User;
import com.nlh.projects.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public ResponseEntity<?> signUp(SignUpRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message("Error: Username is already taken").build());
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(MessageResponse.builder().message("Error: Email is already in use").build());
        }

        //Create new user's account
        User user = User.builder().username(request.getUsername())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword())).build();

        Set<String> strRoles = request.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(adminRole);
                        break;
                    case "PROVIDER":
                        Role providerRole = roleRepository.findByName(ERole.ROLE_PROVIDER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(providerRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(userRole);

                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.badRequest().body(MessageResponse.builder().message("Success: User Registered").build());
    }

}
