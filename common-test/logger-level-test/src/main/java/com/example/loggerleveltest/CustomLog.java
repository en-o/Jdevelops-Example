package com.example.loggerleveltest;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;

public class CustomLog extends RollingFileAppender<ILoggingEvent> {
    @Override
    protected void append(ILoggingEvent event) {
        if(event.getLoggerName().equals("APILOG")){
            super.append(event);
        }
    }
}
