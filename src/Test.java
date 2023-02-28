public class Test {
    public static void main(String[] args) {
        
        String [] array =  new String [4];

        String input = "aabccddd" ; // =   //2a1b2c3d
        String input2 = "aabccddde" ; // =   //2a1b2c3d

        System.out.println( "For inpiut : " + input + "  : Compressed : "  + compressString(input));
        System.out.println("For inpiut : " + input2 + "  : Compressed : "  + compressString(input2));  //2a1b2c3d1e
        System.out.println("For inpiut : " + "" + "  : Compressed : "  + compressString(""));  //""
        System.out.println("For inpiut : " + null + "  : Compressed : "  + compressString(null)); //""
        System.out.println("For inpiut : " + "aabccddda" + "  : Compressed : "  + compressString("aabccddda")); //  2ab2c3d1a


    }

/*
                         i
     // String str = " a a b ccddde a a ";  
     
     o/p - > 2a1b2c3d 

            i jM
             a a b cc ddd
       time : O(n)
       space : O (n)   
                     i   j
       a a b c c ddd a

*/


    public static String compressString(String word) {
        if(word == null || word.length() == 0) return "";
        String ans = ""; 
        int i = 0; int j = 0;

        int count = 1;
        while(i < word.length() && j< word.length()) {
             count = 1;
             j = i + 1;
             while(i < word.length() && j < word.length()) {
                Character curre =  word.charAt(j);
                Character prev =  word.charAt(j-1);
                if( curre != prev) {
                    ans += count + ""+ word.charAt(j-1);
                    i = j;
                    break;  
                }
                j++;
                count++;
             }
        }

        if(j == word.length()) {
            ans += count + "" + word.charAt(j-1);
        }

         return ans;
    }
}
