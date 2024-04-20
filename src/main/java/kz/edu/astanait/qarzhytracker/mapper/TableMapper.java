package kz.edu.astanait.qarzhytracker.mapper;

import kz.edu.astanait.qarzhytracker.annotation.TableColumn;
import kz.edu.astanait.qarzhytracker.util.ConstructorUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import technology.tabula.RectangularTextContainer;
import java.lang.reflect.Parameter;
import java.util.List;

@Component
public class TableMapper {

    public <T> T mapToObject(final List<RectangularTextContainer> row, final Class<T> type) {
        var constructorWithAnnotatedParameters = ConstructorUtil.findConstructorWithAnnotatedParameters(type, TableColumn.class);
        return ConstructorUtil.createInstance(constructorWithAnnotatedParameters, parameter -> mapToObject(row, parameter));
    }

    @SneakyThrows
    public Object mapToObject(final List<RectangularTextContainer> row, final Parameter parameter) {
        var annotation = parameter.getAnnotation(TableColumn.class);
        var cellData = row.get(annotation.columnIndex()).getText();
        var converterConstructor = ConstructorUtil.getNoArgsConstructor(annotation.converter());
        return converterConstructor.newInstance().convert(cellData);
    }
}
