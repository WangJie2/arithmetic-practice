package arithmetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 题目:**N字型变换
 * 描述:
 * 将字符串 "PAYPALISHIRING" 以Z字形排列成给定的行数：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后从左往右，逐行读取字符："PAHNAPLSIIGYIR"
 * 实现一个将字符串进行指定行数变换的函数:
 * string convert(string s, int numRows);
 * 示例 1:
 * 输入: s = "PAYPALISHIRING", numRows = 3
 * 输出: "PAHNAPLSIIGYIR"
 * 示例 2:
 * 输入: s = "PAYPALISHIRING", numRows = 4
 * 输出: "PINALSIGYAHRPI"
 * 解释:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * Created by WangJie on 2018/10/24.
 */
public class Arithmetic6 {

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        System.out.println(convert(s, numRows));
        s = "PAYPALISHIRING";
        numRows = 4;
        System.out.println(convert(s, numRows));

    }

    public static String convert(String s, int numRows) {
        if (null != s && numRows > 1) {
            Map<Integer, StringBuilder> map = new HashMap<>();
            boolean flag = true;//标记是竖还是斜
            for (int i = 0, row = 0; i < s.length(); i++) {
                if (map.get(row) == null) {
                    map.put(row, new StringBuilder());
                }
                map.get(row).append(s.charAt(i));
                if (row == numRows - 1) {//切换斜
                    flag = false;
                } else if (row == 0) {//切换竖
                    flag = true;
                }
                if (flag) {
                    row++;
                } else {
                    row--;
                }
            }
            return map.values().stream().map(StringBuilder::toString).collect(Collectors.joining());
        } else {
            return s;
        }
    }

   /* public static String convert(String s, int numRows) {
        if (null != s && numRows > 1) {
            StringBuilder sb = new StringBuilder();
            //定义一个二维数组
            Character[][] array = new Character[numRows][s.length()];
            boolean flag = true;//标记是竖还是斜
            for (int i = 0, x = 0, y = 0; i < s.length(); i++) {
                array[x][y] = s.charAt(i);
                if (x == numRows - 1) {//切换斜
                    flag = false;
                } else if (x == 0) {//切换竖
                    flag = true;
                }
                if (flag) {
                    x++;
                } else {
                    x--;
                    y++;
                }
            }
            for (int x = 0; x < numRows; x++) {
                for (int y = 0; y < s.length(); y++) {
                    if (null != array[x][y]) {
                        sb.append(array[x][y]);
                    }
                }
            }
            return sb.toString();
        } else {
            return s;
        }
    }*/


    //========================================官方解决方案==========================================

    public static String convert1(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }

    /**
     * 方法二：按行访问
     * 思路
     * 按照与逐行读取 Z 字形图案相同的顺序访问字符串。
     * 算法
     * 首先访问 行 0 中的所有字符，接着访问 行 1，然后 行 2，依此类推...
     * 对于所有整数 kk，
     * 行 00 中的字符位于索引 k \; (2 \cdot \text{numRows} - 2)k(2⋅numRows−2) 处;
     * 行 \text{numRows}-1numRows−1 中的字符位于索引 k \; (2 \cdot \text{numRows} - 2) + \text{numRows} - 1k(2⋅numRows−2)+numRows−1 处;
     * 内部的 行 ii 中的字符位于索引 k \; (2 \cdot \text{numRows}-2)+ik(2⋅numRows−2)+i 以及 (k+1)(2 \cdot \text{numRows}-2)- i(k+1)(2⋅numRows−2)−i 处;
     *
     * @param s
     * @param numRows
     * @return
     */
    public static String convert2(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }
}
