package mid;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class TwoCityScheduling {
        /* 
           

                  y.        y.        x.       x
       A sort : (10, 20) (30, 200) (30, 20) (400, 50) 
                   Y
       B sort : (30, 20) (10, 20) (50, 400) (30, 200) 
                    y         x         y        x

    PQ (10, 20, 0(idx)) 
    
         0         1       2          3         4         5       6          7         8          9       10         11
    [[70,311],[74,927],[732,711],[126,583],[857,118],[97,928],[975,843],[175,221],[284,929],[816,602],[689,863],[721,888]]
         0        1         5       3         7         8          10       11        2         9         4        6
    [[70,311],[74,927],[97,928],[126,583],[175,221],[284,929],[689,863],[721,888],[732,711],[816,602],[857,118],[975,843]]
        4         7       0         3          9         2         6        10         11      5        1           8
    [857,118],[175,221],[70,311],[126,583],[816,602],[732,711],[975,843],[689,863],[721,888],[97,928],[74,927], [284,929]
    
     */
     public static void main(String[] args) {
        TwoCityScheduling o = new TwoCityScheduling();

        int [][] costs = new int [][] {{70,311},{74,927},{732,711},{126,583},{857,118},{97,928},{975,843},{175,221},{284,929},{816,602},{689,863},{721,888}};
        System.out.println("total cost " +o.twoCitySchedCost(costs));
        System.out.println("total cost " +o.twoCitySchedCost2(costs));
     }
     
     public int twoCitySchedCost(int[][] costs) {
        PriorityQueue<int []> aSort = new PriorityQueue<>((x,y) -> {
            if(x[0] != y[0])
                return x[0]-y[0];
             else 
                 return y[1] - x[1];   
        });

        PriorityQueue<int []> bSort = new PriorityQueue<>((x,y) -> 
        {
            if(x[0] != y[0])
                return x[0]-y[0];
             else 
                 return y[1] - x[1];   
        });

        for(int i=0; i<costs.length; i++){
            aSort.offer(new int[] {costs[i][0], costs[i][1], i});
            bSort.offer(new int[] {costs[i][1], costs[i][0], i});
        }
        
        Set<Integer> set = new HashSet<>();
        // greedly collect it if it doesn't exist in the set

        int totalCost = 0;

        while(!aSort.isEmpty() && !bSort.isEmpty()){
            // get one for city A
            totalCost += helpFindNextCheap(aSort, set);

            // get one for city B
            totalCost += helpFindNextCheap(bSort, set);
        }
        
       return totalCost; 
    }

    public int helpFindNextCheap( PriorityQueue<int []> sort, Set<Integer> set){
        int cost = 0;
          while(!sort.isEmpty()){
              int [] current = sort.poll();
              if(!set.contains(current[2])){
                  cost = current[0];
                  set.add(current[2]);
                  System.out.println("setVal : "+ current[2]);
                  break;
              }
          }
         return cost; 
    }
    public int twoCitySchedCost2(int[][] costs) {
        // Sort by a gain which company has 
        // by sending a person to city A and not to city B
        Arrays.sort(costs, new Comparator<int[]>() {
          @Override
          public int compare(int[] o1, int[] o2) {
            return o1[0] - o1[1] - (o2[0] - o2[1]);
          }
        });
        /*
        

          
         */
    
        int total = 0;
        int n = costs.length / 2;
        // To optimize the company expenses,
        // send the first n persons to the city A
        // and the others to the city B
        for (int i = 0; i < n; ++i) total += costs[i][0] + costs[i + n][1];
        return total;
      }
}
