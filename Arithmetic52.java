package arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目:***N皇后II
 * 描述:
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 * 示例 1:
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * Created by WangJie on 2019/02/12.
 */
public class Arithmetic52 {

    public static void main(String[] args) {
        System.out.println(totalNQueens(4));
    }

    public static int totalNQueens(int n) {
        List<int[][]> result = new ArrayList<>();
        int[][] board = new int[n][n];
        setQueen(0, n, board, result);
        return result.size();
    }

    /**
     * 检查是否可以落子
     *
     * @param x
     * @param y
     * @param size
     * @param board
     * @return
     */
    public static boolean check(int x, int y, int size, int[][] board) {
        for (int i = 0; i < size; i++) {
            //检查横向 和 纵向
            if (board[x][i] == 1 || board[i][y] == 1) {
                return false;
            }
        }
        int x_copy_up = x, x_copy_down = x, y_copy_l = y, y_copy_r = y;
        while (y_copy_r < size - 1 || y_copy_l > 0) {
            x_copy_up--;
            x_copy_down++;
            //y右边斜线检查
            if (y_copy_r < size - 1) {
                y_copy_r++;
                if ((x_copy_up >= 0 && board[x_copy_up][y_copy_r] == 1) || (x_copy_down < size && board[x_copy_down][y_copy_r] == 1)) {
                    return false;
                }
            }
            //y左边斜线检查
            if (y_copy_l > 0) {
                y_copy_l--;
                if ((x_copy_up >= 0 && board[x_copy_up][y_copy_l] == 1) || (x_copy_down < size && board[x_copy_down][y_copy_l] == 1)) {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * 每一行落子
     *
     * @param x
     * @param size
     * @param board
     * @param result
     * @return
     */
    public static boolean setQueen(int x, int size, int[][] board, List<int[][]> result) {
        if (x == size) {
            int[][] cp = new int[size][size];
            for (int i = 0; i < board.length; i++) {
                cp[i] = Arrays.copyOf(board[i], board[i].length);
            }
            result.add(cp);
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (check(x, i, size, board)) {
                board[x][i] = 1;
                if (setQueen(x + 1, size, board, result)) {
                    return true;
                }
            }
            //还原为0
            board[x][i] = 0;
        }
        return false;
    }
    //========================================官方解决方案无==========================================


}


