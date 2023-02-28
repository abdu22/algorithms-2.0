package hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BusRoute {
    Map<Integer, Map<Integer, List<Integer>>> G;

    public int numBusesToDestination(int[][] routes, int source, int target) {
        G  = new HashMap<>();
        //build the graph

        for(int i=0; i<routes.length; i++) {
            int [] route = routes[i];
            for(int j=0 ;j <route.length; j++){
                int a = route[j];
                int b = j < route.length-1 ? route[j+1] : a ;

                if(!G.containsKey(a)) 
                   G.put(a, new HashMap<>());

                if(!G.get(a).containsKey(i))
                   G.get(a).put(i, new ArrayList<>());

                G.get(a).get(i).add(b);     

                if(!G.containsKey(b)) 
                   G.put(b, new HashMap<>());  

                if(!G.get(b).containsKey(i))
                   G.get(b).put(i, new ArrayList<>()); 

                G.get(b).get(i).add(a);       
            }
        }  
     Set<Integer> set = new HashSet<>();  
     int temp =  dfs(source, target , -1 , set);

    return temp; 
    }

  public int dfs(int source, int target, int busNo, Set<Integer> set){
         if(source == target) return 0;

         set.add(source);
         Map<Integer, List<Integer>> buses = G.get(source);
         int minNoOfBuses = Integer.MAX_VALUE;

         for(Integer key: buses.keySet()) { 
             List<Integer> edges = buses.get(key);
             for(Integer edge: edges){
                 if(minNoOfBuses == 0) 
                   continue;

                  if(set.contains(edge))
                     continue;

                  int temp = dfs(edge, target, key, set);
                  if(temp == -1)
                         continue; 

                  temp = busNo == key ? temp : temp + 1;
                  minNoOfBuses = Math.min(minNoOfBuses, temp);
             }
         }
       set.remove(source);
       return minNoOfBuses == Integer.MAX_VALUE ? -1 : minNoOfBuses;  
  } 
  
  public static void main(String[] args) {
        BusRoute obj = new BusRoute();
        int [][] routes = 
         {{0,13,49,51,53,55,60,65,66,80,82,87,92,99,112,118,120,125,128,131,137},{15,19,34,37,45,52,56,97,108,123,142},
         {15,41,64,83},{7,13,26,31,57,85,101,108,110,115,119,124,149},
         {1,5,7,10,11,18,40,45,50,51,52,54,55,69,71,81,82,83,85,89,96,100,114,115,124,134,138,148},
         {18,24,30,52,61,64,75,79,85,95,100,103,105,111,128,129,142},
         {3,14,18,32,45,52,57,63,68,78,85,91,100,104,111,114,142},
         {4,7,11,20,21,31,32,33,48,61,62,65,66,73,80,92,93,97,99,108,112,116,136,139}};
            /* {{11,32,52,85,135},
            {43,50,128},
            {7,13,26,31,57,85,101,108,110,115,119,124,149},
            {47,61,67,70,74,75,77,84,92,101,124,132,133,142,147},
            {0,2,5,6,12,18,34,37,47,58,77,98,99,109,112,131,135,149},
            {1,5,7,10,11,18,40,45,50,51,52,54,55,69,71,81,82,83,85,89,96,100,114,115,124,134,138,148},
            {6,7,12,33,37,41,47,53,54,80,107,121,126},
            {18,24,30,52,61,64,75,79,85,95,100,103,105,111,128,129,142},
            {3,14,18,32,45,52,57,63,68,78,85,91,100,104,111,114,142}}; */

        int source = 85;
        int target = 112;
        int ans = obj.numBusesToDestination(routes, source, target);
        System.out.println(ans);
  }
}
