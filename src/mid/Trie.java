package mid;

class Trie {
    Node [] dic;
    public Trie() {
         dic = new Node [26];
    }
    
    public void insert(String word) {
        inserHelp(word, 0, dic);
    }

    public void inserHelp(String word, int i, Node [] dic){
        Node current = dic[word.charAt(i)-'a'];
        boolean isFound = false;
        if(i == word.length()-1){
            isFound = true;
        }
        if (dic[word.charAt(i)-'a'] == null){
            Node temp = new Node();
        }
    }
    
    public boolean search(String word) {
        return false;
    }
    
    public boolean startsWith(String prefix) {
        return false;
    }
}

class Node {
    
    boolean isFound;
    Node [] dic;

    Node() {
        this.isFound = false;
        this.dic = new Node[26];
    }
    Node(boolean isFound) {
        this.isFound = isFound;
        this.dic = new Node[26];
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */