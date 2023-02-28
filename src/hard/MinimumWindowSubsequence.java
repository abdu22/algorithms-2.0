package hard;

public class MinimumWindowSubsequence {
    public static void main(String[] args) {
        MinimumWindowSubsequence obj = new MinimumWindowSubsequence();
        System.out.println(obj.minWindow("abcdebdde", "bde"));
        System.out.println(obj.minWindow("jmeqksfrsdcmsiwvaovztaqenprpvnbstl", "u"));
        System.out.println(obj.minWindow("abcdebdde", "bde"));
    }
    /*        i
                  k
       s1 = "abcdebdde", 
                j
       s2 = "bde"
     */
    public String minWindow(String s1, String s2) {
        
     int [] minWindow = new int []  {Integer.MAX_VALUE, -1, -1};

        for(int i=0; i<s1.length(); i++) {

            if(s1.charAt(i) == s2.charAt(0)){
                int  k = i; int j =0; 

                while(k < s1.length() && j <s2.length()){
                    if(s1.charAt(k) == s2.charAt(j)){
                         j++;
                    }
                        k++;  
                }
                if(j == s2.length()){ // all found
                    if(minWindow[0] > k-i){
                        minWindow[0] = k-i;
                        minWindow[1] = i;
                        minWindow[2] = k;
                    }
                }else{
                    break;
                }

            }
           
        }
        if(minWindow[1] == -1) return "";
         return s1.substring(minWindow[1], minWindow[2]);
    }
}
