package mid;

public class CountCompleteTreeNode {
    int totalDeduction = 0;
    int initalPrediction = 0;
    boolean isEndFound = false;
    int depth = 0;
    public int countNodes(TreeNode root) {
        if(root == null) return 0;

        //find total depth 
        TreeNode temp = root;
        while(temp.left != null){
            initalPrediction += Math.pow(2, depth);
            temp = temp.left;
            depth++;
        }

        initalPrediction += Math.pow(2, depth);
  
        //initalPrediction += 2 ^ depth; 
        
        traversePostOrder(root, 0);
     return initalPrediction - totalDeduction;
    }

    public int traversePostOrder(TreeNode root, int level){
        if(root == null || isEndFound) return 0;
        int right = traversePostOrder(root.right, level + 1);
        int left = traversePostOrder(root.left, level + 1);
        if(level == depth-1){
            if(left + right != 0){
                totalDeduction += left - right;
                isEndFound = true;
            }else{
                totalDeduction += 2;
            }
        }
        return left + right + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        CountCompleteTreeNode obj = new CountCompleteTreeNode();
      System.out.println(obj.countNodes(root));
    }
}
