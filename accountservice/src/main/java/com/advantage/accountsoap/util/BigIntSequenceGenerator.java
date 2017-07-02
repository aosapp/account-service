package com.advantage.accountsoap.util;

import com.advantage.accountsoap.model.Account;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;

import org.hibernate.id.SequenceGenerator;

import java.io.Serializable;
import java.lang.annotation.Annotation;

/**
 * Created by kubany on 2/13/2017.
 */
public class BigIntSequenceGenerator extends SequenceGenerator {
    Session m_session;
    private static final Logger logger = Logger.getLogger(BigIntSequenceGenerator.class);

    @Override
    public Serializable generate(SessionImplementor sessionImplementor, Object obj) {
        this.m_session = (Session) sessionImplementor;
        long id = generateRandomIndex();
        return id;
    }

    private long generateRandomIndex() {

        for (int i = 0; i < 3; i++) {
            logger.info("attempt: " + i);
            long a = generate9DigitNumber();

            logger.info("index: " + String.valueOf(a));
            if (m_session.get(Account.class, a) == null) {
                logger.info("not found this id");
                return a;
            } else {
                logger.info("found this id");
            }
        }

        for (int i = 100000000; i < 999999999; i++) {
            logger.info("Is id free: " + i);
            if (m_session.get(Account.class, i) == null) {
                logger.info("id is free: " + i);
                return i;
            }
        }
        return 0L;
    }

    public long generate9DigitNumber()
    {
        long aNumber = (long) ((Math.random() * 900000000) + 100000000);
        return aNumber;
    }
}