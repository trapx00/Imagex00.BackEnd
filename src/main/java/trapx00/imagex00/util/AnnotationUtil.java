package trapx00.imagex00.util;

import trapx00.imagex00.entity.annotation.Column;
import trapx00.imagex00.entity.annotation.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class AnnotationUtil {
    public static String getTableName(Class clazz) {
        Table table = (Table) clazz.getAnnotation(Table.class);
        return table.name();
    }

    public static ArrayList<String> getAllFieldName(Class clazz) {
        ArrayList<String> columns = new ArrayList<>();
        Field[] fields = clazz.getFields();
        if (fields != null) {
            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Column column = field.getAnnotation(Column.class);
                columns.add(column.name());
            }
        }
        return columns;
    }
}
