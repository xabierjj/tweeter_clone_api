package twitter.clone.api.dto;

import java.io.Serializable;

public class TweetRegisterDto implements Serializable {
    private String content;

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
