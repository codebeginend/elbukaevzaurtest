package com.aeon.elbukaevzaurtest.elbukaevzaurtest.dao;

import com.aeon.elbukaevzaurtest.elbukaevzaurtest.entity.Login;
import com.aeon.elbukaevzaurtest.elbukaevzaurtest.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Long> {

    @Query("select p from Payments p where p.id = (select max(p1.id) from Payments p1 where p1.login_id = :login_id)")
    Payments findByLoginIdAndMaxDate(Long login_id);
}
