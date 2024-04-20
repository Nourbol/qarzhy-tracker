package kz.edu.astanait.qarzhytracker.annotation;

import kz.edu.astanait.qarzhytracker.converter.ColumnDataConverter;
import kz.edu.astanait.qarzhytracker.converter.TrimmedTextConverter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface TableColumn {

    Class<? extends ColumnDataConverter<?>> converter() default TrimmedTextConverter.class;

    int columnIndex();
}
