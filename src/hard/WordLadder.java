package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
    /* 
    beginWord = "hit", endWord = "cog"
        graph
      -------------
       *ht:[hot]
       *ot:[hot, dot, lot]
       h*t:[hot]
       ho*:[hot]
       l*t:[lot]
       lo*:[lot, log]
       *og:[log, cog]
       d*t:[dot]
       do*:[dot, dog]
       d*g: [dog]

       BFS
       [hit] -> *it, h*t, *it => Q[hot]
                                 hot -> *ot, h*t, ho* => Q[dot, lot]
                                         dot -> *ot, d*t, do* => [dog]
                                         lot -> *ot, l*t, lo* => [log] 
                                                              = Q[dog, log]
                                        dog -> *og, d*g, do* => [cog]
                                        log -> *og, l*g, lo* => []
                                                
        set to track already visited words

       [ hit ] 
           [ hot ]
               [ dot, lot ]
                   [ dog, log ] 
                           [ cog ]


       [[hit]] || q = hit
       [[hit, hot]] || q = hot
       [[hit, hot, dot] [hit, hot, lot]] ||  q = [dot, lot] 
       [hit, hot, dot, dog] [hit, hot, lot, log]
       [hit, hot, dot, dog, cog] [hit, hot, lot, log, cog]



     */
    public static void main(String[] args) {
        WordLadder obj = new WordLadder();
        List<String> list = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));

        System.out.println(obj.ladderLength("hit", "cog", list));
    }
    // BFS 
    public int ladderLength(String beginWord, String endWord, List<String> wordList) { 
        Map<String, List<String>> map = new HashMap<>();
        for(String word:wordList){
            for(int i=0; i<word.length(); i++){
                String temp = "" + word.substring(0, i) + "*" + word.substring(i+1, word.length());
                if(!map.containsKey(temp))
                   map.put(temp, new ArrayList<>());
                
                map.get(temp).add(word);   
            }
        }
        Set<String> set = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        set.add(beginWord);
        int levelCounter = 0;
        while(!q.isEmpty()){
            int size = q.size();
            levelCounter++;
            while(size > 0) {
                size--;
                String word = q.poll();
                if(word.equals(endWord)){
                    return levelCounter;
                }
                for(int i=0; i<word.length(); i++ ){
                    String key = "" + word.substring(0, i) + "*" + word.substring(i+1, word.length());
                    if(map.containsKey(key)) {
                        for(String child:map.get(key)){
                            if(!set.contains(child)){
                                q.add(child);
                                set.add(child);
                            }
                        }
                    }
                }
            }
        }

        return 0;
    }
/* 
     DFS expensive
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> map = new HashMap<>();

        for(String word:wordList){
            for(int i=0; i<word.length(); i++){
                String temp = "" + word.substring(0, i) + "*" + word.substring(i+1, word.length());
                if(!map.containsKey(temp))
                   map.put(temp, new ArrayList<>());
                
                map.get(temp).add(word);   
            }
        }

        Set<String> set = new HashSet<>();
        Map<String, Integer> dp = new HashMap<>();
        dp.put(beginWord, 1);

        int ans = Integer.MAX_VALUE; 

        for(int i=0; i<beginWord.length(); i++){
            String current = "" + beginWord.substring(0, i) + "*" + beginWord.substring(i+1, beginWord.length());
            if(map.containsKey(current)){
                int res = traverseDFS(current, endWord, map, set);
                if(res != -1){
                    ans = Math.min(ans, res);
                }
            }
        }
        if(ans == Integer.MAX_VALUE) return 0;
        return ans + 1;
    } */

/*     public int traverseDFS(String currentKey, String endWord,  Map<String, List<String>> map,  Set<String> set){
         
        int length = Integer.MAX_VALUE; 

        for(String word: map.get(currentKey)){
            if(!set.contains(word)){

                if(word.equals(endWord)) {
                    return 1;
                }
                set.add(word);
                for(int i=0; i<word.length(); i++){
                    String current = "" + word.substring(0, i) + "*" + word.substring(i+1, word.length());
                    if(map.containsKey(current)){
                        int res = traverseDFS(current, endWord, map, set);
                        if(res != -1){
                            length = Math.min(res, length);
                        }
                    }
                }
                set.remove(word);
            }
        }
        if(length == Integer.MAX_VALUE) return -1;
        return length + 1;
    } */
}
