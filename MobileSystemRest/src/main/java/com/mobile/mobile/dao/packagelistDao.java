package com.mobile.mobile.dao;

import com.mobile.mobile.entity.Packagelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface packagelistDao extends JpaRepository<Packagelist,String> {
}
