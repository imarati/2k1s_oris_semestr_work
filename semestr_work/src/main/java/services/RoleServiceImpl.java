package services;

import interfaces.RoleRepository;
import repositories.RoleRepositoryImpl;

import javax.sql.DataSource;

public class RoleServiceImpl implements interfaces.RoleService {
    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public String getRole(long userId) {
        return roleRepository.findByUserId(userId);
    }
}
