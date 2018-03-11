package trapx00.imagex00.util;

import net.sf.json.JSONObject;
import trapx00.imagex00.MainApplication;
import trapx00.imagex00.entity.Entity;
import trapx00.imagex00.exception.daoexception.IdDoesNotExistException;

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
    public static <T extends Entity> T saveTuple(T entity, Class<T> clazz) {
        String tableName = AnnotationUtil.getTableName(clazz);
        ArrayList<String> columns = AnnotationUtil.getAllFieldName(clazz);
        ArrayList<String> fileContent = new ArrayList<>();
        String id = AnnotationUtil.getKey(clazz);
        JSONObject json = JSONObject.fromObject(entity);

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(savePath + tableName + fileType)))) {
            boolean isUpdate = false;
            String jsonLine;
            while ((jsonLine = bufferedReader.readLine()) != null) {
                JSONObject jsonObject = JSONObject.fromObject(jsonLine);
                if (jsonObject.get(id).equals(json.get(id))) {
                    jsonLine = json.toString();
                    isUpdate = true;
                }
                fileContent.add(jsonLine);
            }
            if (!isUpdate) {
                fileContent.add(json.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try (FileWriter writer = new FileWriter(savePath + tableName + fileType, true)) {
            for (String tuple : fileContent) {
                writer.write(tuple);
                writer.write(System.lineSeparator());
                writer.flush();
            }
            return entity;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(savePath + tableName + fileType)))) {
            String json;
            while ((json = bufferedReader.readLine()) != null) {
                JSONObject jsonObject = JSONObject.fromObject(json);
                if (jsonObject.get(columnName).equals(info)) {
                    return fromJsonToObject(jsonObject, clazz);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> void delete(String id, Class<T> clazz) {
        String tableName = AnnotationUtil.getTableName(clazz);
        ArrayList<String> fileContent = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(savePath + tableName + fileType)))) {
            boolean isExist = false;
            String jsonLine;
            while ((jsonLine = bufferedReader.readLine()) != null) {
                JSONObject jsonObject = JSONObject.fromObject(jsonLine);
                if (jsonObject.get(id).equals(id)) {
                    isExist = true;
                } else {
                    fileContent.add(jsonLine);
                }
            }
            if (!isExist) {
                throw new IdDoesNotExistException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(savePath + tableName + fileType, true)) {
            for (String tuple : fileContent) {
                writer.write(tuple);
                writer.write(System.lineSeparator());
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
