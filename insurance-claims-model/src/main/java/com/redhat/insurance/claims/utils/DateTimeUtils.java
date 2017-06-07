package com.redhat.insurance.claims.utils;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    private static Clock CLOCK = Clock.systemDefaultZone();
    private static ZoneId ZONE_ID = ZoneId.systemDefault();
    private static DateTimeFormatter DAY_MONTH_YEAR = DateTimeFormatter.ofPattern( "MM-dd-yyyy" );

    public static LocalDate dayMonthYear( String date ) {
        return LocalDate.parse( date, DAY_MONTH_YEAR );
    }

    public static LocalDate dayMonthYearLocalDate( String date ) {
        return LocalDate.parse( date, DAY_MONTH_YEAR );
    }

    public static LocalDateTime now() {
        return LocalDateTime.now( CLOCK );
    }

    public static void useFixedClockAtDate( LocalDate date ) {
        CLOCK = Clock.fixed( date.atStartOfDay().atZone( ZONE_ID ).toInstant(), ZONE_ID );
    }
}