package nl.zthijs.iprwc.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.zthijs.iprwc.config.JwtTokenUtil;
import nl.zthijs.iprwc.dao.RoleDAO;
import nl.zthijs.iprwc.dao.UserDAO;
import nl.zthijs.iprwc.entity.Role;
import nl.zthijs.iprwc.entity.User;
import nl.zthijs.iprwc.exceptions.*;
import nl.zthijs.iprwc.models.JwtResult;
import nl.zthijs.iprwc.models.LoginCredentials;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.passay.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final JwtTokenUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User getMe(@AuthenticationPrincipal String email) {
        return userDAO.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
    }


    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody @Valid User user) {

        if (!this.isValidPassword(user.getPassword())) {
            throw new UnmetPasswordRequirementsException();
        }

        Optional<User> requestedUser = userDAO.findByEmail(user.getEmail());
        if (requestedUser.isPresent()) {
            throw new UserAlreadyExistsException(user.getEmail());
        }


        Role basicUserRole = roleDAO.getById("USER").orElseThrow(BasicRoleNotFoundException::new);

        user.setRole(basicUserRole);


        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        User newUser = userDAO.save(user);
        String token = jwtUtil.generateToken(newUser);

        return Collections.singletonMap("token", token);

    }

    @PostMapping("/login")
    public JwtResult login(@RequestBody @Valid LoginCredentials body) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.email, body.password);

            authManager.authenticate(authInputToken);

            User requestedUser = userDAO.findByEmail(body.email).orElseThrow(ResourceNotFoundException::new);

            String token = jwtUtil.generateToken(requestedUser);
            JwtResult jwtResult = new JwtResult();

            jwtResult.token = token;


            return jwtResult;

        } catch (AuthenticationException authExc) {
            throw new ForbiddenException("Invalid Login Credentials");
        }
    }

    private boolean isValidPassword(String password) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new WhitespaceRule()));

        RuleResult result = validator.validate(new PasswordData(password));
        return result.isValid();
    }


}
