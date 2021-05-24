package twitter.clone.api.controllers;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import twitter.clone.api.dto.AuthDto;
import twitter.clone.api.dto.LoginDto;
import twitter.clone.api.models.UserModel;
import twitter.clone.api.services.AuthService;
import twitter.clone.api.services.UserDetailService;
import twitter.clone.api.services.JwtService;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    JwtService JwtService;

    @Autowired
    AuthService authService;

    @PostMapping("/authenticate")
    public AuthDto authenticate(@RequestBody LoginDto loginDto, HttpServletResponse response) throws Exception {

        UserModel user = authService.authenticate(loginDto.getUsername(), loginDto.getPassword());
        Long id = user.getId();
        UserDetails userDetails = userDetailService.loadUserByUsername(loginDto.getUsername());
        String token = JwtService.generateToken(userDetails);
        String refreshToken = JwtService.generateRefreshToken(user.getUsername());

        Cookie cookie = new Cookie("refresh", refreshToken);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/api/refreshtoken"); 

        response.addCookie(cookie);

        AuthDto authResponse = new AuthDto(loginDto.getUsername(), token);
        return authResponse;
    }

    @GetMapping("/refreshtoken")
    public ResponseEntity<AuthDto> refreshToken(@CookieValue("refresh") String refreshToken) throws Exception {
        

  
      
        String name = JwtService.extractUsername(refreshToken);
        UserDetails userDetails = userDetailService.loadUserByUsername(name);
        System.out.println("Antes de valdiar");
        if (JwtService.validateToken(refreshToken, userDetails)==false) {
            throw new Exception("Token is not valid");
        }
        System.out.println("Validado");

        String token = JwtService.generateToken(userDetails);
        AuthDto authResponse = new AuthDto(name, token);
        return new ResponseEntity<>(authResponse,HttpStatus.ACCEPTED ) ;
    }

    @GetMapping("/prueba")
    public String prueba() throws Exception {
        return "token";
    }

    @GetMapping("/logout")
    public ResponseEntity logout() throws Exception {
        Cookie cookie = new Cookie("refresh", null);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setPath("/api/refreshtoken"); 
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
