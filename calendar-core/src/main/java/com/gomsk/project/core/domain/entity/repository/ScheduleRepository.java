package com.gomsk.project.core.domain.entity.repository;

import com.gomsk.project.core.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
