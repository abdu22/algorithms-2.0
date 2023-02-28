package mid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class NetworkDelayTime {
       /*
      case 2; [[1,2,1],[2,1,3]]

        [1]  -> [2]
         \<------|
         
      case 1 : [[2,1,1],[2,3,1],[2,5,1], [3,4,1], [4,6,1]], n = 4, k = 2 
                     [5]
                     /
                  [2] 
                 /   \
             [1]       [3]
                      /
                  [4] - [6]
        s : 2, 1, 3, 4
         m                  m2 
         2, [<1,1>,<3,1>,<5,1>]         
         3, [<4,1>]                     
         4, [<6,1>] 
              [node,dist]                  
         PQ : { [2,0] }         
         */
        public static void main(String [] args){
            //int [] [] network = {{2,1,1}, {2,3,1}, {2,5,1}, {3,4,1}, {4,6,1}};
            int [] [] network = {{1,2,1}, {2,1,3}};
            int ans = networkDelayTime(network, 2, 2);
            System.out.println(ans);
        }
        // Using DFS ,or Djkistra algorithm, shortes path finding
        public static int networkDelayTime(int[][] times, int n, int k) { 
            int timeTOTravel = 0;
            // build a graph
            Map<Integer, List<Integer[]> > g = new HashMap<>();
            for(int []time:times){
                if(!g.containsKey(time[0])){
                    g.put(time[0], new ArrayList<>());
                }
                g.get(time[0]).add(new Integer [] {time[1], time[2]});
            }

            PriorityQueue<Integer []> pq = new PriorityQueue<>((x,y) -> x[1] - y[1]);
            Set<Integer> set = new HashSet<>();
            
            pq.add(new Integer [] {k, 0});

            while(!pq.isEmpty()){
                Integer [] curr = pq.poll();
                if(set.contains(curr[0])){
                    continue;
                }
                timeTOTravel = Math.max(timeTOTravel, curr[1]);

                List<Integer []> childs = g.get(curr[0]);
                set.add(curr[0]);

                if(childs == null) 
                    continue;

                for(Integer [] child:childs){
                    if(!set.contains(child[0]))
                      pq.add(new Integer [] {child[0], curr[1]+child[1]});
                }

                
            }
          if(set.size() < n) {
            return -1;
          }
            return timeTOTravel;
        }

    /* 
     This is wrong.  BFS not good for shortes path prople. use DFS.
     Doesn't work for
             ____________
            |             \
           [1] -> [2] -> [3]
     */
/*         public static int networkDelayTime(int[][] times, int n, int k) {
            int timeTOTravel = 0;
            Map<Integer, List<Integer []>> m1 = new HashMap<>();
            for(int[] time:times){
                if(!m1.containsKey(time[0])){
                    m1.put(time[0], new ArrayList<Integer []>());
                }
                 List<Integer []> currentL = m1.get(time[0]);
                Integer [] temp = new Integer [2];
                temp[0] = time[1]; temp[1] = time[2];
                currentL.add(temp);
            }
            Map<Integer, Integer> sum = new HashMap<>();
            Queue<Integer> q = new LinkedList<>();
            q.add(k); sum.put(k, 0);
           // Q [2] -> [1][3][5] ->
            while(q.size() > 0) {
                int size = q.size();
                while(size > 0) {
                    size--;
                    Integer poll = q.poll();
                     List<Integer []> currentChilds = m1.get(poll);
                     if(currentChilds == null) 
                     continue;
                     for(Integer[] child: currentChilds){
                         if(sum.containsKey(child[0])){
                            // already visited
                            continue;
                        }
                         q.add(child[0]);
                         // new time, parent + child
                         int newTime = sum.get(poll)+child[1];
                         timeTOTravel = Math.max(newTime, timeTOTravel);
                         sum.put(child[0], newTime);
                     }
                }
            }
          if(sum.size() < n) {
              return -1;
          }
       return timeTOTravel;
        } */
}
