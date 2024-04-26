package kz.edu.astanait.qarzhytracker.converter;

import org.springframework.stereotype.Service;

@Service
public class TrimmedTextConverter implements ColumnDataConverter<String> {

    @Override
    public String convert(final String data) {
        return data.trim();
    }
}
