package hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class AutocompleteSystem {
    public static void main(String[] args) {
        String[] sentences = {"i love you", "island","ironman", "i love leetcode"};
        int[] times = {5,3,2,2};
        AutocompleteSystem acs = new AutocompleteSystem(sentences, times);
        System.out.println(acs.input('i'));
        System.out.println(acs.input(' '));
        System.out.println(acs.input('a'));
        System.out.println(acs.input('#'));
        
    }
    
    TrieNode [] root;
    TreeNode currentNode;
    String prefix;

    Map<String, Integer> map;

    public AutocompleteSystem(String[] sentences, int[] times) {
        prefix = "";
        map = new HashMap<>();
        this.root = new TrieNode [27];
        for(int i=0; i<sentences.length ; i++) {
            map.put(sentences[i], times[i]);
            addNewSentence(sentences[i], times[i], null, 0, root);
        }
    }
    
    public List<String> input(char c) {
        List<String> ans = new ArrayList<>();
         if(c == '#') {
            // check if the word already exist, increment the 'times', 
             Integer prevTimes = map.get(prefix);
             Integer times =  prevTimes == null ? 1 : prevTimes + 1;   
            map.put(prefix, times);
            addNewSentence(prefix, times, prevTimes, 0, root);
            prefix = "";
            return ans;
         }
          prefix += c;
          findAllForThePrefix(prefix, 0, root, ans);
          return ans;
    }

    public void findAllForThePrefix(String prefix, int i, TrieNode [] node, List<String> ans) {
        if(i >= prefix.length()) return;


        Character c = prefix.charAt(i);
        int idx = (c == ' ') ? 0 :  c - 'a' + 1;

        TrieNode current = node[idx]; 
        if(current == null) return;

        if(i == prefix.length()-1) {
            // add all to ans
            for(TreeSet<String> list: current.ListOfSentence.values()){
                for(String word:list) {
                    if(ans.size() >= 3) return; 
                     ans.add(word);
                }
            }
      }

        findAllForThePrefix(prefix, i+1, current.children, ans);
    }

    public void addNewSentence(String sentence, int times, Integer prevTimes, int i,  TrieNode [] node){
          if(i >= sentence.length()) return;

          Character c = sentence.charAt(i);
          int idx = (c == ' ') ? 0 :  c - 'a' + 1;

          if(node[idx] == null) 
               node[idx] = new TrieNode(c, new TreeMap<>((x,y) -> y-x), new TrieNode [27]);
        
         TrieNode currentNode =  node[idx];
         TreeMap<Integer, TreeSet<String>> currentTreeMap = currentNode.ListOfSentence;

           if(!currentTreeMap.containsKey(times))
                currentTreeMap.put(times, new TreeSet<>());

         currentTreeMap.get(times).add(sentence);    

         // if prevTime is not null, we need to remove
         if(prevTimes != null && currentTreeMap.containsKey(prevTimes)) {
            TreeSet<String> tempSet = currentTreeMap.get(prevTimes);
            tempSet.remove(sentence);
         }
         
         addNewSentence(sentence, times, prevTimes, i+1 , currentNode.children) ;
    }
}

class TrieNode{
    Character c;
    TreeMap<Integer, TreeSet<String>>  ListOfSentence;
    TrieNode [] children;

    TrieNode(Character c,  TreeMap<Integer, TreeSet<String>> ListOfSentence,  TrieNode [] children){
         this.c = c;
         this.ListOfSentence = ListOfSentence;
         this.children = children;
    }
}
