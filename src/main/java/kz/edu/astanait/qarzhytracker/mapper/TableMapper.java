package kz.edu.astanait.qarzhytracker.mapper;

import kz.edu.astanait.qarzhytracker.annotation.TableColumn;
import kz.edu.astanait.qarzhytracker.domain.TableRow;
import kz.edu.astanait.qarzhytracker.util.ConstructorUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import java.lang.reflect.Parameter;

@Component
public class TableMapper {

    public <T> T mapToObject(final TableRow row, final Class<T> type) {
        var constructorWithAnnotatedParameters = ConstructorUtil.findConstructorWithAnnotatedParameters(type, TableColumn.class);
        return ConstructorUtil.createInstance(constructorWithAnnotatedParameters, parameter -> mapToObject(row, parameter));
    }

    @SneakyThrows
    public Object mapToObject(final TableRow row, final Parameter parameter) {
        var annotation = parameter.getAnnotation(TableColumn.class);
        var cellData = row.getTextFromColumn(annotation.columnIndex());
        var converterConstructor = ConstructorUtil.getNoArgsConstructor(annotation.converter());
        return converterConstructor.newInstance().convert(cellData);
    }
}
