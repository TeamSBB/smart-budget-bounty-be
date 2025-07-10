package com.smartbudgetbounty.util;

import java.time.Instant;
import java.time.ZoneOffset;

public class DateTimeUtil {

    /**
     * Strips the time from an Instant and returns the Instant at start of day in UTC.
     *
     * @param instant the original Instant
     * @return Instant at start of day (00:00) in UTC
     */
    public static Instant stripTimeToStartOfDayUTC(Instant instant) {
        if (instant == null) {
            throw new IllegalArgumentException("Instant must not be null");
        }

        return instant.atZone(ZoneOffset.UTC)
                .toLocalDate()
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant();
    }

    public static boolean isDateTimeReached(Instant targetDate) {
        return Instant.now().compareTo(targetDate) >= 0;
    }
}


