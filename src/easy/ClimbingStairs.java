package easy;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class ClimbingStairs {
    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis())  ;
        System.out.println(climbStairs(44));
        System.out.println(System.currentTimeMillis())  ;
    }

    public static int climbStairs(int n) {
        if(n ==0)
            return 0;

       Map m = new HashMap<Integer, Integer>();
        Map<Integer, String> ms = new HashMap<>();

         return helper(n, m);
    }

    private static int helper(int n, Map m) {
        //steps 1 or 2;
        // base case  in n = 1 => 1 , if n = 2 => 2
        if(m.get(n) != null){
            return (int)m.get(n);
        }
        if(n == 1 ||  n == 2) {
            return n;
        }else {
            int ans = helper(n-1, m) + helper(n-2, m);
            m.put(n, ans);
            return ans;
        }
    }

}
