package com.mobile.mobile.dao;

import com.mobile.mobile.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberDao extends JpaRepository<PhoneNumber,String> {
}
