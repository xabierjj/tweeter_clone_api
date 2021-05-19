package twitter.clone.api.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import twitter.clone.api.dto.IUser;
import twitter.clone.api.models.UserModel;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {

    public abstract  Optional<UserModel> findByUsername(String username);
    
	public abstract Boolean existsByUsername(String username);

	public abstract Boolean existsByMail(String email);

	@Query(value="SELECT U.id, U.username FROM followers AS F INNER JOIN users AS U ON F.follows = U.id WHERE F.user_id=?1  ORDER BY U.username LIMIT ?3 OFFSET ?2", nativeQuery = true)
    List<IUser> getFollowedUsers(Long userId, int offset, int limit);

	@Query(value="SELECT U.id, U.username FROM followers AS F INNER JOIN users AS U ON F.user_id = U.id WHERE F.follows=?1  ORDER BY U.username LIMIT ?3 OFFSET ?2", nativeQuery = true)
    List<IUser> getFollowers(Long userId, int offset, int limit);

	@Query(value = "SELECT U.id, U.username FROM users AS U  WHERE U.username LIKE %?1% LIMIT 10", nativeQuery = true)
    List<IUser> searchUser(String term);
}
