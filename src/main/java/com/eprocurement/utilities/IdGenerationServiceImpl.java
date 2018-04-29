package com.eprocurement.utilities;

import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * IdGenerationServiceImpl
 */
@Transactional
@Service
public class IdGenerationServiceImpl  implements IdGenerationService{

	@Override
	public String createId(Calendar now,String count) {
        String id =Integer.toString(now.get(Calendar.YEAR))+"-";
        for(int i=count.length();i < 4;i++){
            id += "0";
        }
        id += count;
        return id;
	}





    
}