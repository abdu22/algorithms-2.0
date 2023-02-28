package mid;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/* 
[3,9,20,null,null,15,7] 
             3
          /     \
        9         20
      /   \      /   \      
   8        0|15       7
                 \   /
                  4|5     
  Ans = [  
          [8]
          [9]
          [3,0,5]
          [20, 4, 5] 
         ]
 */
 class P {
    int level;
    TreeNode r;
    P(int level, TreeNode r){
        this.level = level;
        this.r = r;
    }
 }
public class VirticalOrder {
    public static void main(String[] args) {
         TreeNode r = new TreeNode(3);
        r.left = new TreeNode(9);
        r.right = new TreeNode(20);
        r.left.left = new TreeNode(8);
        r.left.right = new TreeNode(0);
        r.right.left = new TreeNode(15);
        r.right.right = new TreeNode(7);
        r.right.left.right = new TreeNode(4);
        r.right.right.left = new TreeNode(5);

       /*  TreeNode r = new TreeNode(1);
        r.left = new TreeNode(2); */
         
        VirticalOrder obj = new VirticalOrder();

        List<List<Integer>> finalAns =  obj.verticalOrder(r);
    }

    List<List<Integer>> ans;
    int leftIdx = 0;
    int rightIdx = 0;

    public List<List<Integer>> verticalOrder(TreeNode root) {
        ans = new ArrayList<>();

        // find left and right end

        findEndToEndIdx(root, 0);
        for(int i=leftIdx ;  i<=rightIdx; i++){
            ans.add(new ArrayList<>());
        }
        int rootIdx =  Math.abs(leftIdx) ;
        Queue<P> q = new LinkedList<>();
        q.add(new P(rootIdx, root));

        while(q.size() > 0){
            int size = q.size();

            while(size > 0) {
                size--;
                P tempPair = q.poll();
                ans.get(tempPair.level).add(tempPair.r.val);

                if(tempPair.r.left != null)
                    q.add(new P(tempPair.level-1, tempPair.r.left));
                if(tempPair.r.right != null)
                    q.add(new P(tempPair.level +1, tempPair.r.right));    
            }
        }
        return ans;
    }



    public void findEndToEndIdx(TreeNode r, int i){
        if(r == null) return;
        if(i < leftIdx) leftIdx = i;
        if(i > rightIdx) rightIdx = i;
        findEndToEndIdx(r.left, i-1);
        findEndToEndIdx(r.right, i+1);
    }
}
