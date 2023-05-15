package com.javatest.databaseTestApp.repository;

import com.javatest.databaseTestApp.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

    List<UserDetails> findAllByIsDeletedIsFalse();

    Optional<UserDetails> findByUserIdAndIsDeletedIsFalse(Integer userId);
}
