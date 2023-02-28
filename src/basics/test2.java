package basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class test2 {
    /* 
     * 
     * 
     * 
     * 
     *  
     * Total share = 6
     * [.  
     *   u.   sc   bp   ts
     *
      
         [3   5    4.   6]
         [1   2    5    0]
         [2   1.   4    2]
         [4   1    5    7]
         [5   2    5.   6]
         [6   10   1.   1]

         // after sorting
        
         [1   2    5    0],
         [5   2    5.   6]
         [4   1    5    7]
         [2   1.   4    2]
         [3   5    4.   6]
         [6   10   1.   1]
         
     Map 
     ---------------------------------------
     int : SumListPair
     ---------------------------------------
      
      4  : { sum=6,  list <[2,2], [3,6]>  }
      1  : { sum=10, list <[6,1]> }
      5  : { sum=5,  list <[1,0], [5,6], [4,7]> }
      
     ----------------------------------------

    PQ key = [ 5, 4, 1 ]

    poll() : 5 -> sumSC = 5 -> 6-5 = 1;
    
      if(share > sumSC)
          check the next pq key 
      else if(share >= list.size()) // to check if all gets one
          all the remaining PQ are not getting it
      
      else 
         Iterate through the list count ++ till share exaustes & 
         all the remaining PQ are not getting it




     
      
     

      for( 1, 5, 4 ) {

      }



     ]
     * 
     */

    public static void main(String[] args) {

         List<List<Integer>> bids = new ArrayList<>( );
         
         List<Integer> each1 = new ArrayList<>();
         each1.add(3); each1.add(5); each1.add(4); each1.add(6);
         bids.add(each1);

         List<Integer> each2 = new ArrayList<>();
         each2.add(1); each2.add(2); each2.add(5); each2.add(0);
         bids.add(each2);

         List<Integer> each3 = new ArrayList<>();
         each3.add(2); each3.add(1); each3.add(4); each3.add(2);
         bids.add(each3);

         PriorityQueue<List<Integer>> priorityQueue = new PriorityQueue<>((x,y) ->{
            if(y.get(2) == x.get(2)) {
            return x.get(3) - y.get(3);
            }
           return y.get(2) - x.get(2); 
        } );

        priorityQueue.add(each1);
        priorityQueue.add(each2);
        priorityQueue.add(each3);

        while(priorityQueue.size() > 0) {
            Integer temp = priorityQueue.poll().get(0);

            System.out.println(temp);
        }

         

        /*  bids.sort((x,y) -> {
           if(y.get(2) == x.get(2)) {
            return x.get(3) - y.get(3);
            }
           return y.get(2) - x.get(2);
         });

         for(int i=0; i<bids.size(); i++) {
            for(int j=0; j<bids.get(0).size(); j++){
                System.out.print(bids.get(i).get(j));
            }
            System.out.println();
         } */

    }
}
