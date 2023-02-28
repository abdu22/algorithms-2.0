package mid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfMatchingSubsequence {
    public static void main(String[] args) {
        
    }
    /* 
       s = "aafbcdea",   
       words = ["a","ff","acd","ace", "abca"]

      
       {abca,0}
       {ade,0}
       {a,0}     {ff,0}
        ^         ^
       [|         |            ]
        a b c d e f . . . . . z


        for(i=0 ---> s.length ) 
             charAt = a 
             a = List< {a,0}, {ade, 0}, {abca, 0}>
              a is exausted -> ans ++;
              distribu them where they belong

           {abca,1}  {ade, 1} {ff,1}
                  ^   ^       ^
               [  |   |       |       ]
                a b c d e f . . . . . z

                (ff,1}  , it will never be 2, won't cound.


     */

    public int numMatchingSubseq(String s, String[] words) {
        int ans = 0;
        
        Map<Character, List<WordPair>> dict = new HashMap<>();

        for(String w:words){
            if(!dict.containsKey(w.charAt(0)))
                 dict.put(w.charAt(0), new ArrayList<>());
            
            dict.get(w.charAt(0)).add(new WordPair(w, 0)); 
        }

        for(int i=0; i<s.length(); i++){
            Character c  = s.charAt(i);

            if(!dict.containsKey(c))
                     continue;

            List<WordPair> curr = dict.get(c);   
            dict.remove(c);
             
            for(WordPair pair:curr){
                if(pair.idx == pair.word.length()-1){
                    // we find a word that exausted
                    ans++;
                    continue;
                }
                pair.idx++;
                Character newC = pair.word.charAt(pair.idx);
                if(!dict.containsKey(newC))
                      dict.put(newC, new ArrayList<>());
                
                dict.get(newC).add(new WordPair(pair.word, pair.idx));      
            }
        }
     
       return ans;  
    }

}

class WordPair {
    String word;
    int idx;
     WordPair(String word, int idx){
        this.word = word;
        this.idx = idx;
     }
}
