package twitter.clone.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @DeleteMapping("/user")
    public ResponseEntity<Void> deleteUser(@RequestParam("id") Long userId) throws Exception {

        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
