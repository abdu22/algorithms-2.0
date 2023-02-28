package mid;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindAndReplaceInString {
   public static void main(String[] args) {
      FindAndReplaceInString o = new FindAndReplaceInString();

      /*      0 1 2 3 4
          s = a b c d e  indices = [2,2] sources = ["cdef","bc"] , targets = ["f","fe"] 
              0 1 2 3

         m 
         
              
          s = a b c d  indices = [0, 2], source = ["ab","ec"],   target = ["eee","ffff"] 
          
          m 
          0: 0
          2: 1

       */

      //System.out.println(o.findReplaceString("vmokgggqzp", new int [] {3,5,1}, new String [] {"kg","ggq","mo"}, new String [] {"s","so","bfr"}));
      System.out.println(o.findReplaceString("abcde", new int [] {2,2}, new String [] {"cdef","bc"}, new String [] {"f","fe"}));

   }

   /* 
            Time complexity = O (m), m is s.length
            SPace complexity = O (m)
   */

   public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) { 
            Map<Integer, Integer> m = new HashMap<>();

            for(int i=0; i<indices.length; i++) {
                  m.put(indices[i], i);
            }

           StringBuilder b = new StringBuilder();

            for(int i=0; i<s.length(); i++) {
                if(!m.containsKey(i)){
                    b.append(s.charAt(i));
                    continue;
                }
                String source = sources[m.get(i)];
                String target = targets[m.get(i)];

                if( i+source.length() > s.length()) { //source is out of the string box
                    b.append(s.charAt(i));
                    continue;
                }
                             
                String subs = s.substring(i, source.length());
                if(!subs.equals(source)){
                    b.append(subs);
                    i += subs.length() - 1;
                 }else{
                    b.append(target);
                    i += target.length() - 1;
                 }

            }
         return b.toString();
   }

   /*
     Time Complexity :  nlogn for sorting
                            n for lookin on indices 
                         == nlong 
   */
   public String findReplaceString2(String s, int[] indices, String[] sources, String[] targets) {
    // build Triplet 
    Triplet [] triplets = new Triplet[indices.length];
    for(int i=0; i<triplets.length; i++){
        triplets[i] = new Triplet(indices[i], sources[i], targets[i]);
    }
    // sort via indices 
    Arrays.sort(triplets, (x,y) ->  x.indx - y.indx);

    String ans = s; 
    int additiIdx = 0; 

    for(int i=0; i<triplets.length; i++) {
        String source = triplets[i].source;
        String target = triplets[i].target;
        int indice = triplets[i].indx;

        if(indice+source.length() > s.length()) 
           continue;

        String subStr = s.substring(indice, indice+source.length());

        if(!subStr.equals(source)) 
               continue;
       
         int startIdx =  indice + additiIdx; // 1, 3+1 = 4
         int endIdx = startIdx + source.length(); //  3, 6

        String newAns = ans.substring(0, startIdx) + target + ans.substring(endIdx, ans.length()); 
        ans = newAns;
        additiIdx += target.length() - source.length();   // 1, 0
    }
    return ans;
  }
}

class Triplet {
    int indx; 
    String source;
    String target;
 
    Triplet(int indx, String source, String target){
        this.source = source;
        this.target = target;
        this.indx = indx;
    }
 }
