package com.mobile.mobile.dao;

import com.mobile.mobile.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface userDao extends JpaRepository<user,String> {
}
