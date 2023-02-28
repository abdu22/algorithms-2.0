package hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

public class MinWindowSubstring {

     /* 
       mOrig = A:1, B:1, C:1
       trackMap = 
        A: 1, 0,   1
        B: 1,    2    1
        C: 1,

       //counter for map match/all found 

       int trackCount = 0; if trackCount == mOrig.size() -> Two maps are equal
             0 1 2 3 4 5 6 7 8 9 10 11 12 
                         i->
       s = " A D O B E C O D E B  A  N  C "
                                  j->
       t = "A B C "
                j++ or i++
       trackCount ++  or -- = 1-> 2 ->3 -> 2 -> 3 -> 2 -> 3

        a
        a
        i
        j

        m
        a:1

        a:1
       

        if(oOrig == trackMap or trackCount == mOrig.size()), check minWindow
          ans = 6x / 4 (BANC)


     */
    public static void main(String[] args) {
        MinWindowSubstring obj = new MinWindowSubstring();
        String ans = obj.minWindow("ADOBECODEBANC", "ABC");
        //String ans2 = obj.minWindow("a", "a");
        //String ans3 = obj.minWindow(s1, s2);

        //System.out.println(ans);
        //System.out.println(ans2);
        System.out.println(ans);
    }
   
   
     public String minWindow(String s, String t) {
        Map<Character, Integer> mOrig = new HashMap<>();
        if(s.length() < t.length()) return "";
        
        for(Character c:t.toCharArray()){
            if(!mOrig.containsKey(c)){
                mOrig.put(c, 0);
            }
            mOrig.put(c, mOrig.get(c)+1);
        }

        int [] minWindow = new int [] {Integer.MAX_VALUE, -1, -1}; //at 0 : lengh, at 1 : first idx, at2 : second index

        Map<Character, Integer>  trackMap = new HashMap<>();
        int trackCount = 0;
        int i=0;
        int j=0;
        while(j < s.length() && i<=j){
            Character c = s.charAt(j);
            if(!mOrig.containsKey(c)){
                //skip this char
                j++;
                continue;
            }
            if(!trackMap.containsKey(c))
                trackMap.put(c, 0);

            trackMap.put(c, trackMap.get(c)+1);
            if(trackMap.get(c).intValue() == mOrig.get(c).intValue()){
                trackCount++;
            }

            // if mOrig == trackMap
            if( trackCount == mOrig.size()){
                if(minWindow[0] > j-i+1){
                    updateNewWindow(minWindow, i, j);
                    //System.out.println(s.substring(minWindow[1], minWindow[2]+1));
                }
                 // minimize the window
                while(i<=j){
                     if(minWindow[0] > j-i+1){
                        updateNewWindow(minWindow, i, j);
                        //System.out.println(s.substring(minWindow[1], minWindow[2]+1));
                    }
                    i++;
                    Character prevC = s.charAt(i-1);
                    if(trackMap.containsKey(prevC)){
                        // if found on trackMap m, deduct --
                        trackMap.put(prevC, trackMap.get(prevC)-1);
                         // if map is different from original, break.
                        if(trackMap.get(prevC) < mOrig.get(prevC)){
                            trackCount--;
                            j++;
                            break;
                        }
                    }
                }
            }else{
                j++;
            }    

        }

        if(minWindow[1] == -1) return "";

        return s.substring(minWindow[1], minWindow[2]+1);
    }
    public int[] updateNewWindow(int [] minWindow, int i, int j){
                    minWindow[0] = j-i+1;
                    minWindow[1] = i;
                    minWindow[2] = j;
                    return minWindow;
    }
}
