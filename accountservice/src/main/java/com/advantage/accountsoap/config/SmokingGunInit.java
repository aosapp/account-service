package com.advantage.accountsoap.config;

import com.advantage.accountrest.api.AccountServiceController;
import com.advantage.accountsoap.init.DataSourceInitByCsv;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmokingGunInit {

    @Autowired
    DataSourceInitByCsv dataSourceInitByCsv;
    private boolean isDirty = false;
    private static final Logger logger = Logger.getLogger(SmokingGunInit.class);
    public void activate() throws Exception{
        logger.info("isDirty: " + isDirty);
        if(isDirty)
            return;
        try {
            logger.info("before dataSourceInitByCsv.init");
            dataSourceInitByCsv.init(true);
            logger.info("after dataSourceInitByCsv.init");
            isDirty = true;
        } catch (Exception e){
            logger.error(e);
            e.printStackTrace();
        }
    }


}
