import java.util.LinkedList;
import java.util.List;

public class PlayGround {
    public static void main(String[] args) {



        List temp = new LinkedList<Integer>();
        String s = "ab :CD12 3.";
        boolean isAlphaNumerics = s.chars().allMatch(Character::isLetterOrDigit);

       String newS =  s.replaceAll("[^a-zA-Z0-9]", "");

        System.out.println(newS);
        int x = Integer.max(2,4);
        Math.max(2, 4); // abs(a)
        int n = 9;


       /*

       Char
       ====================
        char c = s.charAt(0);
        char c2 = s.charAt(2);
        int sum = (c - '0') + (c2-'0');
        int i = Integer.parseInt(String.valueOf(c));

        System.out.println(c);
        System.out.println(c2);
        System.out.println(sum);
        System.out.println(i);*/

    }
}
