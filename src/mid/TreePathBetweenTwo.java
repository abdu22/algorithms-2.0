package mid;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TreePathBetweenTwo {
    /* 
        [5,1,2,3,null,6,4]
     */
    public static void main(String[] args) {
        TreePathBetweenTwo o = new TreePathBetweenTwo();

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(4);

        System.out.println(o.getDirections(root, 3, 6));

    }
    Map<TreeNode, TreeNode> parent;
    TreeNode start;
    public String getDirections(TreeNode root, int startValue, int destValue) {
        parent = new HashMap<>();
        // building parenta and find values
        traverseAll(root, startValue);

        Queue<PairBi> q = new LinkedList<>();
        q.add(new PairBi(start, ""));

        while(!q.isEmpty()){
            int size = q.size();
            while(size > 0) {
                PairBi current = q.poll();
                if(current.node.val == destValue){
                    return current.path;
                }
                if(current.node.left != null)
                    q.offer(new PairBi(current.node.left, current.path + "L"));

                 if(current.node.right != null)
                     q.offer(new PairBi(current.node.right, current.path + "R"));

                 if(parent.containsKey(current.node))
                     q.offer(new PairBi(parent.get(current.node), current.path + "U"));       
            }
        }
    return "";
    }
    public void traverseAll(TreeNode root, int startValue){
        if(root == null) return;
        if(root.val == startValue) start = root;

        if(root.left != null) parent.put(root.left, root);
        if(root.right != null) parent.put(root.right, root);
        traverseAll(root.left, startValue);
        traverseAll(root.right, startValue);
    }
}

class PairBi{
    TreeNode node;
    String path;

    PairBi(TreeNode node, String path){
        this.node = node;
        this.path = path;
    }
}
