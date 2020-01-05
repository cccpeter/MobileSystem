package com.mobile.mobile.dao;

import com.mobile.mobile.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserDao extends JpaRepository<User,String> {
}
