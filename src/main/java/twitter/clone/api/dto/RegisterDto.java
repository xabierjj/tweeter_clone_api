package twitter.clone.api.dto;

import java.io.Serializable;

public class RegisterDto implements Serializable {
    private String username;
    private String password;
    private String mail;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    
}
