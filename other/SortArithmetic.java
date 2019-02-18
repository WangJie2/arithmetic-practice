package arithmetic.other;

import org.junit.Test;

import java.util.*;

/**
 * 十大经典排序
 * https://www.cnblogs.com/onepixel/articles/7674659.html
 * Created by WangJie on 2019/2/13.
 */
public class SortArithmetic {
    public static void main(String[] args) {
        int[] array = {5, 9, 1, 21, 43, 62, 12, 48, 88, 58, 54};
        System.out.println(Arrays.toString(array));
//        bubbleSort(array);
//        selectionSort(array);
//        insertionSort(array);
//        shellSort(array);
//        mergeSort(array, 0, 1);
//        quickSort(array, 0, array.length - 1);
//        heapSort(array);
//        countingSort(array);
//        bucketSort(array);
        radixSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 比较希尔排序和插入排序时间
     */
    @Test
    public void testSort() {
        int length = 6000;
        int[] array1 = new int[length];
        int[] array2 = new int[length];
        Random random = new Random();
        for (int i = 0; i < array1.length; i++) {
            int nextInt = random.nextInt(10000);
            array1[i] = nextInt;
            array2[i] = nextInt;
        }
        long l = System.currentTimeMillis();
        insertionSort(array1);
        long l1 = System.currentTimeMillis();
        shellSort(array2);
        long l2 = System.currentTimeMillis();
        System.out.println("插入排序时间：" + (l1 - l));
        System.out.println("希尔排序时间：" + (l2 - l1));
    }

    /**
     * 1.冒泡排序（Bubble Sort） O(n^2)  O(1) 稳定
     * 冒泡排序是一种简单的排序算法。
     * 它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。
     * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
     * 这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
     * <p>
     * 算法描述
     * 1.比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 3.针对所有的元素重复以上的步骤，除了最后一个；
     * 4.重复步骤1~3，直到排序完成。
     *
     * @param array
     */
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    /**
     * 2.选择排序（Selection Sort） O(n^2)  O(1) 不稳定
     * 选择排序(Selection-sort)是一种简单直观的排序算法。
     * 它的工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
     * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 以此类推，直到所有元素均排序完毕。
     * <p>
     * 算法描述
     * n个记录的直接选择排序可经过n-1趟直接选择排序得到有序结果。具体算法描述如下：
     * 初始状态：无序区为R[1..n]，有序区为空；
     * 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
     * n-1趟结束，数组有序化了。
     *
     * @param array
     */
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }

    /**
     * 3.插入排序（Insertion Sort）  O(n^2)  O(1) 稳定
     * 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。
     * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     * <p>
     * 算法描述
     * 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
     * 从第一个元素开始，该元素可以认为已经被排序；
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * 将新元素插入到该位置后；
     * 重复步骤2~5。
     *
     * @param array
     */
    public static void insertionSort(int[] array) {
   /*     for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j > 0 && array[j] < array[j - 1]; j--) {
                swap(array, j, j - 1);
            }
        }*/
        for (int i = 0; i < array.length - 1; i++) {
            int rightIndex = i + 1;
            int temp = array[rightIndex];
            while (rightIndex >= 1 && temp < array[rightIndex - 1]) {
                array[rightIndex] = array[rightIndex - 1];
                rightIndex--;
            }
            array[rightIndex] = temp;
        }
    }

    /**
     * 4.希尔排序（Shell Sort） O(n^1.3)  O(1) 不稳定
     * 1959年Shell发明，第一个突破O(n^2)的排序算法，是简单插入排序的改进版。
     * 它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
     * 希尔排序（Shell Sort）
     * <p>
     * 算法描述
     * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，具体算法描述：
     * 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
     * 按增量序列个数k，对序列进行k 趟排序；
     * 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
     * 仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     *
     * @param array
     */
    public static void shellSort(int[] array) {
        int increment = array.length;
        do {
            increment = increment / 3 + 1;
            for (int i = 0; i < array.length - increment; i++) {
                int rightIndex = i + increment;
                int temp = array[rightIndex];
                while (rightIndex >= increment && temp < array[rightIndex - increment]) {
                    array[rightIndex] = array[rightIndex - increment];
                    rightIndex -= increment;
                }
                array[rightIndex] = temp;
            }
        } while (increment > 1);
    }

