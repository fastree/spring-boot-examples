package com.fastree.spring.boot.scheduler.service.impl;

import com.fastree.spring.boot.scheduler.job.SampleJob;
import com.fastree.spring.boot.scheduler.service.TaskService;
import org.quartz.*;
import org.quartz.core.jmx.JobDataMapSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private SchedulerFactoryBean schedulerFactory;

    @Override
    public void taskStart(String name) {
        JobDetail job = null;
        Trigger trigger = null;

        try {
            job = schedulerFactory.getScheduler().getJobDetail(new JobKey("job1", "group1"));
            if (!Objects.nonNull(job)) {
                // define the job and tie it to our HelloJob class
                job = newJob(SampleJob.class)
                        .withIdentity("job1", "group1")
                        .build();
            }

            trigger = schedulerFactory.getScheduler().getTrigger(new TriggerKey("trigger1", "group1"));
            if (!Objects.nonNull(trigger)) {
                // Trigger the job to run now, and then repeat every 40 seconds
                trigger = newTrigger()
                        .withIdentity("trigger1", "group1")
                        .startNow()
                        .withSchedule(simpleSchedule()
                                .withIntervalInSeconds(10)
                                .repeatForever()
                                .withMisfireHandlingInstructionNextWithExistingCount())

                        .build();
            }

            JobDataMap jobDataMap = JobDataMapSupport.newJobDataMap(new HashMap<String, Object>() {{
                put("name", name);
            }});

            // 用指定的JobKey从数据库中恢复任务
            schedulerFactory.getScheduler().resumeJob(new JobKey("job1", "group1"));

            // 首次执行并持久化job和trigger到数据库中
            // schedulerFactory.getScheduler().scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void taskStop() {
        try {
            schedulerFactory.getScheduler().pauseJob(JobKey.jobKey("job1", "group1"));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
