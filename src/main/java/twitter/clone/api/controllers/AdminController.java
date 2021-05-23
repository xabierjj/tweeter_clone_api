package twitter.clone.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import twitter.clone.api.dto.IUser;
import twitter.clone.api.dto.RegisterDto;
import twitter.clone.api.dto.UserInfoDto;
import twitter.clone.api.services.TweetService;
import twitter.clone.api.services.UserService;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    TweetService tweetService;

    @DeleteMapping("/tweet")
    public ResponseEntity<Void> deleteTweet(@RequestParam("id") Long tweetId) throws Exception {

        tweetService.deleteTweet(tweetId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @PutMapping("/user")
    public ResponseEntity<UserInfoDto> updateUser( @RequestBody UserInfoDto userUpdate) throws Exception {

        UserInfoDto userInfo = userService.updateUser( userUpdate);
        return new  ResponseEntity<>(userInfo,HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/user")
    public ResponseEntity<Void> deleteUser(@RequestParam("id") Long userId) throws Exception {

        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/user")
    public ResponseEntity<UserInfoDto> addUser(@RequestBody RegisterDto user) throws Exception {

        UserInfoDto userInfo = userService.saveUser(user.getUsername(), user.getPassword(), user.getMail());  
        return new ResponseEntity<>(userInfo,HttpStatus.ACCEPTED);
       
    }

    @GetMapping("/users")

    public ResponseEntity<List<IUser> > getUsers(@RequestParam("offset") int offset)throws Exception {
       
        List<IUser> users= userService.getUsers(offset);
        return new ResponseEntity<>(users,HttpStatus.ACCEPTED);
    }


}
