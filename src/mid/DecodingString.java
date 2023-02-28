package mid;

public class DecodingString {
    public static void main(String[] args) {
        DecodingString obj = new DecodingString();

       /* String s1 = "3[a2[c]]"; //accaccacc
       System.out.println(obj.decodeString(s1));

       String s2 =  "2[abc]3[cd]ef";
       System.out.println(obj.decodeString(s2));

       String s3 = "abc3[cd]ef";
       System.out.println(obj.decodeString(s3)); */

       String s4 = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";

       System.out.println(obj.decodeString(s4));

       //   3[cd]
       //     cd 

       
        
        
        
    }
    /*
        -  "3 [ a 2 [ c ] ] "
        -  "2 [ abc ] 3 [ cd ] ef "
        -  "abc 3 [ cd ] ef "
          0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24  25 26 27
        " 3 [ z ] 2 [ 2 [ y ] p  q  4  [   2 [   j  k  ]  e  1 [  f   ]  ]  ]  e  f"

        3zzz + 2 * [ y y + pq + 4 * [ 2 * [jk] e + f] ] + ef
                              pq    4 *    jkjkef jkjkef
                      2 *   yy pq jkjkef jkjkef jkjkef jkjkef jkjkef jkjkef jkjkef jkjkef 
     3zzz yy pq jkjkef jkjkef jkjkef jkjkef jkjkef jkjkef jkjkef jkjkef yy pq jkjkef jkjkef jkjkef jkjkef jkjkef jkjkef jkjkef jkjkef ef
                                          


    */
    public String decodeString(String s) {
       String ans =  helpDecod(s, 0, s.length()-1);
        return ans;
    }
    public String helpDecod(String s, int i, int j){
        if(i == j) return s.substring(i, j+1);
        int multiply = 1;
        String preStr = "";
        int l = i; 
        int r = i;

        for(int k=i; k<=j; k++){
            //if digit
            if(Character.isDigit(s.charAt(k))) {
                 // find end of the digit. == multply
                 for(int x=k+1; x<=j; x++){
                    if(s.charAt(x)== '['){ 
                        String numS = s.substring(k, x);
                        multiply = Integer.parseInt(numS);
                        l = x+1;
                        break;
                    }
                 }
                 //find ], counting nested inclosing to avoid  [a 2[c]]
                 int countOpenParenth = 1;
                 for(int x=l ; x <= j; x++) {
                    if(s.charAt(x)== '['){
                        countOpenParenth++;
                    }else if(s.charAt(x)== ']'){
                        countOpenParenth--;  
                    }
                    if(countOpenParenth == 0){
                        r = x-1;
                        break;
                    }
                 }
                 String nestedStr = helpDecod(s, l, r);
                 for(int x=1 ; x<=multiply ; x++){
                    preStr = preStr + nestedStr;
                 }
                 k = r+1; 
            }else if (Character.isAlphabetic(s.charAt(k))){
                // find end of string
                int x ;
                for( x = k+1; x <= j; x++){
                    if(Character.isDigit(s.charAt(x))){  
                        preStr = preStr + s.substring(k, x);
                        l = x;
                        break;
                    }
                }
                if(x >= j) { // no digit == no multiple
                    preStr = preStr + s.substring(k, j+1);
                    return preStr;
                }
                // find ] or end of string
                for(x=l ; x<= j; x++) { 
                    if(s.charAt(x)== ']'){  
                        r = x;
                        break;
                    }
                }
                String nestedStr = helpDecod(s, l, r);
                preStr = preStr + nestedStr;
                k = r;
            }
        }
        
        return preStr;
    }
}
