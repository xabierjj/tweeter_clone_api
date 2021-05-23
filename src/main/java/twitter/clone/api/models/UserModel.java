package twitter.clone.api.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames =  "username"),
    @UniqueConstraint(columnNames =  "mail")
})
@JsonIgnoreProperties({ "password", "follows" , "followers", "roles" })
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //default nullable is true
    @Column(unique = true,nullable = false)
    private Long id;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(unique = true,nullable = false)
    private String mail;
    @Column(nullable = false)
    private String password;

    //Tiene que ser eager para cargar los user details
    @ManyToMany(fetch = FetchType.EAGER )
    @JoinTable( name = "user_roles",
        joinColumns = @JoinColumn( name="user_id"),
        inverseJoinColumns = @JoinColumn( name="role_id")
        )
    private Set<RoleModel> roles = new HashSet<>();


    @OneToMany( mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE  )
    private List<TweetModel> tweets = new ArrayList<>();

    // ,orphanRemoval = true

    @LazyCollection(LazyCollectionOption.EXTRA) 

    @OneToMany( mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<FollowersModel> follows = new HashSet<>();
    
    @LazyCollection(LazyCollectionOption.EXTRA) 
    @OneToMany( mappedBy = "follows", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<FollowersModel> followers = new HashSet<>();


    @ManyToMany(mappedBy = "retweeted", fetch = FetchType.LAZY)
    private List<TweetModel> retweets = new ArrayList<>();

    public UserModel(String username, String password, String mail,Set<RoleModel> roles) {
        this.username =username;
        this.password =password;
        this.mail =mail;
        this.roles =roles;

    }

    public UserModel() {
        
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

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleModel> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<RoleModel> roles) {
        this.roles = roles;
    }

    public Set<FollowersModel> getFollows() {
        return this.follows;
    }

    public void setFollows(Set<FollowersModel> follows) {
        this.follows = follows;
    }

    public Set<FollowersModel> getFollowers() {
        return this.followers;
    }

    public void setFollowers(Set<FollowersModel> followers) {
        this.followers = followers;
    }

    

}

  