    /**
     * 5.归并排序（Merge Sort）  O(nlog2n)  O(n) 稳定
     * 归并排序是建立在归并操作上的一种有效的排序算法。
     * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
     * 将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。
     * 若将两个有序表合并成一个有序表，称为2-路归并。
     * <p>
     * 算法描述
     * 把长度为n的输入序列分成两个长度为n/2的子序列；
     * 对这两个子序列分别采用归并排序；
     * 将两个排序好的子序列合并成一个最终的排序序列。
     *
     * @param a
     * @param s
     * @param len
     */
    public static void mergeSort(int[] a, int s, int len) {
        int size = a.length;
        int mid = size / (len << 1);
        int c = size & ((len << 1) - 1);
        //　-------归并到只剩一个有序集合的时候结束算法-------//
        if (mid == 0)
            return;
        //　------进行一趟归并排序-------//
        for (int i = 0; i < mid; ++i) {
            s = i * 2 * len;
            merge(a, s, s + len, (len << 1) + s - 1);
        }
        //　-------将剩下的数和倒数一个有序集合归并-------//
        if (c != 0)
            merge(a, size - c - 2 * len, size - c, size - 1);
        //　-------递归执行下一趟归并排序------//
        mergeSort(a, 0, 2 * len);
    }

    /**
     * 　　<pre>
     * 　　二路归并
     * 　　原理：将两个有序表合并和一个有序表
     * 　　</pre>
     * <p>
     * 　　@param　a
     * 　　@param　s
     * 　　第一个有序表的起始下标
     * 　　@param　m
     * 　　第二个有序表的起始下标
     * 　　@param　t
     * 　　第二个有序表的结束下标
     */
    private static void merge(int[] a, int s, int m, int t) {
        int[] tmp = new int[t - s + 1];
        int i = s, j = m, k = 0;
        while (i < m && j <= t) {
            if (a[i] <= a[j]) {
                tmp[k] = a[i];
                k++;
                i++;
            } else {
                tmp[k] = a[j];
                j++;
                k++;
            }
        }
        while (i < m) {
            tmp[k] = a[i];
            i++;
            k++;
        }
        while (j <= t) {
            tmp[k] = a[j];
            j++;
            k++;
        }
        System.arraycopy(tmp, 0, a, s, tmp.length);
    }

    /**
     * 6.快速排序（Quick Sort）  O(nlog2n)  O(nlog2n) 不稳定
     * 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，
     * 其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
     * <p>
     * 算法描述
     * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。具体算法描述如下：
     * 从数列中挑出一个元素，称为 “基准”（pivot）；
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     *
     * @param arr
     * @param _left
     * @param _right
     */
    public static void quickSort(int arr[], int _left, int _right) {
        int left = _left;
        int right = _right;
        int temp = 0;
        if (left <= right) {   //待排序的元素至少有两个的情况
            temp = arr[left];  //待排序的第一个元素作为基准元素
            while (left != right) {   //从左右两边交替扫描，直到left = right
                while (right > left && arr[right] >= temp) {
                    right--;        //从右往左扫描，找到第一个比基准元素小的元素
                }
                arr[left] = arr[right];  //找到这种元素arr[right]后与arr[left]交换

                while (left < right && arr[left] <= temp) {
                    left++;         //从左往右扫描，找到第一个比基准元素大的元素
                }
                arr[right] = arr[left];  //找到这种元素arr[left]后，与arr[right]交换
            }
            arr[right] = temp;    //基准元素归位
            quickSort(arr, _left, left - 1);  //对基准元素左边的元;素进行递归排序
            quickSort(arr, right + 1, _right);  //对基准元素右边的进行递归排序
        }
    }

