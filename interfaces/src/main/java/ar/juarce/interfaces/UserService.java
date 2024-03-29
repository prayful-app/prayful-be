package ar.juarce.interfaces;

import ar.juarce.models.User;

import java.util.Optional;

public interface UserService extends CrudOperations<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> getCurrentUser();

}
