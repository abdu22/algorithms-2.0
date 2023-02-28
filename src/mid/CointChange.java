package mid;

import java.util.Arrays;
import java.util.PriorityQueue;

import com.apple.concurrent.Dispatch.Priority;

public class CointChange {
    /* 
       [1,2,4,5] amount = 8; 
       5 +  2 + 1 = 8
       4 + 4 = 8;

        coins = [1,2,5], amount = 11 ===> 3
        5 + 5 + 1 = 11

   11 ->     5 : 11-5 --> 6  -> 5  -> 6-5 ---> 1  ->  5
                                                      2
                                                      1 - 1 = 0 = 3
                                          
                                2
                                1
             2
             1
     */
    public static void main(String[] args) {
        CointChange obj = new CointChange();
       // System.out.println(obj.coinChange( new int [] {5, 2, 1}, 11));
       // System.out.println(obj.coinChange( new int [] {1,2,4,5}, 8));
        System.out.println(obj.coinChange( new int [] {186,419,83,408}, 6249));
    }

    public int coinChange(int[] coins, int amount) {
        int [] m = new int [amount+1];
        Arrays.fill(m, -2);
        // -2 is unvisited
        return dp(coins, amount, m); 
    }

    public int dp(int[] coins, int amount, int[] m){
        if(amount < 0)  return -1;
        if(amount == 0) return 0;

        if(m[amount] != -2) return m[amount];

        int ans = Integer.MAX_VALUE;

        for(int i=0; i<coins.length; i++){
            int temp = dp(coins, amount-coins[i], m);
            if(temp == -1) continue;
            ans = Math.min(ans, temp+1);
        }
        
        if(ans == Integer.MAX_VALUE) ans = -1;
        // m[i] = -1  is visited but invalid
        // m[i] = -2  is unvisited

        m[amount] = ans;
        return ans;
    }

}
