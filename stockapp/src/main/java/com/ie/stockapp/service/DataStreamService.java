package com.ie.stockapp.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DataStreamService {

    @Scheduled(cron = "*/5 * * * * ?")
    public void read() {
        //System.out.println("Ben calisiyorum");
    }
}
