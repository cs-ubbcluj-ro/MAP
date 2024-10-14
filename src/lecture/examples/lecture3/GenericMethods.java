package lecture.examples.lecture3;

class Pair<K, V> {
    private K key;
    private V value;

    Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

class Utils {
    public static <K, V> boolean equals(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }
}

public class GenericMethods {
    public static void main(String[] args) {
        Pair<Integer, String> p1 = new Pair<>(1, "car");
        Pair<Integer, String> p2 = new Pair<>(2, "vehicle");
        System.out.println(Utils.equals(p1, p2));

        Pair<String, String> p3 = new Pair<>("gene", "unit of heredity");
        Pair<String, String> p4 = new Pair<>("gene", "unit of heredity");
        System.out.println(Utils.equals(p3, p4));
    }
}
