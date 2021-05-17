package twitter.clone.api.dto;

public class AuthDto {
    private String username;
    private String token;


    public AuthDto() {
    }

    public AuthDto(String username, String token) {
        this.username = username;
        this.token = token;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
