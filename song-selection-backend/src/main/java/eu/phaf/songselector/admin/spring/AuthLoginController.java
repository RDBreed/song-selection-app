package eu.phaf.songselector.admin.spring;

import eu.phaf.songselector.admin.jwt.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Collections.singletonList;

@RestController
public class AuthLoginController {
  public static final String ACCESS_TOKEN = "accessToken";
  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final JwtTokenUtil jwtTokenUtil;
  private final int cookieExpiry;

  public AuthLoginController(AuthenticationManager authenticationManager,
                             UserDetailsService userDetailsService,
                             JwtTokenUtil jwtTokenUtil,
                             @Value("${jwt.cookieExpiry}") int cookieExpiry) {
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtTokenUtil = jwtTokenUtil;
    this.cookieExpiry = cookieExpiry;
  }

  @PostMapping("/auth/login")
  public ResponseEntity<Void> login(@RequestBody Credentials credentials) {
    Authentication authenticate = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password())
    );
    if (authenticate.isAuthenticated()) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.username());
      String accessToken = jwtTokenUtil.generateToken(userDetails.getUsername());
      // set accessToken to cookie header
      ResponseCookie cookie = ResponseCookie.from(ACCESS_TOKEN, accessToken)
        .httpOnly(true)
//        .secure(true)
        .path("/")
        .maxAge(cookieExpiry)
        .build();
      return ResponseEntity.ok()
        .headers(new HttpHeaders() {{
          put(HttpHeaders.SET_COOKIE, singletonList(cookie.toString()));
        }})
        .build();
    } else {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
  }

  @GetMapping("/auth/check-token")
  public ResponseEntity<Void> checkTokenValidity() {
    return ResponseEntity.ok().build();
  }

  @PostMapping("/auth/logout")
  public ResponseEntity<Void> logout() {
    ResponseCookie cookie = ResponseCookie.from(ACCESS_TOKEN, "")
      .httpOnly(true)
//      .secure(true)
      .path("/")
      .maxAge(cookieExpiry)
      .build();
    return ResponseEntity.ok()
      .headers(new HttpHeaders() {{
        put(HttpHeaders.SET_COOKIE, singletonList(cookie.toString()));
      }})
      .build();
  }

  public record Credentials(String username, String password) {
  }

  public record TokenResponse(String token) {
  }
}
