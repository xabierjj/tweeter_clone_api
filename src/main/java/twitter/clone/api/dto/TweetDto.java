package twitter.clone.api.dto;

import org.hibernate.type.SerializableType;
import java.io.Serializable;
public class TweetDto implements Serializable   {
    

    private Long user;
    private String content;
    private Long created;
    private String username;


    public TweetDto() {
    }

    public TweetDto(Long user, String content, Long created, String username) {
        this.user = user;
        this.content = content;
        this.created = created;
        this.username = username;
    }


    public Long getUser() {
        return this.user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreated() {
        return this.created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
