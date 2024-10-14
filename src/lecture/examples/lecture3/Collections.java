package lecture.examples.lecture3;

import java.util.*;

public class Collections {
    public static void main(String[] args) {
        Map<String, Integer> numbers = new HashMap<>();

        // Adding key-value pairs to a HashMap
        numbers.put("One", 1);
        numbers.put("Two", 2);
        numbers.put("Three", 3);

        for (String key : numbers.keySet())
            System.out.println(key + " " + numbers.get(key));

        System.out.println("-----------------------------------------------");

        Iterator it = numbers.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " " + pair.getValue());
        }

        System.out.println("-----------------------------------------------");

        SortedSet<String> names = new TreeSet<>();
        names.add("Mihai");
        names.add("Andreea");
        names.add("Bianca");
        names.add("Ionut");

        System.out.println(names);

        names.add("Mihai"); // will not be added (a set cannot have duplicate values)

        System.out.println(names);
    }
}
