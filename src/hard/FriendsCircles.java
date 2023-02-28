package hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FriendsCircles {

    /* 

        Y Y N N
        Y Y Y N
        N Y Y N
        N N N Y
        
     */
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
       /*  input.add("YYNN");
        input.add("YYYN");
        input.add("NYYN");
        input.add("NNNY"); */

        input.add("YNNNN");
        input.add("NYNNN");
        input.add("NNYNN");
        input.add("NNNYN");
        input.add("NNNNY");

        System.out.println(friendCircles(input));


    }

    static int friendCircles(List<String> friends) {

        Map<Integer, List<Integer>> g = new HashMap<>();

        for (int i = 0; i < friends.size(); i++) {
            String row = friends.get(i);
            for(int j=0; j < row.length(); j++){
                if(i == j) { // the friend it self
                    g.put(i, new ArrayList<>());
                }
                if(row.charAt(j) == 'Y'){
                      if(!g.containsKey(i)){
                        g.put(i, new ArrayList<>()); // create if not exist
                      }
                        g.get(i).add(j); // add it to the list
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        int count = 0;

        for(Integer key: g.keySet()){
            if(!set.contains(key)){
                traverseDFS(key, g, set);
                count++;
            }
        }

        return count;
    }

    public static void traverseDFS(Integer parent,  Map<Integer, List<Integer>> g,  Set<Integer> set){
           set.add(parent);
           if(g.get(parent) != null) {
               for(Integer child: g.get(parent)){
                if(!set.contains(child)){
                    traverseDFS(child, g, set);
                }
               }
           }
    }
}
