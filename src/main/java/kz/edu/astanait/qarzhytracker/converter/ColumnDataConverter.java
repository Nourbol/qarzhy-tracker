package kz.edu.astanait.qarzhytracker.converter;

public interface ColumnDataConverter<T> {

    T convert(String data);
}
