package com.example.demo.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.springframework.stereotype.Component;

/**
 * Created by MrDing
 * Date: 2017/8/13.
 * Time:12:53
 */
@Component
public class MyAppender extends AppenderBase<ILoggingEvent> {

    private  String location;

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        System.out.println(location);
        System.out.println(iLoggingEvent.getTimeStamp());
        System.out.println(iLoggingEvent.getThreadName());
    }



    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
