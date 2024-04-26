package kz.edu.astanait.qarzhytracker.converter;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class DdMmYyyyConverter implements ColumnDataConverter<LocalDate> {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public LocalDate convert(final String date) {
        return LocalDate.parse(date, DATE_TIME_FORMATTER);
    }
}
