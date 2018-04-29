package com.eprocurement.utilities;

import java.util.Calendar;

import org.springframework.stereotype.Component;

/**
 * IdGenerationServiceImpl
 */
@Component
public class IdGenerator {

    public String createId(Calendar now, String count) {
        String id = Integer.toString(now.get(Calendar.YEAR)) + "-";
        for (int i = count.length(); i < 4; i++) {
            id += "0";
        }
        id += count;
        return id;
    }

}