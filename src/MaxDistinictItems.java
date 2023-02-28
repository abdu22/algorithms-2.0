import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MaxDistinictItems {
    public static void main(String[] args) {
        int[] arr = {1,3,8};

        List<Integer> list = new ArrayList<>(Arrays.asList(1,3,8));

        Set<Integer> set = new HashSet<>(list);
        int k = 10; // dollar amount 
        int n = 10;
        System.out.println(findMaxDistinctItems(n, k, list));
    }

    public static int findMaxDistinctItems(int n, int k, List<Integer> arr) { 
        int max = 0;
        int distinct = 0;
        int i = 0;
        int j = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while(j < arr.size()) {
            int current = arr.get(j);
            map.put(current, map.getOrDefault(current, 0) + 1);
            if(map.get(current) == 1) {
                distinct++;
            }
            if(distinct > n) {
                int left = arr.get(i);
                map.put(left, map.get(left) - 1);
                if(map.get(left) == 0) {
                    distinct--;
                }
                i++;
            }
            max = Math.max(max, j - i + 1);
            j++;
        }
        return max;
    }

}
