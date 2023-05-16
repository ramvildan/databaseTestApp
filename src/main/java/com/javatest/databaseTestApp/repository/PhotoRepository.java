package com.javatest.databaseTestApp.repository;

import com.javatest.databaseTestApp.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    Optional<Photo> findByUserInfoIdAndIsDeletedIsFalse(Integer userInfoId);
}
