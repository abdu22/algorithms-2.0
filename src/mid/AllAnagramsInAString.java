package mid;

import java.util.ArrayList;
import java.util.List;

public class AllAnagramsInAString {

    /*       i
              j
        s = "cbaebabacd"
        p = "abc"
             k

        [1, 1, 1. 0 0 . . . . . .0]
        0                        26
     */
    public static void main(String[] args) {
        AllAnagramsInAString obj = new AllAnagramsInAString();
        System.out.println(obj.findAnagrams("abab", "ab"));
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();

        if(p.length() == 0) return ans;

        int [] dic = new int [26];
        for(char c:p.toCharArray()){
            dic[c-'a']++;
        }

        for(int i=0; i <= s.length()-p.length(); i++) {
            int [] temp =  dic.clone();
            int j = i;
            int pSize = p.length();
            //int k = 0;
             while(j < s.length()  && pSize > 0){
                if(temp[s.charAt(j)-'a'] > 0) {
                    temp[s.charAt(j)-'a']--;
                    j++;
                    pSize--;
                }else{
                    break;
                }
            }
            if( j-i == p.length()){
                ans.add(i);
            } 
        }
    return ans;
    }
}
