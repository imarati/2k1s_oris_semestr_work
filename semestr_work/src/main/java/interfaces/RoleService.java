package interfaces;

import models.User;

import java.util.List;

public interface RoleService {
    String getRole(long userId);
    List<User> getUsers(long roleId);
}
