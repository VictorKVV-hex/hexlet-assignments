package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {

    public static List<String> validate(Address address) {
        List<String> list = new ArrayList<>();
        String PartOfAdress = null;
        try {
            Field[] fields = address.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                PartOfAdress = (String) field.get(address);
                if (field.isAnnotationPresent(NotNull.class) && PartOfAdress == null) {
                    list.add(field.getName());
                }
//                System.out.println(field);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        String PartOfAdress = null;
        int DeclsredLengthPart = 0;
        try {
            Field[] fields = address.getClass().getDeclaredFields();
            for (Field field : fields) {
                List<String> list = new ArrayList<>();
                field.setAccessible(true);
                boolean bPut = false;
                PartOfAdress = (String) field.get(address);
                if (field.isAnnotationPresent(NotNull.class) && PartOfAdress == null) {
                    list.add("can not be null");
                    bPut = true;
                }
                if (field.isAnnotationPresent(MinLength.class)) {
                    MinLength minLengthInfo =  field.getAnnotation(MinLength.class);
                    DeclsredLengthPart = minLengthInfo.minLength();
                    PartOfAdress = (String) field.get(address);
                    if (PartOfAdress.length() < DeclsredLengthPart) {
                        list.add("length less than " + DeclsredLengthPart);
                        bPut = true;
                    }
                }
                if (bPut) {
                    map.put(field.getName(), list);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }
}
// BEGIN

// END
