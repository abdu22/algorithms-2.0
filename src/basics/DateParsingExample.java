package basics;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateParsingExample {

    public static void main(String[] args) throws ParseException {

        // Parsing LocalDate
        String dateStr = "2022-02-28";
        LocalDate date = LocalDate.parse(dateStr);
        System.out.println("Parsed LocalDate: " + date);

        // Parsing LocalTime
        String timeStr = "12:34:56";
        LocalTime time = LocalTime.parse(timeStr);
        System.out.println("Parsed LocalTime: " + time);

        // Parsing LocalDateTime
        String dateTimeStr = "2022-02-28T12:34:56";
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);
        System.out.println("Parsed LocalDateTime: " + dateTime);

        // Parsing ZonedDateTime
        String zonedDateTimeStr = "2022-02-28T12:34:56-05:00[America/New_York]";
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(zonedDateTimeStr);
        System.out.println("Parsed ZonedDateTime: " + zonedDateTime);

        // Parsing with custom DateTimeFormatter
        String customDateTimeStr = "2022-02-28 12:34:56 PM";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        LocalDateTime customDateTime = LocalDateTime.parse(customDateTimeStr, formatter);
        System.out.println("Parsed custom LocalDateTime: " + customDateTime);


        // Parsing date with SimpleDateFormat
        String dateStr2 = "2022-02-28";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = dateFormat.parse(dateStr2);
        System.out.println("Parsed date: " + date2);

        // Parsing time with SimpleDateFormat
        String timeStr2 = "12:34:56";
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date time2 = timeFormat.parse(timeStr2);
        System.out.println("Parsed time: " + time2);

        // Parsing date-time with SimpleDateFormat
        String dateTimeStr2 = "2022-02-28 12:34:56";
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime2 = dateTimeFormat.parse(dateTimeStr2);
        System.out.println("Parsed date-time: " + dateTime2);
    }
}

