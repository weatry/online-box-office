package com.github.budwing.obo.schedule.service;

import com.github.budwing.obo.schedule.entity.Schedule;

public interface ScheduleService {
    Schedule changeStatus(String scheduleId, Schedule.Status status);
}
