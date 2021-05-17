package twitter.clone.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import twitter.clone.api.models.UserModel;
import twitter.clone.api.repositories.UserRepository;

@Service
public class AuthService {
    @Autowired
    UserRepository  userRepository;
    public UserModel authenticate(String username, String password) throws Exception {
        UserModel user = userRepository.findByUsername(username).orElseThrow( ()->new Exception("User not found"));
        if ( user.getPassword().equals(password)) {
            return user;
        } else {
            throw new Exception("Bad credentials");
        }
        
    }
}
