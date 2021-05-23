package twitter.clone.api.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@Table(name = "followers", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id", "follows" }))
public class FollowersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

   
    @ManyToOne
    @JoinColumn(name = "follows")
    private UserModel follows;

    @Column(name = "creation_date")
    private Long created;


    public FollowersModel() {
       
    };
    public FollowersModel(UserModel user, UserModel follows , Long created) {
        this.user =user;
        this.follows = follows;
        this.created = created;
    };


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public UserModel getUser() {
        return this.user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public UserModel getFollows() {
        return this.follows;
    }

    public void setFollows(UserModel follows) {
        this.follows = follows;
    }

    public Long getCreated() {
        return this.created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    
}
