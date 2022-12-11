package com.xzc.learn;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

/**
 * @Author: ZCXu1
 * @Date: 2022/11/24 10:29
 * @Version: 1.0.0
 * @Description:
 */
public class Sort {
    /**
     * 生成指定范围内的随机数
     * @param min 最小整数
     * @param max 最大整数
     * @return 范围内随机数
     */
    private static int randomInt(int min, int max){
        return new Random().nextInt(max)%(max-min+1)+min;
    }

    public static void swap(int[] arr, int i, int j) {

        int a = arr[i];
        arr[i] = arr[j];
        arr[j] = a;

    }


    // 选择排序 每一轮确定把本轮最小的放在最左边
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            swap(arr, minIndex, i);
        }
    }

    // 冒泡排序 通过相邻的交换 每一轮把大的往右放
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        boolean swapped;
        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    // 插入排序 第i轮已知0~i-1 通过相邻比较和交换 让0~i有序
    public static void insertSort(int[] a) {
        if (a == null || a.length < 2) return;
        int n = a.length;
        // 0~0有序
        // 0~i-1有序，接下来让0~i有序
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0 && a[j] > a[j + 1]; j--) {
                swap(a, j, j + 1);
            }
        }

    }

    // 归并排序 分为左侧和右侧数组 分别排序 然后用双指针合并起来 O(NlogN)
    // 归并排序思路可以用在数组中求一个数右边有几个数比它小/大
    public static void mergeSort(int[] a) {
        if (a == null || a.length < 2) return;
        process(a, 0, a.length - 1);
    }

    private static void process(int[] a, int l, int r) {
        if (l == r) return;
        int mid = l + ((r - l) >> 1);
        process(a, l, mid);
        process(a, mid + 1, r);
        merge(a, l, mid, r);
    }

    private static void merge(int[] a, int l, int mid, int r) {
        // 两个部分 l~mid和mid+1到r都有序了
        int[] help = new int[r - l + 1];
        int i = 0; // pointer of help[]
        int p1 = l; // pointer of left
        int p2 = mid + 1; // pointer of right
        while (p1 <= mid && p2 <= r) {
            help[i++] = a[p1] <= a[p2] ? a[p1++] : a[p2++];
        }
        while (p1 <= mid) {
            help[i++] = a[p1++];
        }
        while (p2 <= r) {
            help[i++] = a[p2++];
        }
        for (i = 0; i < r - l + 1; i++) {
            a[l + i] = help[i];
        }
    }
    // 快速排序
    public static void quickSort(int[] a){
        // 普通的快排中划分是前面是<=区域A 后面是> 区域
        // 方法是双指针 一个指针是A区域右边界（包含），一个是遍历数组的i
        // 当a[i]<=k的时候 A区域右边第一个元素和a[i]交换 并且A右边界扩充， i++
        // 当a[i]>k的时候 i++
        // 但是快排可以被三指针的荷兰国旗问题优化 因为荷兰国旗一次搞定一批等于k的数
        // 选最后一个数当k 划分k左边的部分 然后和=区域的右边界后第一个也就是>区域的第一个交换
        // 避免最差情况 要随机选一个数和最后交换
        if (a == null || a.length < 2) return;
        helper(a,0,a.length - 1);
    }
    private static void helper(int[] a, int l, int r){
        if (l < r){
            int k = randomInt(l,r);
            swap(a,k,r);
            int[] p = partition(a,l,r);
            helper(a,l,p[0]-1);
            helper(a,p[1]+1,r);
        }
    }
    // 返回=区域的左边界和右边界（左右都是闭区间）
    private static int[] partition(int[] a, int l, int r){
        // 取最后一个数做基准
        // 划分a[r]前面的部分
        // 荷兰国旗 三指针
        int p1 = l - 1, p2 = r; // 划分pivot前面的部分 所以边界不是r+1是r
        for (int i = l; i < p2;){
            if (a[i] < a[r]){
                swap(a,i++,++p1);
            }else if (a[i] == a[r]){
                i++;
            }else{
                swap(a,i,--p2);
            }
        }
        // [<,<,<,....,<,=,=,...,=,>,>,....>,pivot]
        // [.........,p1,.........,p2,......,r]
        swap(a,p2,r);
        // [<,<,<,....,<,=,=,...,=,pivot,>,....>]
        // [.........,p1,.........,p2    ,......]
        return new int[]{p1+1,p2};

    }




    public static void main(String[] args) {
        int[] a = new int[]{10, 9, 8, 5, 8, 1, 2};
        int[] b = new int[]{5,2,3,1};
        quickSort(a);
        quickSort(b);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }
}
