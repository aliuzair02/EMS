package org.template.common.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;

public class BaseLog {

    protected static final Logger log = LoggerFactory.getLogger(BaseLog.class);
    public static long logId;

    public static void generateLogId() {
        SecureRandom random = new SecureRandom();
        logId = 1_000_000_000L + random.nextLong(9_000_000_000L);
    }

    public static void infoLog(String message) {
        log.info("[{}] - {}", logId, message);
    }

    public static void errorLog(String message) {
        log.error("[{}] - {}", logId, message);
    }

}
