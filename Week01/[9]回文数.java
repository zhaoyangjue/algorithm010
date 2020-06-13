package leetcode.editor.cn;//判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
//
// 示例 1: 
//
// 输入: 121
//输出: true
// 
//
// 示例 2: 
//
// 输入: -121
//输出: false
//解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
// 
//
// 示例 3: 
//
// 输入: 10
//输出: false
//解释: 从右向左读, 为 01 。因此它不是一个回文数。
// 
//
// 进阶: 
//
// 你能不将整数转为字符串来解决这个问题吗？ 
// Related Topics 数学


//leetcode submit region begin(Prohibit modification and deletion)
class Solution009 {
    public boolean isPalindrome(int x) {
       /* String tempX = x+"";
        boolean flag = true;
        byte[] tempByte = tempX.getBytes();
        for (int i = 0; i < tempByte.length;i++){
            if (tempByte[i]!=tempByte[tempByte.length-1-i] && i != tempByte.length-1-i){
                flag = false;
            }
        }
        return flag;*/
        if (x < 0 ||(x % 10 == 0 && x != 0)){
            return false;
        }
        int tempNums = 0;
        while (x > tempNums){
            tempNums = tempNums * 10 + x % 10;
            x /= 10;
        }
        return x==tempNums || x == tempNums/10;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
