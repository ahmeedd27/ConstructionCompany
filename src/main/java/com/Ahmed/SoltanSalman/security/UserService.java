package com.Ahmed.SoltanSalman.security;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final MongoTemplate temp;

    public void revokeAllTokens(String userId) {
        try {
            Query q = new Query();
            q.addCriteria(Criteria.where("userId").is(userId));
            List<Token> tokens = temp.find(q, Token.class);
            if (!tokens.isEmpty()) {
                BulkOperations bulkOps = temp.bulkOps(BulkOperations.BulkMode.UNORDERED, Token.class, "tokens");
                tokens.forEach(t -> {
                    t.setRevoked(true);
                    bulkOps.updateOne(
                            Query.query(Criteria.where("_id").is(t.get_id())),
                            new Update().set("isRevoked", true)
                    );
                });
                bulkOps.execute();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to revoke tokens", e);
        }
    }
    public ResponseEntity<String> registerAdmin(UserRequest userRequest) {
        if (userRepo.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new BadCredentialsException("Email already exists");
        }

        try {
            User user = User.builder()
                    .name(userRequest.getName())
                    .password(passwordEncoder.encode(userRequest.getPassword()))
                    .email(userRequest.getEmail())
                    .role(Role.ROLE_ADMIN) // Set admin role
                    .build();
            userRepo.save(user);
            String jwt = jwtService.generateToken(user);
            Token token = Token.builder()
                    .token(jwt)
                    .userId(user.get_id())
                    .isRevoked(false)
                    .build();
            temp.save(token , "tokens");


            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            throw new RuntimeException("Admin registration failed", e);
        }
    }



    public ResponseEntity<String> login(UserLogin userLogin) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword())
            );
            User user = (User) auth.getPrincipal();
            revokeAllTokens(user.get_id());

            Map<String, Object> claims = new HashMap<>();
            claims.put("name", user.getName());

            String jwt = jwtService.generateToken(claims, user);
            Token token = Token.builder()
                    .token(jwt)
                    .userId(user.get_id())
                    .isRevoked(false)
                    .build();
            temp.save(token , "tokens");

            return ResponseEntity.ok(jwt);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password");
        } catch (Exception e) {
            throw new RuntimeException("Login failed", e);
        }
    }
}
