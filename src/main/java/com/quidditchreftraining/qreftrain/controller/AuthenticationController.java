package com.quidditchreftraining.qreftrain.controller;

import com.quidditchreftraining.qreftrain.model.ApiResponse;
import com.quidditchreftraining.qreftrain.model.AuthToken;
import com.quidditchreftraining.qreftrain.model.LoginUser;
import com.quidditchreftraining.qreftrain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResponse<AuthToken> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        return new ApiResponse<>(200, "success", "");
    }

}
