package com.gomsk.project.api.service;

import com.gomsk.project.api.dto.AuthUser;
import com.gomsk.project.api.dto.EngagementEmailStuff;
import com.gomsk.project.api.dto.EventCreateReq;
import com.gomsk.project.core.domain.RequestStatus;
import com.gomsk.project.core.domain.entity.Engagement;
import com.gomsk.project.core.domain.entity.Schedule;
import com.gomsk.project.core.domain.entity.User;
import com.gomsk.project.core.domain.entity.repository.EngagementRepository;
import com.gomsk.project.core.domain.entity.repository.ScheduleRepository;
import com.gomsk.project.core.exception.CalendarException;
import com.gomsk.project.core.exception.ErrorCode;
import com.gomsk.project.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EmailService emailService;
    private final EngagementRepository engagementRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    @Transactional
    public void create(EventCreateReq eventCreateReq, AuthUser authUser) {
        // 이벤트 참여자의 다른 이벤트와 중복이 되면 안된다.
        // 1~2까지 회의가 있는데, 추가로 다른 회의를 등록할 수 없음.
        // 추가로 이메일 발송..?

        final List<Engagement> engagementList = engagementRepository.findAll(); // TODO findAll 개선..!
        if(engagementList.stream()
                .anyMatch(e -> eventCreateReq.getAttendeeIds().contains(e.getAttendee().getId())
                && e.getRequestStatus() == RequestStatus.ACCEPTED
                && e.getEvent().isOverlapped(eventCreateReq.getStartAt(), eventCreateReq.getEndAt()))
        ){
            throw new CalendarException(ErrorCode.EVENT_CREATE_OVERLAPPED_PERIOD);
        }
        final Schedule evnetSchedule = Schedule.event(
                eventCreateReq.getTitle(),
                eventCreateReq.getDescription(),
                eventCreateReq.getStartAt(),
                eventCreateReq.getEndAt(),
                userService.findByUserId(authUser.getId())
        );
        scheduleRepository.save(evnetSchedule);
        final List<User> attendees = eventCreateReq.getAttendeeIds().stream()
                .map(userService::findByUserId)
                .collect(Collectors.toList());
        attendees.forEach(attendee -> {
                    final Engagement engagement = Engagement.builder()
                            .schedule(evnetSchedule)
                            .requestStatus(RequestStatus.REQUESTED)
                            .attendee(attendee)
                            .build();
                    engagementRepository.save(engagement);
                    emailService.sendEngagement(EngagementEmailStuff.builder()
                                    .engagementId(engagement.getId())
                                    .title(engagement.getEvent().getTitle())
                                    .toEmail(engagement.getAttendee().getEmail())
                                    .attendeeEmails(attendees.stream()
                                            .map(User::getEmail)
                                            .collect(Collectors.toList()))
                                    .period(engagement.getEvent().getPeriod())
                            .build());
                });
    }
}
