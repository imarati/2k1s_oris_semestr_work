package interfaces;

import models.User;

import java.util.List;

public interface RoleRepository {
    String findByUserId(long userId);
    List<User> findByRoleId(long roleId);
}
