package eu.phaf.songselector.admin.spring;

import eu.phaf.songselector.admin.jwt.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthLoginController {
  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final JwtTokenUtil jwtTokenUtil;

  public AuthLoginController(AuthenticationManager authenticationManager,
                             UserDetailsService userDetailsService,
                             JwtTokenUtil jwtTokenUtil) {
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @PostMapping("/auth/login")
  public ResponseEntity<TokenResponse> login(@RequestBody Credentials credentials) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password())
    );

    UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.username());
    String token = jwtTokenUtil.generateToken(userDetails.getUsername());

    return ResponseEntity.ok(new TokenResponse(token));
  }

  public record Credentials(String username, String password) {
  }

  public record TokenResponse(String token) {
  }
}
