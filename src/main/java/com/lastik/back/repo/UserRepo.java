package com.lastik.back.repo;

import com.lastik.back.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
