package leetcode.editor.cn;
//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.暴力破解，循环遍历第一个字符串，在第二个字符串中找到对应的字符，将其置为0；
 * 2.hashMap存放元素和下标，循环查看是否有对应的元素在第二个元素中；
 * 3.数组
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution242 {
    public boolean isAnagram(String s, String t) {
        // 1.暴力求解
        /*boolean returnFlag = false;
        char[] sChars = getChars(s.getBytes());
        char[] tChars = getChars(t.getBytes());
        if (sChars.length != tChars.length){
            return false;
        }
        for (int i = 0; i < sChars.length; i++) {
            for (int j = 0; j < tChars.length; j++) {
                if (tChars[j] ==  sChars[i]){
                    tChars[j]='0';
                    break;
                }
            }
        }
        try{
            if (Integer.valueOf("".equals(String.valueOf(tChars))?"0":String.valueOf(tChars)) == 0){
                returnFlag = true;
            }
        }catch(NumberFormatException e){
            returnFlag = false;
        }
        return returnFlag;
    }
    private char[] getChars (byte[] bytes) {
        Charset cs = Charset.forName("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }*/
        // 2.hashMap
        boolean flag = true;
        Map allElementMap = new HashMap<>();
        byte[] sBytes = s.getBytes();
        byte[] tBytes = t.getBytes();
        for (int i = 0;i < sBytes.length;i++){
            if (allElementMap.containsKey(sBytes[i])){
                allElementMap.put(sBytes[i],(Integer)allElementMap.get(sBytes[i]) + 1);
            }else{
                allElementMap.put(sBytes[i],1);
            }
        }
        for (int i = 0;i < tBytes.length;i++){
            if (allElementMap.containsKey(tBytes[i])){
                allElementMap.put(tBytes[i],(Integer)allElementMap.get(tBytes[i])-1);
            }else{
                flag = false;
            }
        }
        for(Object key : allElementMap.keySet()) {
            Integer value = (Integer)allElementMap.get(key);
            if (value != 0){
                flag=false;
            }
        }
    return flag;
        /*if (s.length() != t.length()){
            return false;
        }
        int[] result = new int[26];
        for (int i = 0;i < s.length(); i++){
            result[s.charAt(i)-'a']++;
            result[t.charAt(i)-'a']--;
        }
        for (int i : result){
            if (i != 0){
                return false;
            }
        }
        return true;*/
        /*if (s.length() != t.length()){
            return false;
        }
        int[] result = new int[26];
        for (int i = 0;i < s.length(); i++){
            result[s.charAt(i)-'a']++;
        }
        for (int i = 0;i < s.length(); i++){
            result[t.charAt(i)-'a']--;
            if (result[t.charAt(i)-'a'] < 0){
                return false;
            }
        }
        return true;*/
        if (s.length() != t.length()){
            return false;
        }
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        Arrays.sort(sChar);
        Arrays.sort(tChar);
        return Arrays.equals(sChar,tChar);

    }
}
//leetcode submit region end(Prohibit modification and deletion)
