package com.Secuirty.secuirtyproject.Services;

import org.springframework.http.ResponseEntity;

import com.Secuirty.secuirtyproject.Dto.JwtAuthenticationResponse;
import com.Secuirty.secuirtyproject.Dto.RefreshTokenRequest;
import com.Secuirty.secuirtyproject.Dto.SignUpRequest;
import com.Secuirty.secuirtyproject.Dto.SigninRequest;

public interface AuthService {
    ResponseEntity<?> signUp(SignUpRequest signUpRequest);
    ResponseEntity<?> signIn(SigninRequest signinRequest) ;
    JwtAuthenticationResponse RefreshToken (RefreshTokenRequest refreshTokenRequest);
}
