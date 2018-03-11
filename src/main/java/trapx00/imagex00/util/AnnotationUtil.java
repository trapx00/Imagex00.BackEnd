package trapx00.imagex00.util;

import trapx00.imagex00.entity.annotation.Column;
import trapx00.imagex00.entity.annotation.Id;
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

    public static String getKey(Class clazz) {
        String key = "";
        Field[] fields = clazz.getFields();
        if (fields != null) {
            for (Field field : fields) {
                Id id = field.getAnnotation(Id.class);
                key = id != null ? field.getName() : key;
            }
        }
        return key;
    }
}
