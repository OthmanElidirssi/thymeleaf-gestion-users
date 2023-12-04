package com.example.thymleaf.repositories;

import com.example.thymleaf.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepositiry extends JpaRepository<User,Long> {
}
