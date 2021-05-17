package twitter.clone.api.models;



import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tweet")
@JsonIgnoreProperties({ "retweeted" })
public class TweetModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true,nullable=false)
    private Long id;

    @Column(length = 140, nullable = false)
    private String content;


    @Column(name="creation_date")
    private Long created; 
    
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserModel user;

    @LazyCollection(LazyCollectionOption.EXTRA) 
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name="retweet",
        joinColumns = @JoinColumn(name="tweet_id"),
        inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private List<UserModel> retweeted;

    private  int retweetNumber;

    public TweetModel() {
     
        this.created = System.currentTimeMillis();
    }
    public TweetModel(String content) {
        this.created = System.currentTimeMillis();
        this.content = content;
    }

    public int getRetweetNumber() {
        return this.retweetNumber;
    }

    public void setRetweetNumber(int retweetNumber){
        this.retweetNumber=retweetNumber;
    }
    public List<UserModel> getRetweeted() {
        return this.retweeted;
    }

    public void setRetweeted(List<UserModel> retweeted) {
        this.retweeted = retweeted;
    }

    public void addRetweeted(UserModel user) {
        this.retweeted.add(user);
    }

    public void removeRetweeted(UserModel userRemove) {

        this.retweeted.remove(userRemove);
        
    }

  


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserModel getUser() {
        return this.user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

}
