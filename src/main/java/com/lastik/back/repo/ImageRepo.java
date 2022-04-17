package com.lastik.back.repo;

import com.lastik.back.pojo.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
}
