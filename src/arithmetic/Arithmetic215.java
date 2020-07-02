package arithmetic;

/**
 * 题目:**数组中的第K个最大元素
 * 描述:
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * Created by WangJie on 2019/5/9.
 */
public class Arithmetic215 {

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{5, 2, 4, 1, 3, 6, 0}, 4));
        System.out.println(findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    /**
     * 自己的方法
     * 利最小堆特性
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        int top = 0;
        for (int i = 0; i < nums.length; i++) {
            //如果没满一直加，否则判断顶端是否小于当前值
            if (top < k) {
                upHeap(nums, top++);
            } else {
                if (nums[i] > nums[0]) {
                    nums[0] = nums[i];
                }
                downHeap(nums, 0, top);
            }
        }
        return nums[0];
    }

    public static void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    /**
     * 下浮调整
     *
     * @param arr
     * @param i
     * @param length
     */
    public static void downHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && arr[k] > arr[k + 1]) {//如果左子结点大于右子结点，k指向右子结点
                k++;
            }
            if (arr[k] < temp) {//如果子节点小于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    /**
     * 上浮调整
     *
     * @param arr
     * @param i
     */
    public static void upHeap(int[] arr, int i) {
        int temp = arr[i];//先取出当前元素i
        for (int k = (i - 1) / 2; i > 0; k = (k - 1) / 2) {
            if (temp < arr[k]) {//如果子节点小于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }


    //========================================官方解决方案==========================================


}
