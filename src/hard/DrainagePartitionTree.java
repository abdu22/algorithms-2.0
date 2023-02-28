package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DrainagePartitionTree{
    /* 
      p  [-1, 0, 0, 1, 1, 2]
      i  [ 1, 2, 2, 1, 1, 2]

             [1]
            /   \
         [2]     [2]
         / \ .      \ 
      [1]   [1]     [2]  

           0    1    2    3    4     5
      sum [1,   2,   2,   1,   1,    2]
                    +2
                +1
                +1
           +4
           +3      
           

        compare sum[0] - sum[i]
     */
    public static void main(String [] args) {
        DrainagePartitionTree obj = new DrainagePartitionTree();
        List<Integer> parent = new ArrayList<>(Arrays.asList(-1, 0, 0, 1, 1, 2));
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 2, 1, 1, 2));

        int ans = obj.drainagePartition(parent, input);    
        System.out.println(ans);
    }

     int minDiff = Integer.MAX_VALUE;

     public int drainagePartition(List<Integer> parents, List<Integer> inputs){

        for(int i=inputs.size()-1 ; i > 0; i--) {
            List<Integer> sum = new ArrayList<>(inputs);
            helpFind(parents, sum, i, sum.size()-1);
            int diff = Math.abs(sum.get(0) - sum.get(i));
            minDiff = Math.min(minDiff, diff);
        }
    
      return minDiff;
    }

    public void  helpFind(List<Integer> parents, List<Integer> sum, int cutIndx, int i){
         if(i < 0) {
            return;
         }

        if(cutIndx != i){
            // if not in the cut indx, add it the parent else, skip. 
            int parent =  parents.get(i);
            // for edge case, where were reach parent -1, root doesn't have parent
            if(parent < 0) {
                return;
            }
            int sumParent = sum.get(parent) + sum.get(i);
            sum.set(parent, sumParent);
        }
        helpFind(parents, sum, cutIndx, i-1);
    }
}