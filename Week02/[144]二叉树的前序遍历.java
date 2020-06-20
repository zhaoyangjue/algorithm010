package leetcode.editor.cn;//给定一个二叉树，返回它的 前序 遍历。
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [1,2,3]
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树


//leetcode submit region begin(Prohibit modification and deletion)

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        // List<Integer> returnList = new ArrayList<>();
        // getElement(root, returnList);
        // return returnList;
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> out = new LinkedList<>();
        if (root == null){return out;}
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pollLast();
            out.add(node.val);
            if (node.right != null){
                stack.add(node.right);
            }
            if (node.left != null){
                stack.add(node.left);
            }

        }
        return out;
    }

    // private void getElement(TreeNode root, List<Integer> returnList) {
    //     if (root == null) return;
    //     returnList.add(root.val);
    //     getElement(root.left, returnList);
    //     getElement(root.right, returnList);
    // }
}
//leetcode submit region end(Prohibit modification and deletion)
