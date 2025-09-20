package com.scm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.entities.Contact;
import com.scm.entities.User;

import java.util.List;


@Repository
public interface ContactRepo extends JpaRepository<Contact,String> {

    List<Contact> findByUser(User user);

    @Query("Select c from Contact c where c.user.id=:userId")
    List<Contact> findByUserId(@Param("userId") String userId);
}
