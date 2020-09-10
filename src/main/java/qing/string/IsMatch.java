package qing.string;

/**
 * description: IsMatch
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * 说明:
 * • s 可能为空，且只包含从 a-z 的小写字母。
 * • p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * @author yq
 * @date 2020/9/10.
 */
public class IsMatch {

    /**
     * 解题失败。。。
     * @param s
     * @param p
     * @return
     */
    public static boolean solution(String s, String p) {
        if(s == null || p == null) {
            return false;
        }
        if("".equals(p) || "".equals(s)) {
            return false;
        }
        //"aab"  "c*a*b"
        int len = s.length();
        int is;
        int ip = 0;
        // 匹配的字符
        char temp = s.charAt(0);
        boolean isDot = false;
        boolean isStar = true;
        for (is = 0; is < len; is++) {
            if(temp != s.charAt(is)) {
                temp = s.charAt(is);
                if (!isDot && p.charAt(ip) == '*') {
                    isStar = false;
                    ip++;
                }
            }
            if(ip >= p.length()) {
                break;
            }
            while (s.charAt(is) != p.charAt(ip)) {
                if(p.charAt(ip) == '*' && isStar) {
                    ip--;
                    break;
                }
                if(p.charAt(ip) == '.') {
                    isDot = true;
                    break;
                }
                if(ip + 1 >= p.length()) {
                    return false;
                }
                if(p.charAt(ip + 1) == '*') {
                    ip += 2;
                    if(ip >= p.length()) {
                        return false;
                    }
                }
            }
            if(is == len-1) {
                break;
            }
            ip++;
            isStar = true;
        }
        return (is == len) && (ip >= p.length()-1);
    }

    /**
     * 动态规划
     * @param s
     * @param p
     * @return
     */
    public static boolean solution2(String s, String p) {
        int[][] matchs = new int[s.length() + 1][p.length()+1];
        matchs[0][0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            matchs[i][0] = -1;
        }
        for (int i = 1; i <= p.length(); i++) {
            matchs[0][i] = -1;
            if (p.charAt(i-1) == '*') {
                matchs[0][i] = matchs[0][i-2];
            }
        }
        int row = 1, col = 1;
        while (row <= s.length()) {
            char sc = s.charAt(row - 1);
            col = 1;
            while (col <= p.length()) {
                char pc = p.charAt(col - 1);
                if (pc == '.' || pc == sc) {
                    matchs[row][col] = matchs[row-1][col-1];
                } else if (pc == '*') {
                    if (matchs[row][col-2] == 1) {
                        matchs[row][col] = 1;
                    } else {
                        if(p.charAt(col - 2) == sc || p.charAt(col - 2) == '.') {
                            matchs[row][col] = matchs[row-1][col];
                        } else {
                            matchs[row][col] = -1;
                        }
                    }
                } else {
                    matchs[row][col] = -1;
                }
                col++;
            }
            row++;
        }
        return matchs[s.length()][p.length()] == 1;
    }

    public static void main(String[] args) {
        System.out.println(solution2("","."));
    }
}
