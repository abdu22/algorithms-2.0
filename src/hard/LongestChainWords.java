package hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LongestChainWords {
    /* 

            a
            b
            ba
            bca
            bda
            bdca
     */
    public static void main(String[] args) {

        List<String> l = new ArrayList<>();
        l.add("a");  l.add("b");  l.add("ba");  l.add("bca");
        l.add("bda");  l.add("bdca");

        System.out.println(longestChain(l));
        
    }
    public static int longestChain(List<String> words) {
        // Write your code here

        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        set.addAll(words);
        /* for(String word:words) {
            set.add(word);
        } */
        int longestChain = 0;

        for(String word: words){
            int tempLength = traverseDFS(word, set, map);
            longestChain = Math.max(longestChain, tempLength);
        }
         return longestChain;
    }

    public static int traverseDFS(String word, Set<String> set, Map<String, Integer> map){

        if(map.containsKey(word)) 
           return map.get(word);

         
         int maxLength = 1; 

         for(int i=0; i< word.length(); i++) {
            String current = "" + word.substring(0, i) + word.substring(i+1, word.length()); 
            if(set.contains(current)){
                 int tempLength = 1 + traverseDFS(current, set, map);
                 maxLength = Math.max(maxLength, tempLength);
            }
         }

         map.put(word, maxLength);
         
        return maxLength;
    }
     
        
}
