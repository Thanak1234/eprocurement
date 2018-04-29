package com.eprocurement.utilities;

import java.util.Calendar;

/**
 * IdGenerationService
 */
public interface IdGenerationService {
    
    public String createId(Calendar now,String count);
}