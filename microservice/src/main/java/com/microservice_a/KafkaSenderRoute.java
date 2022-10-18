package com.microservice_a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class KafkaSenderRoute extends RouteBuilder{
    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;
    public void configure() throws Exception {
        from("timer:first-timer")
                .bean(getCurrentTimeBean)
                .log("${body}")
                .to("kafka:microservice");
    }
}
@Component
class GetCurrentTimeBean {
    public String getCurrentTime(){

        return "time now is " + LocalDateTime.now();
    }
}