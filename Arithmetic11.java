package arithmetic;

/**
 * 题目:**盛最多水的容器
 * 描述:
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * Created by WangJie on 2019/5/6.
 */
public class Arithmetic11 {

    public static void main(String[] args) {
        int[] a = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(a));

    }

    public static int maxArea(int[] height) {
        int maxArea = 0;
        if (height != null || height.length > 1) {
            for (int i = 0; i < height.length; i++) {
                int left = 0;
                int right = 0;
                int heigh = height[i];
                for (int j = height.length - 1; j > i; j--) {
                    if (height[j] >= heigh) {
                        right = j - i;
                        break;
                    }
                }
                if (right < i) {
                    for (int k = 0; k < i; k++) {
                        if (height[k] >= heigh) {
                            left = i - k;
                            break;
                        }
                    }
                }
                maxArea = Math.max(maxArea, Math.max(left, right) * heigh);
            }
        }
        return maxArea;
    }

    //========================================官方解决方案==========================================

    /**
     * 方法一：暴力法
     *
     * @param height
     * @return
     */
    public static int maxArea1(int[] height) {
        int maxarea = 0;
        for (int i = 0; i < height.length; i++)
            for (int j = i + 1; j < height.length; j++)
                maxarea = Math.max(maxarea, Math.min(height[i], height[j]) * (j - i));
        return maxarea;
    }

    /**
     * 方法二：双指针法
     * 算法
     * 这种方法背后的思路在于，两线段之间形成的区域总是会受到其中较短那条长度的限制。此外，两线段距离越远，得到的面积就越大。
     * 我们在由线段长度构成的数组中使用两个指针，一个放在开始，一个置于末尾。 此外，我们会使用变量 maxareamaxarea 来持续存储到目前为止所获得的最大面积。
     * 在每一步中，我们会找出指针所指向的两条线段形成的区域，更新 maxareamaxarea，并将指向较短线段的指针向较长线段那端移动一步。
     * <p>
     * 这种方法如何工作？
     * <p>
     * 最初我们考虑由最外围两条线段构成的区域。现在，为了使面积最大化，我们需要考虑更长的两条线段之间的区域。
     * 如果我们试图将指向较长线段的指针向内侧移动，矩形区域的面积将受限于较短的线段而不会获得任何增加。
     * 但是，在同样的条件下，移动指向较短线段的指针尽管造成了矩形宽度的减小，但却可能会有助于面积的增大。
     * 因为移动较短线段的指针会得到一条相对较长的线段，这可以克服由宽度减小而引起的面积减小。
     *
     * @param height
     * @return
     */
    public static int maxArea2(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }

}
