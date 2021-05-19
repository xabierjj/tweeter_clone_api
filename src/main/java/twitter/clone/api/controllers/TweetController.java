package twitter.clone.api.controllers;

import java.security.Principal;
import java.util.List;
import org.apache.catalina.connector.Response;
import org.hibernate.annotations.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import twitter.clone.api.models.TweetModel;
import twitter.clone.api.services.TweetService;
import twitter.clone.api.services.UserService;
import twitter.clone.api.dto.ITweet;
import twitter.clone.api.dto.TweetDto;
import twitter.clone.api.dto.TweetRegisterDto;
@RestController

@RequestMapping("/api/tweet")
public class TweetController {

    @Autowired
    TweetService tweetService;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<TweetDto> saveTweet(@RequestBody TweetRegisterDto tweetRegister, Principal principal) throws Exception {
        System.out.println("tweetRegister.getContent()");
        System.out.println(tweetRegister.getContent());
        TweetModel tweet = tweetService.saveTweet(tweetRegister.getContent(), principal.getName());
        TweetDto tweetDto = new TweetDto(tweet.getId(),tweet.getContent(), tweet.getCreated(), tweet.getUser().getUsername());
        return new ResponseEntity<>(tweetDto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/feed/{offset}")
    public ResponseEntity<List<ITweet>> getFeed(@PathVariable("offset") int offset , Principal principal) throws Exception {
       
        List<ITweet> feed =tweetService.getFeed(principal.getName(),offset);
        return new ResponseEntity<>(feed,HttpStatus.ACCEPTED);
         
    }

    @GetMapping("/retweet")

    public TweetModel makeRetweet(@RequestParam("id") Long tweetId, Principal principal) throws Exception {

        return tweetService.makeRetweet(tweetId, principal.getName());
    }

    @DeleteMapping("/retweet")

    public TweetModel deleteRetweet(@RequestParam("id") Long tweetId, Principal principal) throws Exception {

        return tweetService.deleteRetweet(tweetId, principal.getName());
    }
}
