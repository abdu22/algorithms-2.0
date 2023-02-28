package basics;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class testLinkedHashMap {
    public static void main(String[] args) {
        LinkedHashMap<Integer, Integer> lm = new LinkedHashMap<>();

        LinkedHashSet<Integer> ls = new LinkedHashSet<>();

        ls.add(1); ls.add(2); ls.add(3);

        ls.stream().forEach(x -> System.out.print(x +" ,"));

     

    }
}
