package basics;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class testSorting {
    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(9, 4, 3, 8, 7, 2, 6, 1, 2, 3);
        
       Collections.sort(l1 , (x,y) -> y-x);

         l1.stream().forEach(x -> System.out.print(x +" ,"));
    }
}
