package com.mobile.mobile.dao;

import com.mobile.mobile.entity.number;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface numberDao extends JpaRepository<number,String> {
}
