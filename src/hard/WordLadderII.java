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
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class WordLadderII {

    /* 
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
        WordLadderII obj = new WordLadderII();
        List<String> list = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));
        List<List<String>> ans  = obj.findLadders("hit", "cog", list);
  
        /* 
         won't work for this give. 
         b/c for each level, it gets updated the existed ans and ruin everything
        List<String> list = new ArrayList<>(Arrays.asList("hot","cog","dog","tot","hog","hop","pot","dot"));
        List<List<String>> ans  = obj.findLadders("hot", "dog", list); 
        */

        for(List<String> l:ans){
            for(String word:l){
                System.out.print(word+" -> ");
            }
            System.out.println();
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) { 
       List<List<String>> ans = new ArrayList<>();
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

       boolean isDestinationFound = false;
       
       q.add(beginWord);
       set.add(beginWord);
       ans.add(new ArrayList<>(Arrays.asList(beginWord)));
       

       while(!q.isEmpty()){
        if(isDestinationFound) break;
        int size = q.size();
        while(size > 0) {
            size--;
            String word = q.poll();
            for(int i=0; i<word.length(); i++ ){
                String key = "" + word.substring(0, i) + "*" + word.substring(i+1, word.length());
                if(map.containsKey(key)) {
                    List<String> children = map.get(key).stream().filter(each -> (!set.contains(each) || each.equals(endWord))).collect(Collectors.toList());
                    if(children.isEmpty()) continue;
                    // build possible ans 
                    buildListofLists(word, children, ans);
                    for(String child:children){
                            q.add(child);
                            set.add(child);
                            if(child.equals(endWord)) 
                                 isDestinationFound = true;
                    }
                }
            }
        }
    }
      //clear uneccessary list which is its end word is not the endword
       return ans.stream().filter(list -> list.get(list.size()-1).equals(endWord)).collect(Collectors.toList());
    }

   public void buildListofLists(String parent, List<String> children, List<List<String>> ans){
      for(int i=0; i<ans.size(); i++) {
         List<String> currentList = ans.get(i);
         List<String> temp = new ArrayList<>(currentList);
         if(currentList.get(currentList.size()-1).equals(parent)){
            //if last word is same as parent, multiply it 
            for(int j=0; j<children.size(); j++) {
                if(j==0){
                    // inital, just concat
                    currentList.add(children.get(j));
                }else{
                    // clone it and add it new. 
                    List<String> clone = new ArrayList<>(temp);
                    clone.add(children.get(j));
                    ans.add(clone);
                }
            }
         }
      }
   }
}
