package qing.array;

/**
 * description: Tictactoe
 * 设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
 * 以下是井字游戏的规则：
 * 玩家轮流将字符放入空位（" "）中。
 * 第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。
 * "X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
 *
 * 示例 1：
 * 输入： board = ["O X"," XO","X O"]
 * 输出： "X"
 * 示例 2：
 * 输入： board = ["OOX","XXO","OXO"]
 * 输出： "Draw"
 * 解释： 没有玩家获胜且不存在空位
 * 示例 3：
 * 输入： board = ["OOX","XXO","OX "]
 * 输出： "Pending"
 * 解释： 没有玩家获胜且仍存在空位
 * 提示：
 * 1 <= board.length == board[i].length <= 100
 * 输入一定遵循井字棋规则
 *
 * @author yq
 * @date 2020/7/29.
 */
public class Tictactoe {

    public String solution(String[] board) {
        int len = board.length;
        if(len == 1) {
            return board[0];
        }
        // 是否有空位
        boolean isNullChar = false;
        // 字符’O‘、’X‘ 与len乘积
        int oLen = 'O'*len;
        int xLen = 'X'*len;
        // 左对角线
        int left = 0;
        // 右对角线
        int right = 0;
        for ( int i = 0; i < len; i++) {
            // 行
            int row = 0;
            // 列
            int column = 0;
            for (int j = 0; j < len; j++) {
                row += board[i].charAt(j);
                column += board[j].charAt(i);
                // ''字符为值为32
                if(board[i].charAt(j) == 32) {
                    isNullChar = true;
                }
            }
            if(row == oLen || column == oLen) {
                return "O";
            } else if (row == xLen || column == xLen) {
                return "X";
            }
            left += board[i].charAt(i);
            right += board[i].charAt(len - 1 - i);
        }
        if(left == oLen || right == oLen) {
            return "O";
        } else if(left == xLen || right == xLen) {
            return "X";
        }
        // 没有玩家获胜且仍存在空位
        if (isNullChar) {
            return "Pending";
        }
        // 没有玩家获胜且不存在空位
        return "Draw";
    }

    public static void main(String[] args) {
        Tictactoe tictactoe = new Tictactoe();
        // board = ["O X"," XO","X O"]
        String[] board1 = {"O X"," XO","X O"};
        // board = ["OOX","XXO","OXO"]
        String[] board2 = {"OOX","XXO","OXO"};
        // board = ["OOX","XXO","OX "]
        String[] board3 = {"OOX","XXO","OX "};
        System.out.println(tictactoe.solution(board1));
        System.out.println(tictactoe.solution(board2));
        System.out.println(tictactoe.solution(board3));
    }
}
