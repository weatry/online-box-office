package com.github.budwing.obo.idcenter.repository;

import com.github.budwing.obo.idcenter.entity.GlobalID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GlobalIDRepository extends JpaRepository<GlobalID, Integer> {
}
