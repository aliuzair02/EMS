package org.template.common.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.template.common.constants.MessageConstants;
import org.template.common.models.BaseVO;
import org.template.common.models.ResponseObject;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ObjectService extends BaseService {

    public static <T extends BaseVO> ResponseEntity<ResponseObject> getResponseBody(T body) {

        ResponseObject ro = new ResponseObject();
        ro.setBody(body);
        ro.setLogId(logId);
        ro.setStatus(body.isStatus());

        if (!body.isStatus()) {
            ro.setMessage(body.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ro);
        }
        ro.setMessage(MessageConstants.successMessage);

        return ResponseEntity.status(HttpStatus.OK).body(ro);
    }

    public static <T extends BaseVO> void setStatusVO(T body, boolean status, String message) {

        body.setStatus(status);
        body.setMessage(message);
        body.setLogId(logId);

    }

    public static <T, R> void copyProperties(R fromObject, T toObject){

        if (Objects.nonNull(fromObject) && Objects.nonNull(toObject)){

            Class<?> clazz = fromObject.getClass();
            Field[] fields = getAllFields(clazz);
            Class<?> clazzVO = toObject.getClass();
            Field[] var1 = fields;
            int var2 = fields.length;

            for (int var3 = 0; var3 < var2 ; ++var3){
                Field field = var1[var3];
                String fieldName = field.getName();
                Class<?> clazzFieldType = field.getType();
                Type genericType = field.getGenericType();

                try {
                    Field fieldVO = getField(fieldName, clazzVO);
                    if (fieldVO != null) {
                        Class<?> clazzFieldTypeVO = fieldVO.getType();
                        Type genericTypeVO = fieldVO.getGenericType();
                        if (clazzFieldType.equals(clazzFieldTypeVO) && genericType.equals(genericTypeVO)) {
                            try {
                                boolean proceed = true;
                                if (genericType instanceof ParameterizedType) {
                                    ParameterizedType aType = (ParameterizedType)genericType;
                                    ParameterizedType aTypeVO = (ParameterizedType)genericTypeVO;
                                    Type[] fieldArgTypes = aType.getActualTypeArguments();
                                    Type[] fieldArgTypesVO = aTypeVO.getActualTypeArguments();
                                    Type[] var4 = fieldArgTypes;
                                    int var5 = fieldArgTypes.length;

                                    for(int var6 = 0; var6 < var5; ++var6) {
                                        Type fieldArgType = var4[var6];
                                        boolean isFound = false;
                                        Class<?> fieldArgClass = (Class)fieldArgType;
                                        Type[] var7 = fieldArgTypesVO;
                                        int var8 = fieldArgTypesVO.length;

                                        for(int var9 = 0; var9 < var8; ++var9) {
                                            Type fieldArgTypeVO = var7[var9];
                                            if (fieldArgClass.equals(fieldArgTypeVO)) {
                                                isFound = true;
                                                break;
                                            }
                                        }

                                        if (!isFound) {
                                            proceed = false;
                                            break;
                                        }
                                    }
                                }

                                if (proceed) {
                                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, clazz);
                                    Method getter = propertyDescriptor.getReadMethod();
                                    Object value = getter.invoke(fromObject, (Object[])null);
                                    propertyDescriptor = new PropertyDescriptor(fieldName, clazzVO);
                                    Method setter = propertyDescriptor.getWriteMethod();
                                    setter.invoke(toObject, value);
                                }
                            } catch (Exception _) {
                            }
                        }
                    }
                } catch (Exception _) {
                }
            }

        }

    }

    public static Field[] getAllFields(Class<?> clazz){

        Field[] fields;
        for (fields = null; Objects.nonNull(clazz.getSuperclass()); clazz = clazz.getSuperclass()){

            Field[] currentFields = clazz.getDeclaredFields();
            if (Objects.nonNull(currentFields)){
                List<Field> listField = new ArrayList<>(Arrays.asList(currentFields));
                if (Objects.nonNull(fields)){
                    List<Field> oldList = Arrays.asList(fields);
                    listField.addAll(oldList);
                }

                fields = (Field[]) listField.toArray(new Field[listField.size()]);

            }

        }

        return fields;

    }

    public static Field getField(String fieldName, Class<?> clazz){

        Field field = null;

        for (Class<?> currentClazz = clazz; Objects.nonNull(currentClazz.getSuperclass()); currentClazz = currentClazz.getSuperclass()){

            try {
                field = currentClazz.getDeclaredField(fieldName);
                if (Objects.nonNull(field)) {
                    break;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        return field;

    }

    public static String getErrorMessage(Exception e){
        String str1 = e.getMessage();

        if (str1.contains("Duplicate")) {
            Pattern pattern = Pattern.compile("constraint\\s*\\[[^_]+_[^_]+_(.+?)]");
            Matcher matcher = pattern.matcher(str1);

            if (matcher.find()) {
                return "Duplicate record for " + matcher.group(1);
            }

        }

        return str1;
    }

}
