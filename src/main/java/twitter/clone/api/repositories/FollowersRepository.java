package twitter.clone.api.repositories;

import org.apache.catalina.mbeans.UserMBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import twitter.clone.api.models.UserModel;
import twitter.clone.api.models.FollowersModel;

@Repository
public interface FollowersRepository extends CrudRepository<FollowersModel,Long> {
    
    // public abstract FollowersModel findByUser();
    
}
