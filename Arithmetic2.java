package arithmetic;

import model.ListNode;

/**
 * 题目:**两数相加
 * 描述:
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * Created by WangJie on 2018/9/18.
 */
public class Arithmetic2 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
//        ListNode l1 = new ListNode(5);
//        ListNode l2 = new ListNode(5);
        println(l1);
        println(l2);
        println(addTwoNumbers1(l1, l2));
    }

    /**
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;//总结果
        ListNode pre = null;//记录上一次节点
        int up = 0;//记录进位数逢10进1
        for (; l1 != null || l2 != null || up > 0; l1 = l1 == null ? null : l1.next, l2 = l2 == null ? null : l2.next) {
            int one = l1 == null ? 0 : l1.val;
            int two = l2 == null ? 0 : l2.val;
            int sum = one + two + up;//加和
            up = 0;//加完归0
            if (sum >= 10) {//判断是否满10
                sum = sum - 10;
                up = 1;
            }
            if (pre != null) {//判断是否含有父节点
                pre.next = new ListNode(sum);//创建本次节点
                pre = pre.next;
            }
            if (result == null) {//初始化总结果
                result = new ListNode(sum);
                pre = result;
            }
        }
        return result;
    }

    /**
     * 打印输出
     */
    public static void println(ListNode node) {
        StringBuilder sb = new StringBuilder();
        for (; node != null; node = node.next) {
            sb.append(" -> " + node.val);
        }
        String s = sb.toString();
        if (s.length() > 0) {
            s = s.substring(4);
        }
        System.out.println(s);
    }

    //========================================官方解决方案==========================================

    /**
     * 就像你在纸上计算两个数字的和那样，我们首先从最低有效位也就是列表 l1 和 l2 的表头开始相加。由于每位数字都应当处于 0…9 的范围内，我们计算两个数字的和时可能会出现“溢出”。
     * 例如，5 + 7 = 12。在这种情况下，我们会将当前位的数值设置为 2，并将进位 carry = 1 带入下一次迭代。
     * 进位 carry 必定是 0 或 1，这是因为两个数字相加（考虑到进位）可能出现的最大和为 9 + 9 + 1 = 19。
     * 伪代码如下：
     * 将当前结点初始化为返回列表的哑结点。
     * 将进位 carry 初始化为 0。
     * 将 p 和 q 分别初始化为列表 l1 和 l2 的头部。
     * 遍历列表 l1 和 l2 直至到达它们的尾端。
     * 将 x 设为结点 p 的值。如果 p 已经到达 l1 的末尾，则将其值设置为 0。
     * 将 y 设为结点 q 的值。如果 q 已经到达 l2 的末尾，则将其值设置为 0。
     * 设定 sum = x + y + carry。
     * 更新进位的值，carry = sum / 10。
     * 创建一个数值为 (sum mod 10) 的新结点，并将其设置为当前结点的下一个结点，然后将当前结点前进到下一个结点。
     * 同时，将 pp 和 qq 前进到下一个结点。
     * 检查 carry = 1 是否成立，如果成立，则向返回列表追加一个含有数字 1 的新结点。
     * 返回哑结点的下一个结点。
     * 请注意，我们使用哑结点来简化代码。如果没有哑结点，则必须编写额外的条件语句来初始化表头的值。
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
