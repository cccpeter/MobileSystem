package com.mobile.mobile.dao;

import com.mobile.mobile.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface rentDao extends JpaRepository<Rent,String> {
}
