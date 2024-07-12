package com.Secuirty.secuirtyproject.Controller;

import com.Secuirty.secuirtyproject.Dto.JwtAuthenticationResponse;
import com.Secuirty.secuirtyproject.Dto.RefreshTokenRequest;
import com.Secuirty.secuirtyproject.Dto.SignUpRequest;
import com.Secuirty.secuirtyproject.Dto.SigninRequest;
import com.Secuirty.secuirtyproject.Services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest){
        return ResponseEntity.ok(authService.signIn(signinRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse>refresh (@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authService.RefreshToken(refreshTokenRequest));
    }
    @ExceptionHandler(BindException.class)
    public ResponseEntity<HashMap<String ,List<String>>> handleBindException(BindException e){
        List<String> errors = e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        HashMap<String ,List<String>> errorMap = new HashMap<>();
        errorMap.put("errors", errors);
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
