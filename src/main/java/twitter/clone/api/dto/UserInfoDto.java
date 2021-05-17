package twitter.clone.api.dto;

import java.io.Serializable;

public class UserInfoDto implements Serializable {
    
    private Long id;
    private String username;
    private int followerNumber;
    private int followedNumber;

    public UserInfoDto() {
    }


    public UserInfoDto(Long id, String username, int followerNumber, int followedNumber) {
        this.id = id;
        this.username = username;
        this.followerNumber = followerNumber;
        this.followedNumber = followedNumber;
    }
    public UserInfoDto(Long id, String username) {
        this.id = id;
        this.username = username;
       
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFollowerNumber() {
        return this.followerNumber;
    }

    public void setFollowerNumber(int followerNumber) {
        this.followerNumber = followerNumber;
    }

    public int getFollowedNumber() {
        return this.followedNumber;
    }

    public void setFollowedNumber(int followedNumber) {
        this.followedNumber = followedNumber;
    }


}
