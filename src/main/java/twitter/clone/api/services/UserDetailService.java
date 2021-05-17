package twitter.clone.api.services;

import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Collection;
import twitter.clone.api.models.RoleModel;
import twitter.clone.api.models.UserModel;
import twitter.clone.api.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    
  
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        UserModel user= userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        
        Set<RoleModel> roles =user.getRoles();
        List<GrantedAuthority> authorities = new ArrayList();

        for (RoleModel role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoles().name()));
          
        }

      
        
        UserDetails userDetails = new User(user.getUsername(), user.getPassword(), authorities);
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
