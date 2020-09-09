package qing.string;

/**
 * description: LongestPalindrome
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 * @author yq
 * @date 2020/9/9.
 */
public class LongestPalindrome {

    /**
     * 中心扩散法
     * @param s
     * @return
     */
    public static String solution(String s) {
        int len = s.length();
        if(len < 2) {
            return s;
        }
        int start = 0;
        int end = 0;
        int l = 0;
        int r = 0;
        int maxLen = 0;
        //迭代字符串s, 以当前字符c 为中心向左向右顺找回文
        for(int i = 0; i < len-1; i++) {
            int sum = 0;
            // 偶数对称
            if(s.charAt(i) == s.charAt(i+1)) {
                l = i;
                r = i+1;
                while (l >= 0 && r < len) {
                    if(s.charAt(l) != s.charAt(r)) {
                        sum = r - l - 1;
                        break;
                    }
                    l--;
                    r++;
                    // 边界处理
                    if(l < 0 || r >= len) {
                        sum = r - l - 1;
                        break;
                    }
                }
            }
            if(sum > maxLen) {
                maxLen = sum;
                start = l + 1;
                end = r;
            }
            // 奇数对称
            if(i-1 >= 0 && s.charAt(i-1) == s.charAt(i+1)) {
                l = i-1;
                r = i+1;
                while (l >= 0 && r < len) {
                    if(s.charAt(l) != s.charAt(r)) {
                        sum = r - l - 1;
                        break;
                    }
                    l--;
                    r++;
                    if(l < 0 || r >= len) {
                        sum = r - l - 1;
                        break;
                    }
                }
            }
            if(sum > maxLen) {
                maxLen = sum;
                start = l + 1;
                end = r;
            }
        }
        return end == 0 ? s.substring(start, end+1) : s.substring(start, end);
    }

    public static void main(String[] args) {
        String s = "aaaa";
         System.out.println(solution(s));
    }
}
