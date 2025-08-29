package ru.aidar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aidar.entity.UserCredentional;

public interface UserCredentionalRepository extends JpaRepository<UserCredentional, Long> {

}
