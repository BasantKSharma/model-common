package com.gogo.model.common.domain.util;

import java.util.concurrent.TimeUnit;

public final class ThreadUtil {

    public static void sleep(TimeUnit timeUnit, int duration) {
        LogUtil.logInfo("Sleeping for duration [" + duration + " " + timeUnit.name() + "]");
        try {
            if (timeUnit.equals(TimeUnit.SECONDS)) {
                Thread.sleep(TimeUnit.SECONDS.toSeconds(duration));
            } else if (timeUnit.equals(TimeUnit.MILLISECONDS)) {
                Thread.sleep(TimeUnit.MILLISECONDS.toSeconds(duration));
            } else if (timeUnit.equals(TimeUnit.MINUTES)) {
                Thread.sleep(TimeUnit.MINUTES.toSeconds(duration));
            } else {
                throw new RuntimeException("Unsupported timeunit: " + timeUnit);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
