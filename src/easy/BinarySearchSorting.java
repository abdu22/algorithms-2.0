package easy;

import java.util.Arrays;

public class BinarySearchSorting {
    public static void main(String [] args){

        int [] a = {8,4,1,3,11,4,1,2,5};
        int [][] c = new int [3][3];

        Arrays.stream(c).forEach(x -> Arrays.fill(x, -1));




        /*
            8,4,1,3                 11,4,1,2,5
         8,4     1,3              11,4,   1,2,5
         8  4

         4,8
         4,8    1,3
          1,3,4,8
 Integer
        1,3,4,8                    1,2,4,5,11
Integer
                 1,1,2,3,4,4,5,8,11
     */
        int [] sorted = sort(a);

        System.out.println(Arrays.toString(sort(a)));
    }

    private static int[] sort(int[] nums ){
       return helpSort(nums, 0, nums.length-1);
    }
    private static int[] helpSort(int[] nums, int s, int e){
        if(s == e){
            return new int [] {nums[s]};
        }
        int m = (s + e) / 2;
       int [] left =  helpSort(nums, s, m);
       int [] right = helpSort(nums,m+1,e);
        // merge sorted array
      return merge(left, right);
    }
    private static int [] merge(int[] left, int [] right){
        int [] ans = new int [left.length + right.length];
        int i = 0; int j = 0;
        int k=0;

        while(i < left.length &&  j < right.length){
            if(left[i] <= right[j]){
                ans[k] = left[i];
                i++;
            }else {
                ans[k] = right[j];
                j++;
            }
            k++;
        }
        while(i < left.length){
            ans[k] = left[i];
            k++;
            i++;
        }
        while(j < right.length){
            ans[k] = right[j];
            k++;
            j++;
        }
        return ans;
    }
}
