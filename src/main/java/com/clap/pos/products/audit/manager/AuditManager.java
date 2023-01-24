package com.clap.pos.products.audit.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AuditManager {
    private static final Logger logger = LoggerFactory.getLogger(AuditManager.class);
    private static ExecutorService executor = new ThreadPoolExecutor(1, 20, 1L, TimeUnit.MINUTES,
        new LinkedBlockingQueue<>(), runnable -> {
            var newThread = new Thread(runnable);
            newThread.setName("Audit-" + System.nanoTime());
            return newThread;
        });

    private AuditManager() {
    }

    public static void audit(@NotNull AspectAuditRunnable auditRunnable) {
        try {
            executor.execute(auditRunnable);
        } catch (Exception e) {
            logger.error("error al tratar de auditar", e);
        }
    }
}
