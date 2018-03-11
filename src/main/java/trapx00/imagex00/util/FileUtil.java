package trapx00.imagex00.util;

import net.sf.json.JSONObject;
import trapx00.imagex00.MainApplication;
import trapx00.imagex00.entity.Entity;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class FileUtil {
    private final static String savePath = MainApplication.class.getResource("../../../resources/data/").getPath();
    private final static String fileType = ".txt";

    /**
     * save the entity
     *
     * @param entity the entity object
     * @return the entity if success else return null
     */
    public static <T> T saveTuple(Entity entity, Class<T> clazz) {
        T tuple = (T) entity;
        String tableName = AnnotationUtil.getTableName(clazz);
        ArrayList<String> columns = AnnotationUtil.getAllFieldName(clazz);
        JSONObject json = JSONObject.fromObject(tuple);

        FileWriter writer = null;
        try {
            writer = new FileWriter(savePath + tableName + fileType, true);
            writer.write(json.toString());
            writer.write(System.lineSeparator());
            writer.flush();
            writer.close();
            return tuple;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * find a entity
     *
     * @param info the key info to find
     * @return the entity
     */
    public static <T> T findOne(String info, Class<T> clazz) {
        String methodName = new Exception().getStackTrace()[1].getMethodName();
        String columnName = methodName.split("By")[1].toLowerCase();
        String tableName = AnnotationUtil.getTableName(clazz);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(savePath + tableName + fileType)));
            String json;
            while ((json = bufferedReader.readLine()) != null) {
                JSONObject jsonObject = JSONObject.fromObject(json);
                if (jsonObject.get(columnName).equals(info)) {
                    return fromJsonToObject(jsonObject, clazz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private static <T> T fromJsonToObject(JSONObject jsonObject, Class<T> clazz) {
        try {
            T t = clazz.newInstance();
            ArrayList<String> columns = AnnotationUtil.getAllFieldName(clazz);
            for (String column : columns) {
                Field field = clazz.getDeclaredField(column);
                field.set(t, jsonObject.get(column));
            }
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
