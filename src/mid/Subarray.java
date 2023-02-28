package mid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subarray {
    public static void main(String[] args) {
        Subarray o = new Subarray();
        int [] a = new int[] {3, 2, 3, 3, 1, 3};
        List<Integer> arr = new ArrayList<>(Arrays.asList( 3, 2, 3, 3, 1, 3));
        System.out.println(o.findMinimumLengthSubarray(arr, 3));
    }
    public  int findMinimumLengthSubarray(List<Integer> arr, int k) {
        // Write your code here
           int minLength = Integer.MAX_VALUE;
           int l=0; int r=0;
           Map<Integer, Integer> map = new HashMap<>();
           map.put(arr.get(0), 1);
           while(r < arr.size()){
                    if(map.size() < k){
                        // increase window size
                        r++;
                        if(r >= arr.size()) break;
                        
                        if(!map.containsKey(arr.get(r)))
                            map.put(arr.get(r), 0);
                            
                        int val = map.get(arr.get(r));     
                        map.put(arr.get(r),val+1);  
                    }else if(map.size() == k ){
                        int len = r-l+1;
                        minLength = Math.min(minLength, len);

                        //map.put(arr.get(l), arr.get(l)-1);
                        map.put(arr.get(l), map.get(arr.get(l))-1);

                        if(map.get(arr.get(l)) == 0 )
                            map.remove(arr.get(l));
                          
                        l++;
                    }
           }
              return minLength;
        }
}
