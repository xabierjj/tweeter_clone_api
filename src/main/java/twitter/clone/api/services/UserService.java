package twitter.clone.api.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import twitter.clone.api.dto.IUser;
import twitter.clone.api.dto.UserInfoDto;
import twitter.clone.api.errors.UserNotFoundException;
import twitter.clone.api.models.ERole;
import twitter.clone.api.models.FollowersModel;
import twitter.clone.api.models.RoleModel;
import twitter.clone.api.models.UserModel;
import twitter.clone.api.repositories.FollowersRepository;
import twitter.clone.api.repositories.RoleRepository;
import twitter.clone.api.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    FollowersRepository followersRepository;

    public UserInfoDto saveUser(String username, String password, String mail) throws Exception {

        RoleModel role = roleRepository.findByRole(ERole.ROLE_USER).orElseThrow(() -> new Exception("Role not found"));
        Set<RoleModel> roles = new HashSet<>();
        roles.add(role);
        UserModel user = new UserModel(username, password, mail, roles);
        UserModel finaluser = userRepository.save(user);

        UserInfoDto userInfo =new UserInfoDto(user.getId(), user.getUsername(), user.getFollowers().size(),
        user.getFollows().size());
        return userInfo;
    }

    public UserInfoDto followUser(Long followId, String username) throws Exception {
        UserModel user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
        UserModel follow = userRepository.findById(followId).orElseThrow(() -> new UserNotFoundException());

        Long created = System.currentTimeMillis();
        FollowersModel follower = new FollowersModel(user, follow, created);
        followersRepository.save(follower);

        UserInfoDto userInfo = new UserInfoDto(user.getId(), user.getUsername(), user.getFollowers().size(),
                user.getFollows().size());

        return userInfo;
    }

    public UserInfoDto getUser(Long id) throws Exception {

        UserModel user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        UserInfoDto userInfo = new UserInfoDto(user.getId(), user.getUsername(), user.getFollowers().size(),
                user.getFollows().size());

        return userInfo;

    }

    public List<IUser> getFollowedUsers(String username, int offset) throws Exception {
        UserModel user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());

        return userRepository.getFollowedUsers(user.getId(), offset, 10);
    }

    public List<IUser> getFollowers(String username, int offset) throws Exception {
        UserModel user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());

        return userRepository.getFollowers(user.getId(), offset, 10);
    }


    public void deleteUser(Long userId) throws Exception  {
        UserModel user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        userRepository.delete(user);
    }
}
