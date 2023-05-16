package com.javatest.databaseTestApp.repository;

import com.javatest.databaseTestApp.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    List<UserInfo> findAllByIsDeletedIsFalse();

    Optional<UserInfo> findByIdAndIsDeletedIsFalse(Integer userDetailsId);
}
