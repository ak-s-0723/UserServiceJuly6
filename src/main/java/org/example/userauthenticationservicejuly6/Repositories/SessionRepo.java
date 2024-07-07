package org.example.userauthenticationservicejuly6.Repositories;

import org.example.userauthenticationservicejuly6.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepo extends JpaRepository<Session,Long> {

    Optional<Session> findByToken(String token);

    Session save(Session session);
}