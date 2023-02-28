package easy;

public class IsAbsDifferenceOfTwoArraysWithInTheRange {
    /* 
        
    Java function that takes an array A, an integer K, and two bounds L and R, 
    and returns true if there exists a second array B with elements in the range [L, R] 
    such that the total sum of the absolute values of the differences between the elements of 
    arrays A and B is equal to K:

    For example, given the array A = [8,4, 2, 3] and K = 20, L = 5, R = 10, the function should return true
    because there exists a second array B = [5, 5, 5, 5] such that the total sum of the absolute values of the differences
    between the elements of arrays A and B is equal to K = 20. and the second array b is in the range [L, R].
    
    can you write me the funtion?


     */
    public static void main(String[] args) {
        int [] array = new int [] {8,4,2,3};
        int left = 5; int right = 10;
        System.out.println(doesSecondArrayExistWithInTheRangeAndAbsDifferenceBetweenTheElementsOfArraysAandB(array, left, right, 20));
    }
   
   public static boolean doesSecondArrayExistWithInTheRangeAndAbsDifferenceBetweenTheElementsOfArraysAandB(int [] array, int L, int R, int K){
       int sum = 0;
       for (int i = 0; i < array.length; i++) {
           if(array[i] >= L && array[i] <= R){
               sum += array[i];
           }
       }
       if(sum == K){
           return true;
       }
       return false;
   }
   
}
