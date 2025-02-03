package com.semzy_ecommerce.soft.controller;

import com.semzy_ecommerce.soft.component.JwtUtil;
import com.semzy_ecommerce.soft.dao.RoleRepository;
import com.semzy_ecommerce.soft.dao.UserRepository;
import com.semzy_ecommerce.soft.dtos.ErrorResponse;
import com.semzy_ecommerce.soft.dtos.requests.AuthenticationRequest;
import com.semzy_ecommerce.soft.dtos.requests.RegisterRequest;
import com.semzy_ecommerce.soft.dtos.response.AuthenticationResponse;
import com.semzy_ecommerce.soft.dtos.response.CustomResponse;
import com.semzy_ecommerce.soft.entity.Role;
import com.semzy_ecommerce.soft.entity.User;
import com.semzy_ecommerce.soft.service.CustomUserService;
import com.semzy_ecommerce.soft.service.PasswordResetService;
import com.semzy_ecommerce.soft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private PasswordResetService passwordResetService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {


        if (userRepository.existsByUsername(registerRequest.getName())) {
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    "Registration Error",
                    "Username is already taken!",
                    "/users/register"
            );
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        Role role = roleRepository.findRoleByName(registerRequest.getRole());
        user.setUsername(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.addRoles(role);

        userService.registerUser(user);

        CustomResponse customResponse =
                new CustomResponse(true, "User Registered Successfully", null);

        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody
                                                       AuthenticationRequest authenticationRequest)
            throws Exception {

        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName()
                        , authenticationRequest.getPassword())
        );
        final User user = userRepository.findByUsername(authenticationRequest.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        // Load user details
        final UserDetails userDetails = customUserService.
                loadUserByUsername(authenticationRequest.getUserName());

        // Generate JWT token
        final String jwt = jwtUtil.generateToken(userDetails);

        // Return the token
        return ResponseEntity.ok(new AuthenticationResponse(user.getId(), jwt));
    }

    @PostMapping("/forgotPassword")
    public String requestPasswordReset(@RequestParam String email) {
        passwordResetService.requestPasswordReset(email);
        return "Password reset email sent";
    }



}
