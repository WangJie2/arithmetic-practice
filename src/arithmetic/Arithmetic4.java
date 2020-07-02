package arithmetic;

import java.util.Arrays;

/**
 * 题目:***两个排序数组的中位数
 * 描述:
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
 * 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
 * 你可以假设 nums1 和 nums2 不同时为空。
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 中位数是 2.0
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 中位数是 (2 + 3)/2 = 2.5
 * Created by WangJie on 2018/9/20.
 */
public class Arithmetic4 {

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(findMedianSortedArrays1(nums1, nums2));
        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println(findMedianSortedArrays1(nums3, nums4));

    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] s = new int[nums1.length+nums2.length];
        System.arraycopy(nums1, 0,s,0,nums1.length);
        System.arraycopy(nums2, 0,s,nums1.length,nums2.length);
        Arrays.sort(s);
        int i = s.length % 2;
        int j = s.length / 2;
        if(i==0){
            return (s[j-1] + s[j]) / 2.0;
        }else{
            return s[j];
        }
    }

    //========================================官方解决方案==========================================
    /**
     *       left_part          |        right_part
     A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
     B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
     * @param A 1 5 6  9 10
     * @param B 2 6 7  8 9 10
     * @return
     */
    public static double findMedianSortedArrays1(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

}
