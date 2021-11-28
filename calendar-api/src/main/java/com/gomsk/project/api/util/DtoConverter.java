package com.gomsk.project.api.util;


import com.gomsk.project.api.dto.EventDto;
import com.gomsk.project.api.dto.NotificationDto;
import com.gomsk.project.api.dto.ScheduleDto;
import com.gomsk.project.api.dto.TaskDto;
import com.gomsk.project.core.domain.ScheduleType;
import com.gomsk.project.core.domain.entity.Schedule;
import com.gomsk.project.core.exception.CalendarException;
import com.gomsk.project.core.exception.ErrorCode;

// abstract - 생성자 노출안됨, static만 구성하고 싶을경우 사용
public abstract class DtoConverter {
    public static ScheduleDto fromSchedule(Schedule schedule){
        switch (schedule.getScheduleType()){
            case EVENT:
                return EventDto.builder()
                        .scheduleId(schedule.getId())
                        .description(schedule.getDescription())
                        .startAt(schedule.getStartAt())
                        .endAt(schedule.getEndAt())
                        .title(schedule.getTitle())
                        .writerId(schedule.getWriter().getId())
                        .build();
            case TASK:
                return TaskDto.builder()
                        .scheduleId(schedule.getId())
                        .title(schedule.getTitle())
                        .description(schedule.getDescription())
                        .taskAt(schedule.getStartAt())
                        .writerId(schedule.getWriter().getId())
                        .build();
            case NOTIFICATION:
                return NotificationDto.builder()
                        .scheduleId(schedule.getId())
                        .notifyAt(schedule.getStartAt())
                        .title(schedule.getTitle())
                        .writerId(schedule.getWriter().getId())
                        .build();
            default:
                throw new CalendarException(ErrorCode.BAD_REQUEST);
        }
    }
}
