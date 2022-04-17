package com.lastik.back.repo;

import com.lastik.back.pojo.Lastik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LastikRepo  extends JpaRepository<Lastik, Long> {

}
