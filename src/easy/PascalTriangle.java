package easy;

import java.util.LinkedList;
import java.util.List;

public class PascalTriangle {
    public static void main (String[] args) {
        System.out.println(generate(6));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new LinkedList();

        for(int i = 1; i <= numRows; i++){
            LinkedList temp = new LinkedList<Integer>();
            for(int j = 0; j < i; j++) {
                if(j == 0) {
                    temp.add(1);
                } else if(j == i-1) {
                    temp.add(1);
                }else {
                    List<Integer> last = ans.get(i-2);
                    temp.add(last.get(j-1) + last.get(j));
                }
            }
            ans.add(temp);
        }

        return ans;
    }

}
