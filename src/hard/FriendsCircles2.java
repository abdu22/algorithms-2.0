package hard;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FriendsCircles2 {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
          input.add("YYNN");
         input.add("YYYN");
         input.add("NYYN");
         input.add("NNNY"); 
 
        /*  input.add("YNNNN");
         input.add("NYNNN");
         input.add("NNYNN");
         input.add("NNNYN");
         input.add("NNNNY"); */
 
         System.out.println(friendCircles(input));
    }

    static int friendCircles(List<String> friends) {

        //int[] track = new int[friends.size()];
        //Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        int count = 0;

        for (int i = 0; i < friends.size(); i++) { 
            if(!set.contains(i)){
                traverseDFS(friends, set, i);
                count++;
            }
        }

        return count;
    }

    public static void traverseDFS(List<String> friends,  Set<Integer> set, int i){
        String fiend = friends.get(i);
        for(int j=0; j < fiend.length(); j++){
            if(fiend.charAt(j) == 'Y' && !set.contains(j)){
                set.add(j);
                traverseDFS(friends, set, j);
            }
        }
    }
}
