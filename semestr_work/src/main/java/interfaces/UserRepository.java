package interfaces;

import models.User;

public interface UserRepository extends CrudRepository<User>{
    User findByEmail(String email);
    User findById(long id);
    void saveAdmin(User user);
}
