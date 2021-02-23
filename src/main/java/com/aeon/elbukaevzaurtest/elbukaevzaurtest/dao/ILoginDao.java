package com.aeon.elbukaevzaurtest.elbukaevzaurtest.dao;

import com.aeon.elbukaevzaurtest.elbukaevzaurtest.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoginDao extends JpaRepository<Login, Long> {
    Login findWithRoleByUsername(String username);
}
