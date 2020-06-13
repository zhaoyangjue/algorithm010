package leetcode.editor.cn;
//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
//
// 示例 1: 
//
// 输入: 123
//输出: 321
// 
//
// 示例 2: 
//
// 输入: -123
//输出: -321
// 
//
// 示例 3: 
//
// 输入: 120
//输出: 21
// 
//
// 注意: 
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。 
// Related Topics 数学


//leetcode submit region begin(Prohibit modification and deletion)
class Solution007 {
    public int reverse(int x) {
        /*boolean flag = false;
        int returnInt = 0;
        String tempInt = x+"";
        if (x < 0 ){
            flag = true;
            tempInt = x * (-1) +"";
        }

       byte[] tempByte = tempInt.getBytes();
       for (int i = 0;i < tempByte.length;i++){
            byte temp = tempByte[i];
            tempByte[i] = tempByte[tempByte.length-1-i];
            tempByte[tempByte.length-1-i] = temp;
            if (i == tempByte.length-1-i || i+1 == tempByte.length-1-i){
                break;
            }
       }
       if (flag){
           returnInt = Integer.valueOf(new String(tempByte)) * (-1);
       }else{
           returnInt = Integer.valueOf(new String(tempByte));
       }
       return returnInt;*/
        int returnInt = 0;
        try{
            while(x != 0){
                int tempInt = x%10;
                x/=10;
                if (returnInt > Integer.MAX_VALUE/10 || (returnInt == Integer.MAX_VALUE / 10 && tempInt > 7)) return 0;
                if (returnInt < Integer.MIN_VALUE/10 || (returnInt == Integer.MIN_VALUE / 10 && tempInt < -8)) return 0;
                returnInt = returnInt*10+tempInt;
            }
        }catch(Exception e){
            returnInt = 0;
        }
        return returnInt;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