    /**
     * 7.堆排序（Heap Sort）  O(nlog2n)  O(1) 不稳定
     * 堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。
     * 堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。
     * <p>
     * 算法描述
     * 将初始待排序关键字序列(R1,R2….Rn)构建成大顶堆，此堆为初始的无序区；
     * 将堆顶元素R[1]与最后一个元素R[n]交换，此时得到新的无序区(R1,R2,……Rn-1)和新的有序区(Rn),且满足R[1,2…n-1]<=R[n]；
     * 由于交换后新的堆顶R[1]可能违反堆的性质，因此需要对当前无序区(R1,R2,……Rn-1)调整为新堆，
     * 然后再次将R[1]与无序区最后一个元素交换，得到新的无序区(R1,R2….Rn-2)和新的有序区(Rn-1,Rn)。
     * 不断重复此过程直到有序区的元素个数为n-1，则整个排序过程完成。
     *
     * @param arr
     */
    public static void heapSort(int arr[]) {
        //1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, j);//重新对堆进行调整
        }
    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && arr[k] < arr[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (arr[k] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    /**
     * 8.计数排序（Counting Sort）   O(n+k)  O(n+k) 稳定
     * 计数排序不是基于比较的排序算法，其核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。
     * 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数。
     * <p>
     * 算法描述
     * 找出待排序的数组中最大和最小的元素；
     * 统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
     * 对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
     * 反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。
     *
     * @param arr
     */
    public static void countingSort(int arr[]) {
        // 计算最大最小值，严谨实现最好用ifPresent检查下
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int N = arr.length;
        int R = max - min + 1; // 最大最小元素之间范围[min, max]的长度
        // 1. 计算频率，在需要的数组长度上额外加1
        int[] count = new int[R + 1];
        for (int i = 0; i < N; i++) {
            // 使用加1后的索引，有重复的该位置就自增
            count[arr[i] - min + 1]++;
        }
        // 2. 频率 -> 元素的开始索引
        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }

        // 3. 元素按照开始索引分类，用到一个和待排数组一样大临时数组存放数据
        int[] aux = new int[N];
        for (int i = 0; i < N; i++) {
            // 填充一个数据后，自增，以便相同的数据可以填到下一个空位
            aux[count[arr[i] - min]++] = arr[i];
        }
        // 4. 数据回写
        for (int i = 0; i < N; i++) {
            arr[i] = aux[i];
        }
    }

    /**
     * 9.桶排序（Bucket Sort）   O(n+k)  O(n+k) 稳定
     * 桶排序是计数排序的升级版。它利用了函数的映射关系，高效与否的关键就在于这个映射函数的确定。
     * 桶排序 (Bucket sort)的工作的原理：假设输入数据服从均匀分布，将数据分到有限数量的桶里，
     * 每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排）。
     * <p>
     * 算法描述
     * 设置一个定量的数组当作空桶；
     * 遍历输入数据，并且把数据一个一个放到对应的桶里去；
     * 对每个不是空的桶进行排序；
     * 从不是空的桶里把排好序的数据拼接起来。
     *
     * @param arr
     */
    public static void bucketSort(int arr[]) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        //桶数
        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }

        //将每个元素放入桶
        for (int i = 0; i < arr.length; i++) {
            int num = (arr[i] - min) / (arr.length);
            bucketArr.get(num).add(arr[i]);
        }

        //对每个桶进行排序
        for (int i = 0; i < bucketArr.size(); i++) {
            Collections.sort(bucketArr.get(i));
        }
        int j = 0;
        for (ArrayList<Integer> integers : bucketArr) {
            for (Integer integer : integers) {
                arr[j] = integer;
                j++;
            }
        }
    }

    /**
     * 10.基数排序（Radix Sort） O(n*k)  O(n+k) 稳定
     * 基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；
     * 依次类推，直到最高位。有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。
     * 最后的次序就是高优先级高的在前，高优先级相同的低优先级高的在前。
     * <p>
     * 算法描述
     * 取得数组中的最大数，并取得位数；
     * arr为原始数组，从最低位开始取每个位组成radix数组；
     * 对radix进行计数排序（利用计数排序适用于小范围数的特点）；
     *
     * @param array
     */
    public static void radixSort(int array[]) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) { //找到数组中的最大值
            if (array[i] > max) {
                max = array[i];
            }
        }
        int keysNum = 0; //关键字的个数，我们使用个位、十位、百位...当做关键字，所以关键字的个数就是最大值的位数
        while (max > 0) {
            max /= 10;
            keysNum++;
        }
        List<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++) { //每位可能的数字为0~9，所以设置10个桶
            buckets.add(new ArrayList<Integer>()); //桶由ArrayList<Integer>构成
        }
        for (int i = 0; i < keysNum; i++) { //由最次关键字开始，依次按照关键字进行分配
            for (int j = 0; j < array.length; j++) { //扫描所有数组元素，将元素分配到对应的桶中
                //取出该元素对应第i+1位上的数字，比如258，现在要取出十位上的数字，258%100=58,58/10=5
                int key = array[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                buckets.get(key).add(array[j]); //将该元素放入关键字为key的桶中
            }
            //分配完之后，将桶中的元素依次复制回数组
            int counter = 0; //元素计数器
            for (int j = 0; j < 10; j++) {
                ArrayList<Integer> bucket = buckets.get(j); //关键字为j的桶
                while (bucket.size() > 0) {
                    array[counter++] = bucket.remove(0); //将桶中的第一个元素复制到数组，并移除
                }
            }
        }
    }


    public static void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
