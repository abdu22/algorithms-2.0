package hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Trie {
    Trie [] trie;
    String word;
    Trie(){
        this.trie = new Trie [26];
        this.word = null;
    }
}

public class  WordSearchII {
    /* 
       Input: board = 
                       [["o","a","a","n"],
                        ["e","t","a","e"],
                        ["i","h","k","r"],
                        ["i","f","l","v"]], 
                        
       words = ["oath","pea","eat","rain"]
       Output: ["eat","oath"]
     */
    public static void main(String[] args) {
        WordSearchII o = new WordSearchII();
        String [] words = new String [] {"oath","pea","eat","rain"};// oa
        char[][] board = new char [] [] 
            {{'o','a','a','n'},
             {'e','t','a','e'},
             {'i','h','k','r'},
             {'i','f','l','v'}};

         char [][] board2 = new char [][]
         {{'a','b','c','e'},
          {'x','x','c','d'},
          {'x','x','b','a'}};   

         String [] words2 = new String [] {"abc","abcd"};  

         char [][] board3 = new char[][] {
            {'a','b','c'},
            {'a','e','d'},
            {'a','f','g'}
        };
         String [] words3 = new String [] {"abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade"};


        List<String> output = o.findWords(board3, words3);
        for(String word:output)
        System.out.print(word +", ");
    }
/* 
    l = length of max word
    N = words
    M = all cells
    building tier out of words =  N * l  
     
     finding words for each cell = M * 4 *(3 ^ l), 4 is for initial case, the 3 ^ l = we not going back to where we visited
             /-------------------------\            
            ||  total = O(M 4(3 ^ l))  ||
            ||-------------------------||

                                     Trie 
                                   26 [e   o . . r . . ]
                                           |
                                      26 [ a  e   t ]
                                           |
                                  26 [a . .t . . o] 
                                           |
                               26[a. . e . h. ]
                                           |
                                         26 [ i, f , k, h ]      
 */
    Integer [][] directions = new Integer [][] {{0, 1}, {1,0}, {0, -1}, {-1, 0}};
    int [][] memory;
    Character [] trie;
    Set<String> set;
    public List<String> findWords(char[][] board, String[] words) {
       // build trie 
       Trie [] wordTrie = new Trie[26];
       for(String word:words){
         buildTrie(word, wordTrie, 0);
       }

      // for all starting char, check traverse the trie and look for words.
      List<String> ans = new ArrayList<>();
       set = new HashSet<>();
       for(int i=0; i<board.length; i++){
        for(int j=0; j<board[0].length; j++){
            char chr = board[i][j];
            if(wordTrie[chr - 'a'] != null){
                // look up from the wordTrie chain
                memory = new int[board.length][board[0].length];
                dfsTraverseBoard(board, i, j, wordTrie);
            }
         }
       }
        for(String s:set){
            ans.add(s);
        }
        return ans;
    }

    public void dfsTraverseBoard(char[][] board, int r, int c, Trie [] wordTrie){
        if(r < 0 || r >= board.length || c < 0 || c >= board[0].length) return;
        if(memory[r][c] == 1) return; // visited
        memory[r][c] = 1;

        char chr = board[r][c];
        Trie currentTrie = wordTrie[chr-'a'];
        if(currentTrie == null) {
            memory[r][c] = 0;
            return;
        } 
        if(currentTrie.word != null && !set.contains(currentTrie.word)) { 
            set.add(currentTrie.word);
        }
        // traverse 4 directions 
        for(Integer [] direction:directions){
            dfsTraverseBoard(board, r+direction[0], c+direction[1], currentTrie.trie);
        }
        memory[r][c] = 0;
    }

    public void buildTrie(String word, Trie[] wordTrie, int i){
        if(i >= word.length()) return;
        if(wordTrie[word.charAt(i) - 'a'] == null){
            wordTrie[word.charAt(i) - 'a'] = new Trie();
        }
        if(i == word.length()-1) {
            //word found
            wordTrie[word.charAt(i)-'a'].word = word;
            return;
        }else
           buildTrie(word, wordTrie[word.charAt(i)-'a'].trie, i+1);
    }
    // expensive 
    /* 
        N * c * dfs 
            c = constant = for all cell with starting charAt(0), max case all cells are starting position
            dfs = 3 ^ l
        = N * c * (3^l) => worest case N * M (3^l)    
     */
    public List<String> findWords2(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();

        Map<Character, List<Integer[]>> mapIntCharToBoard = new HashMap<>();

        for(String word:words){
           if(!mapIntCharToBoard.containsKey(word.charAt(0)))
               mapIntCharToBoard.put(word.charAt(0), new ArrayList<>());
         }
           
        

        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(mapIntCharToBoard.containsKey(board[i][j]))
                    mapIntCharToBoard.get(board[i][j]).add(new Integer [] {i, j});
            }
        }

        for(int i=0; i<words.length; i++){
            boolean found = false;
             List<Integer []> startingXYs = mapIntCharToBoard.get(words[i].charAt(0));
             for(Integer[] xy :startingXYs) {
                 memory = new int[board.length][board[0].length];
                 found = dfsExploration(board, xy[0], xy[1], words[i], 0, memory);
                if(found) break;
             }
            if(found) ans.add(words[i]);
        } 

        return ans;     
    }


    public boolean dfsExploration(char[][] board , int x, int y, String word, int k, int[][] memory){
        if(x < 0 || x >=board.length || y < 0 || y >= board[0].length) return false;
        if(board[x][y] != word.charAt(k) || memory[x][y] == 1) return false; 

        if(board[x][y] == word.charAt(k) && k == word.length()-1){ // last char
            return true;
        }

        memory[x][y] = 1;
        boolean isFound = false;

        for(Integer[] direction:directions ){
            isFound =  dfsExploration(board, x+direction[0], y+direction[1], word, k+1, memory);
            if(isFound) return true;
        }
        memory[x][y] = 0;
        return isFound;
    }
}
