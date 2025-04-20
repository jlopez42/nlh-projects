package com.nlh.projects.service;


import com.nlh.projects.payloads.auth.request.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> signUp(SignUpRequest request);

}
