package com.gomsk.project.core;


import com.gomsk.project.core.domain.ScheduleType;
import com.gomsk.project.core.domain.entity.Engagement;
import com.gomsk.project.core.domain.Event;
import com.gomsk.project.core.domain.RequestStatus;
import com.gomsk.project.core.domain.entity.Schedule;
import com.gomsk.project.core.domain.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainCreateTest {
    @Test
    void eventCreate(){
        User me = new User("name", "email", "pw", LocalDate.now());
        final Schedule taskSchedule = Schedule.task("할일", "청소하기", LocalDateTime.now(), me);
        assertEquals(taskSchedule.getScheduleType(), ScheduleType.TASK);
        assertEquals(taskSchedule.toTask().getTitle(), "할일");

    }
}
