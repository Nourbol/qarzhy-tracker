package kz.edu.astanait.qarzhytracker.converter;

public class TrimmedTextConverter implements ColumnDataConverter<String> {
    @Override
    public String convert(final String data) {
        return data.trim();
    }
}
