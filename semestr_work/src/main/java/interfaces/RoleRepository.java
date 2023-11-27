package interfaces;

public interface RoleRepository {
    String findByUserId(long userId);
}
