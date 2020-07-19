//给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。 
//
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。 
//
// 示例 1: 
//
// 
//输入: "abc"
//输出: 3
//解释: 三个回文子串: "a", "b", "c".
// 
//
// 示例 2: 
//
// 
//输入: "aaa"
//输出: 6
//说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
// 
//
// 注意: 
//
// 
// 输入的字符串长度不会超过1000。 
// 
// Related Topics 字符串 动态规划 
// 👍 282 👎 0

package leetcode.editor.cn;

public class PalindromicSubstrings {
  public static void main(String[] args) {
    Solution solution = new PalindromicSubstrings().new Solution();
  }

  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // dp[i][j] = dp[i+1][j-1] && str[i]==str[j]
    public int countSubstrings(String s) {
      int e = s.length();
      int returnNum = 0;
      boolean[][] dp = new boolean[e][e];
      for (int i = 0; i < e; i++) {
        for (int j = i; j >= 0; j--) {
          if (s.charAt(i) == s.charAt(j) && ((i - j < 2) || (dp[j + 1][i - 1]))) {
            dp[j][i] = true;
            returnNum++;
          }
        }
      }
      return returnNum;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)

}