package twitter.clone.api.repositories;

import org.springframework.stereotype.Repository;


import twitter.clone.api.models.TweetModel;
import twitter.clone.api.dto.ITweet;
import twitter.clone.api.dto.TweetDto;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface TweetRepository extends CrudRepository <TweetModel, Long>{
    

    @Query(value="SELECT T.id, T.user_id AS user, T.content AS content, T.creation_date  AS created, U.username AS username  FROM followers AS F INNER JOIN tweet AS T ON T.user_id=follows INNER JOIN users AS U ON U.id = T.user_id where F.user_id =?1 ORDER BY T.creation_date LIMIT ?3 OFFSET ?2", nativeQuery = true)
    List<ITweet> getFeed(Long userId, int offset, int limit);

}
