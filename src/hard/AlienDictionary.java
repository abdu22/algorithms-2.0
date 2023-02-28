package hard;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AlienDictionary {
    boolean hasFound = false;
    public static void main(String[] args) {
        AlienDictionary obj = new AlienDictionary();
        String [] words1 = new String [] {"wrt","wrf","er","ett","rftt"};
        String [] words2 = new String [] {"zy","zx"};
        String [] words3 = new String [] {"z", "x", "z"};
        String [] words5 = new String [] {"abc","ab"};
        String [] words6 = new String [] {"z","x","a","zb","zx"};
        String [] words4 = new String [] {"dvpzu","bq","lwp","akiljwjdu","vnkauhh","ogjgdsfk","tnkmxnj","uvwa","zfe","dvgghw","yeyruhev","xymbbvo","m","n"};

        System.out.println(obj.alienOrder(words1));
        System.out.println(obj.alienOrder(words2));
        System.out.println(obj.alienOrder(words3));
        System.out.println(obj.alienOrder(words4));
        System.out.println(obj.alienOrder(words5));
        System.out.println(obj.alienOrder(words6));
    }
    /* 
        Input:   words = ["wrt","wrf","er","ett","rftt"]
        
         Output: "wertf"

          g
         ---
         t, L[t, f]
         w, L[e, ]
         r, L[t]
         e, L[r]

         do topological sort.

         dq :  [  w e r t f ]

       Input  : ["z","x","z"]
       Output : invalid, "";

       g 
       z : L[x]
       x : L[z]
     */
    // find a cycle
    public String alienOrder(String[] words) {

     Map<Character, List<Character>> g = new HashMap<>();
     int [] charaDict = new int [26];

     // make sure all are key
     for(String s:words){
        for(Character c:s.toCharArray())
          g.put(c, new ArrayList<>());
     }
     //build graph
     for(int i=0; i<words.length-1; i++) {
        String current = words[i];
        String next = words[i+1];
        int shortLength = Math.min(current.length(), next.length());
       
        int j = 0;
        for( j = 0; j < shortLength ; j++) {
            if(current.charAt(j) == next.charAt(j)){
                continue;
            }else{
               /* if(!g.containsKey(current.charAt(j)))
                 g.put(current.charAt(j), new ArrayList<>()); */
                 g.get(current.charAt(j)).add(next.charAt(j));
                 charaDict[next.charAt(j) - 'a']++;
                 break;
            }
        }
        if(j == shortLength && current.length() > next.length()){
            // invalid 
            // eg  // ["abc" , "ab"]
            return "";
        }
     }
     //check for cycle 
     Set<Character> set = new HashSet<>();
     for(Character c:g.keySet()){ 
         checkForCycle(c, set, g);
        if(hasFound == true ) return "";
     }
     
     

     set = new HashSet<>();
     Deque<Character> q = new LinkedList<>();
     
     for(Character c:g.keySet()){
        if(!set.contains(c) && charaDict[c-'a'] == 0){
            helpToplogicalSort(c, g, set, q);
        }
     }
     StringBuilder ans = new StringBuilder();
     while(!q.isEmpty()){
        ans.append(q.pollLast());
     }
     return ans.toString();   
    }

    public void helpToplogicalSort(Character c, Map<Character,  List<Character>> g,  
              Set<Character> set, Deque<Character> q)
    {
        if(set.contains(c))
            return;

        set.add(c);
        if( g.get(c) != null) {
            for(Character child: g.get(c)){
                    helpToplogicalSort(child, g, set, q);
            }
        }
        q.add(c);
    }

    public void checkForCycle(Character c, Set<Character> set,  Map<Character,  List<Character>> g){
        if(hasFound == true || set.contains(c)){
            hasFound = true;
            return;
        }
        set.add(c);
        for(Character child:g.get(c)){
            checkForCycle(child, set, g);
        }
        set.remove(c);
    }
}
