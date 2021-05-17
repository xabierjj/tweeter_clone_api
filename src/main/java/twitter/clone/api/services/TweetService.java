package twitter.clone.api.services;

import java.util.Optional;

import org.hibernate.annotations.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import twitter.clone.api.dto.ITweet;
import twitter.clone.api.dto.TweetDto;

import java.util.List;
import twitter.clone.api.models.TweetModel;
import twitter.clone.api.models.UserModel;
import twitter.clone.api.repositories.TweetRepository;
import twitter.clone.api.repositories.UserRepository;

@Service
public class TweetService {
    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    UserRepository userRepository;

    public TweetModel saveTweet(String content, String username) throws Exception {

        UserModel user = userRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found"));
        TweetModel tweet = new TweetModel(content);
        tweet.setUser(user);
        return (TweetModel) tweetRepository.save(tweet);
    }

    public Optional<TweetModel> getTweet(Long tweetId) {

        Optional<TweetModel> tweet = tweetRepository.findById(tweetId);
        return tweet;
    }

    public List<ITweet> getFeed(String username, int offset) throws Exception {
        UserModel user = userRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found"));
        offset = offset * 10;
        return tweetRepository.getFeed(user.getId(), offset, 10);
    }

    public TweetModel makeRetweet(Long tweetId, String username) throws Exception {
        UserModel user = userRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found"));
        TweetModel tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new Exception("Tweet not found"));

        tweet.addRetweeted(user);

        TweetModel result = tweetRepository.save(tweet);

        return result;

    }

    public TweetModel deleteRetweet(Long tweetId, String username) throws Exception {
        UserModel user = userRepository.findByUsername(username).orElseThrow(() -> new Exception("User not found"));
        TweetModel tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new Exception("Tweet not found"));

        tweet.removeRetweeted(user);

        TweetModel result = tweetRepository.save(tweet);

        return result;

    }

    public void deleteTweet( Long tweetId) throws Exception {
        TweetModel tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new Exception("Tweet not found"));

        tweetRepository.delete(tweet);

    }
}
