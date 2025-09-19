package ru.aidar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aidar.entity.UserCredentional;

import java.util.Optional;

public interface UserCredentionalRepository extends JpaRepository<UserCredentional, Long> {

    Optional<UserCredentional> findByName(String name);

}
