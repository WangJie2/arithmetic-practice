package arithmetic;

/**
 * 题目:**字符串转换整数
 * 描述:
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，qing返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * <p>
 * 示例 1:
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 * <p>
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 * <p>
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 * <p>
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * 示例 5:
 * <p>
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 * 因此返回 INT_MIN (−231) 。
 * Created by WangJie on 2018/12/07.
 */
public class Arithmetic8 {

    public static void main(String[] args) {
     /*   char[] chars = "-  +0123456789".toCharArray();
        for (char aChar : chars) {
            int num = (int) aChar;
            System.out.println(num);
        }*/

        System.out.println(myAtoi("1"));
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("20000000000000000000"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("   -w42"));
    }

    public static int myAtoi(String str) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        Long result = 0L;
        for (char aChar : chars) {
            //空格：32  减号：45  加号：43   0-9：48-57
            int num = (int) aChar;
            if (sb.length() > 0) {
                if (num > 47 && num < 58) {
                    sb.append(aChar);
                } else {
                    break;
                }
            } else {
                if (aChar == 32) {
                    continue;
                } else if (aChar == 45 || aChar == 43 || (num > 47 && num < 58)) {
                    sb.append(aChar);
                } else {
                    break;
                }
            }
            if (!"-".equals(sb.toString()) && !"+".equals(sb.toString())) {
                result = Long.valueOf(sb.toString());
                if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                    result = Math.max(Math.min(result, Integer.MAX_VALUE), Integer.MIN_VALUE);
                    break;
                }
            }
        }
        return result.intValue();


    }
    //========================================官方解决方案无==========================================


}


