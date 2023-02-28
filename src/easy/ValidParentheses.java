package easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
*
*
*
*   ( ( [ )
* */

public class ValidParentheses {
    public static void main (String[] args){
        System.out.println("Hello world");
        System.out.println(isValid("({[]}])"));
        System.out.println(isValid("()[]{}{"));
        System.out.println(isValid("()"));
    }

    private static boolean isValid(String s) {
        Map<Character, Character> m = new HashMap<>();
        m.put('(', ')');
        m.put('[', ']');
        m.put('{', '}');

        Stack<Character> stack = new Stack<>();
        for (Character c:s.toCharArray()){
            if(c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }else {
              if( m.get(stack.peek()) == c) {
                  stack.pop();
              }else {
                  return false;
              }
            }
        }
        return stack.size() == 0;
    }
}
