package com.gomsk.project.api.service;

import com.gomsk.project.api.dto.AuthUser;
import com.gomsk.project.api.dto.NotificationCreateReq;
import com.gomsk.project.core.domain.entity.Schedule;
import com.gomsk.project.core.domain.entity.User;
import com.gomsk.project.core.domain.entity.repository.ScheduleRepository;
import com.gomsk.project.core.service.UserService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.apache.juli.logging.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;


    @Transactional
    public void create(NotificationCreateReq notificationCreateReq, AuthUser authUser) {
        final User user = userService.findByUserId(authUser.getId());
        System.out.println("authUser :" + authUser.toString());
        System.out.println("notificationCreateReq :" + notificationCreateReq.toString());
        System.out.println("user :" + user.toString());
        final List<LocalDateTime> notifyAtList =  notificationCreateReq.getRepeatTimes();
        System.out.println("notifyAtList :" + notifyAtList.toString());
        notifyAtList.forEach(notifyAt -> {
            final Schedule notificationSchedule =
                    Schedule.notification(
                            notificationCreateReq.getTitle(),
                            notifyAt,
                            user
                    );
            scheduleRepository.save(notificationSchedule);
        });


    }
}
