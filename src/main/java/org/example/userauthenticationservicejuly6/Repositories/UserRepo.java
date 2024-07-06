package org.example.userauthenticationservicejuly6.Repositories;

import org.example.userauthenticationservicejuly6.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    User save(User user);

    Optional<User> findById(Long id);
}
