package com.lxisoft.appraisal.web.rest;

import com.lxisoft.appraisal.security.jwt.JWTFilter;
import com.lxisoft.appraisal.security.jwt.TokenProvider;
import com.lxisoft.appraisal.web.rest.vm.LoginVM;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import com.lxisoft.appraisal.service.UserService;
import com.lxisoft.appraisal.domain.User;
import java.util.*;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {

	private final Logger log = LoggerFactory.getLogger(UserJWTController.class);
    private final TokenProvider tokenProvider;
    
    private final UserService userserv;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public UserJWTController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder,UserService userserv) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userserv=userserv;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM) {

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }
    @GetMapping("/auth")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void authenticate() {
      // we don't have to do anything here
      // this is just a secure endpoint and the JWTFilter
      // validates the token
      // this service is called at startup of the app to check
      // if the jwt token is still valid
    }
    @GetMapping("/getAuth")
    public Authentication getAuth(@Valid @RequestBody String token)
    {
    	
    	Authentication auth=tokenProvider.getAuthentication(token);
    	System.out.println("auth "+auth);
    	return auth;
    }
    @GetMapping("/getuser")
    public User getUser(@Valid @RequestBody String username)
    {
    	Optional<User> user=userserv.getUserWithAuthoritiesByLogin(username);
    	return user.get();
    	
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
