package com.github.budwing.obo.schedule.service;

import com.github.budwing.obo.schedule.entity.Schedule;

public interface ScheduleService {
    void changeStatus(String scheduleId, Schedule.Status status);
}
