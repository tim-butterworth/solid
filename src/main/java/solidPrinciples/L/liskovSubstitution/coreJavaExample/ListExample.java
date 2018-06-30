package solidPrinciples.L.liskovSubstitution.coreJavaExample;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        String[] entries = new String[]{"one", "two", "three"};

        liskovTest("Linked List", populateList(LinkedList.class, entries));
        liskovTest("Array List", populateList(ArrayList.class, entries));
        liskovTest("Arrays.asList", Arrays.asList(entries));
    }

    private static void liskovTest(String message, List<String> list) {
        System.out.println(message);
        System.out.println(list);

        list.remove(0);

        System.out.println(list);

        list.add("new entry");

        System.out.println(list);
        System.out.println();
    }

    private static <T extends List<String>> T populateList(Class<T> listClass, String ...entries) {
        try {
            T list = listClass.getDeclaredConstructor().newInstance();

            list.addAll(Arrays.asList(entries));

            return list;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
