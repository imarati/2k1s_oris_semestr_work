package repositories;

import models.User;

public interface UserRepository extends CrudRepository<User>{
    User findByEmailAndPassword(String email, String password);
}
