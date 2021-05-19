package twitter.clone.api.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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
    UserDetailService  userDetailService;

    @Autowired
    JwtService JwtService;

    @Autowired
    AuthService authService;

    @PostMapping("/authenticate")
    public AuthDto authenticate(@RequestBody LoginDto loginDto)  throws Exception{
       
        UserModel user = authService.authenticate(loginDto.getUsername(), loginDto.getPassword());
        Long id = user.getId();
        UserDetails userDetails= userDetailService.loadUserByUsername(loginDto.getUsername());
        String token = JwtService.generateToken(userDetails);
      
      
      
        AuthDto authResponse = new AuthDto(loginDto.getUsername(),token) ;
        return authResponse;
    }

    @GetMapping("/prueba")
    public String prueba()  throws Exception{
        return "token";
    }



    
    
    
}
