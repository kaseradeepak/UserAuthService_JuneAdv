package org.example.userauthservice_june4.controllers;

import org.antlr.v4.runtime.misc.Pair;
import org.example.userauthservice_june4.dtos.LoginRequestDto;
import org.example.userauthservice_june4.dtos.SignupRequestDto;
import org.example.userauthservice_june4.dtos.UserDto;
import org.example.userauthservice_june4.models.Token;
import org.example.userauthservice_june4.models.User;
import org.example.userauthservice_june4.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IAuthService authService;

    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignupRequestDto signupRequestDto) {
       User user = authService.signup(signupRequestDto.getName(), signupRequestDto.getEmail(), signupRequestDto.getPassword(), signupRequestDto.getPhoneNumber());
       return from(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
      Token token = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
      return new ResponseEntity<>(token.getValue(), HttpStatus.OK);
    }

    @GetMapping("/validate/{tokenValue}")
    public UserDto validateToken(@PathVariable String tokenValue) {
        User user = authService.validateToken(tokenValue);
        return from(user);
    }

    private UserDto from(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
