package twitter.clone.api.repositories;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import twitter.clone.api.models.ERole;
import twitter.clone.api.models.RoleModel;
import twitter.clone.api.models.UserModel;

@Repository
public interface RoleRepository extends CrudRepository<RoleModel, Long> {

    public abstract  Optional<RoleModel> findByRole(ERole role);
    
	
}
