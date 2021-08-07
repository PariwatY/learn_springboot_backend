package com.example.leraning.schedule;

import com.example.leraning.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserSchedule {

    private final UserService userService;

    public UserSchedule(UserService userService) {
        this.userService = userService;
    }

    // Schedule Note
    // Can use 0-23 for define cron
    // 1 => second
    // 2 => minute
    // 3 => hour
    // 4 => day
    // 5 => month
    // 6 => year

    //Every at 00 second (UTC TIME)
    @Scheduled(cron = "0 * * * * *")
    public void testEveryMinute(){
        log.info("Hello, What's up?");
    }

    //Every at 00:00 (UTC TIME)
    @Scheduled(cron = "0 0 0 * * *")
    public void testEverydayMidNight(){
        log.info("Hey yahh!");
    }
    //Every at 10:50 (Thai Time)
    @Scheduled(cron = "0 50 10 * * *",zone = "Asia/Bangkok")
    public void testEverydayNineAM(){
        log.info("Hey Pek");
    }
}
