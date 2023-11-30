package services;

import interfaces.RoleRepository;
import models.User;
import repositories.RoleRepositoryImpl;

import javax.sql.DataSource;
import java.util.List;

public class RoleServiceImpl implements interfaces.RoleService {
    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public String getRole(long userId) {
        return roleRepository.findByUserId(userId);
    }

    @Override
    public List<User> getUsers(long roleId) {
        return roleRepository.findByRoleId(roleId);
    }
}
