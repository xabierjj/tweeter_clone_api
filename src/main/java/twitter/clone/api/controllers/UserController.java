package twitter.clone.api.controllers;

import java.security.Principal;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import twitter.clone.api.dto.IUser;
import twitter.clone.api.dto.RegisterDto;
import twitter.clone.api.dto.UserInfoDto;
import twitter.clone.api.models.FollowersModel;
import twitter.clone.api.models.UserModel;
import twitter.clone.api.services.UserService;

@RestController
@RequestMapping("/api/user")

public class UserController {
    
@Autowired
UserService  userService;
    
    @PostMapping("/register")
    public ResponseEntity<UserInfoDto> saveUser(@RequestBody RegisterDto user ) throws Exception{
       
        UserInfoDto userInfo = userService.saveUser(user.getUsername(), user.getPassword(), user.getMail());  
        return new ResponseEntity<>(userInfo,HttpStatus.ACCEPTED);
        
    }


    @GetMapping("/follow")
    public ResponseEntity<UserInfoDto> followUser(@RequestParam("id") Long id, Principal principal)throws Exception {
        UserInfoDto userInfo =userService.followUser(id,  principal.getName());
        return new ResponseEntity<>(userInfo,HttpStatus.ACCEPTED); 
    }

    @GetMapping("/followed/{offset}")
    public ResponseEntity<List<IUser> > getFollowedUsers( @PathVariable("offset") int offset, Principal principal)throws Exception {

        List<IUser> followedUsers = userService.getFollowedUsers(principal.getName(), offset);
     return  new ResponseEntity<>(followedUsers,HttpStatus.ACCEPTED); 
    }
    @GetMapping("/followers/{offset}")
    public ResponseEntity<List<IUser>> getFollowers(@PathVariable("offset") int offset, Principal principal)throws Exception {

        
        List<IUser> followedUsers = userService.getFollowers(principal.getName(), offset);
        return  new ResponseEntity<>(followedUsers,HttpStatus.ACCEPTED); 
    }


    @GetMapping()
    public ResponseEntity<UserInfoDto> getUser(@RequestParam("id") Long id, Principal principal)throws Exception {
        
        UserInfoDto userInfo =userService.getUser(id);
        return new ResponseEntity<>(userInfo,HttpStatus.ACCEPTED); 
    }

    @GetMapping("/search")
    public ResponseEntity<List<IUser>> getUser(@RequestParam("t") String term)throws Exception {
        
        List<IUser> users = userService.searchUser(term);
        return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
        // return new ResponseEntity<>(userInfo,HttpStatus.ACCEPTED); 
    }

    //Para conseguir el nombre del usuario autenticado usar Principal
    @GetMapping("/prueba")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String authToken(Principal principal) {
        return principal.getName();
    }
      
}
