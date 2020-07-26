//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法 
// 👍 1192 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
  public static void main(String[] args) {
    int n = 3;
    Solution solution = new GenerateParentheses().new Solution();
    List<String> strings = solution.generateParenthesis(n);
    System.out.println(strings);
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
      dfs(n, n, "");
      return res;
    }

    private void dfs(int nl, int nr, String str) {
      if (nl == 0 && nr == 0) {
        res.add(str);
        return;
      }
      if (nl > 0) {
        dfs(nl - 1, nr, str + "(");
      }
      if (nr > nl) {
        dfs(nl, nr - 1, str + ")");
      }
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}