package com.gomsk.project.core.domain.entity.repository;


import com.gomsk.project.core.domain.entity.Engagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngagementRepository extends JpaRepository<Engagement,Long> {
}
