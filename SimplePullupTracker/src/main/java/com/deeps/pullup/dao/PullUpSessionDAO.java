package com.deeps.pullup.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deeps.pullup.entity.PullUpSessionEntity;

@Repository
public interface PullUpSessionDAO extends JpaRepository<PullUpSessionEntity , Long>{

    List<PullUpSessionEntity> findByUserId(Long userId);
}
