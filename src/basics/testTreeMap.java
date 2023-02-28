package basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ThreadPoolExecutor;

public class testTreeMap {

    /* 
          [10, 20]
     */
    public static void main(String[] args) {

        double xx = Math.floor(5.1);
        double yy = Math.ceil(5.1);


                

        Map<String, Map<String, Double []>> mapOfMap = new HashMap<>();
        mapOfMap.put("San Jose", new HashMap<>());

        mapOfMap.get("San ose").put("San Fransisco", new Double [] {1.0, 40.0});

        List<Integer> list = new ArrayList<>();

        TreeMap<Integer, Integer> tm = new TreeMap<>();
        TreeMap<Integer, Integer> c = new TreeMap<>(tm);

        TreeSet<Integer> ts = new TreeSet<>();
        Integer uu = ts.ceiling(0);

        for(Integer key: tm.keySet()){
            
        }
        


        
        tm.put(10, 20);
        tm.put(7,30);
        Integer kk = tm.lastKey();

                
        Integer w = tm.floorKey(9);
        Integer x = tm.floorKey(10);
        Integer y = tm.floorKey(15);
        
        Integer z = tm.ceilingKey(10); // 10
        Integer zz = tm.ceilingKey(9); // 7

        Integer zzz = tm.higherKey(10); // 7
        Integer zzzz = tm.higherKey(9); // 7

        //Integer z = tm.ceilingKey(9);

     int [][] [] arra = new int[][][] {
        {{1,2,4}, {1,2,4}, {7,9,8}},
        {{1,1,1}, {5,5,5}, {6,2,1}}
     };

     boolean stor = false;

     int upper = 15;
     int lower = 10; 

     int randValue = (int)(Math.random() * (upper - lower) + lower);


     Integer [] test = new Integer [] {1,2,3,4};
     Integer [] test2 = new Integer [] {1,2,3,4};


     Integer [] clone = Arrays.copyOf(test, test.length);

     boolean is = clone == test;
     is = clone.equals(test);



    }
}
