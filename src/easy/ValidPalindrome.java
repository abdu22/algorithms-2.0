package easy;

public class ValidPalindrome {

    public static void main(String[] args) {
        System.out.println(validP("cupupucu"));
    }

    public static boolean validP(String s) {

        int l = 0 ;  int r=s.length()-1;
        int errorCount = 0;
        while( l <= r) {
            if(s.charAt(l) == s.charAt(r)) {
                l++; r--;
            }else {
                //first error found
                errorCount ++;
                break;
            }
        }

        if(errorCount > 0) {
            boolean leftCheck = helper(s, l+1, r);
            boolean rightCheck = helper(s, l, r-1);

            return leftCheck || rightCheck;
        }

        return true;
    }
    public static boolean helper(String s, int l, int r) {
        while(l <= r) {
            if(s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++; r--;
        }
        return true;
    }

/*
 DOEST work for :    System.out.println(validP("cupupucu"));

 public static boolean validP(String s) {
        int l = 0 ;  int r=s.length()-1;
        int errorCount = 0;
        while( l <= r) {
            if(s.charAt(l) == s.charAt(r)) {
                l++; r--;
            }else {

                if(errorCount > 0 ) {
                    return false;
                }

                if(s.charAt(l+1) == s.charAt(r)){
                    l++;
                    errorCount++;
                } else if(s.charAt(l) == s.charAt(r-1)) {

                    r--;
                    errorCount++;
                } else {
                    return false;
                }
            }
        }
        return true;
    }*/
}
