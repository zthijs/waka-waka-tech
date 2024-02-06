package nl.zthijs.iprwc.dao;

import lombok.AllArgsConstructor;
import nl.zthijs.iprwc.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class RoleDAO {

    private final RoleRepository roleRepository;

    public Optional<Role> getById(String id){
        return this.roleRepository.findById(id);
    }

    public long count(){
        return this.roleRepository.count();
    }

    public Role save(Role role){
        return this.roleRepository.save(role);
    }

}
