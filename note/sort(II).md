# 复杂排序（I）

## Master公式

计算递归的复杂度
master公式：也叫主定理。它提供了一种通过渐近符号表示递推关系式的方法。

应用Master定理可以很简便的求解递归方程。

`T[n] = a*T[n/b] + O (N^d)`
- T[n]: 母问题规模
- T[n/b]: 子问题规模
- a: 子问题数量
- O(N^d): 母问题除去子问题剩余的时间复杂度
①当d<log(b,a)时，时间复杂度为O(n^(logb a))

②当d=log(b,a)时，时间复杂度为O((n^d)*logn)

③当d>log(b,a)时，时间复杂度为O(n^d)

比如归并排序 a = 2, b = 2,d = 1。根据第二条，时间复杂度是O(nlogn)


## 归并排序

思想是分为左侧和右侧数组 分别排序 然后用双指针合并起来 复杂度为O(NlogN)

**归并排序思路可以用在数组中求一个数右边有几个数比它小/大**，比如逆序对问题。

### 初始算法
```java
// LEETCODE 912. 排序数组
class Solution {
    public void mergeSort(int[] a) {
        if (a == null || a.length < 2) return;
        process(a, 0, a.length - 1);
    }

    private void process(int[] a, int l, int r) {
        if (l == r) return;
        int mid = l + ((r - l) >> 1);
        process(a, l, mid);
        process(a, mid + 1, r);
        merge(a, l, mid, r);
    }

    private void merge(int[] a, int l, int mid, int r) {
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

    public int[] sortArray(int[] nums) {
        mergeSort(nums);
        return nums;
    }
}

```
### 衍生问题：剑指 Offer 51. 数组中的逆序对

> 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

本质就是求数组中每个数的右边有几个数比它小

做归并排序的过程中 统计某个数右边有几个数比它小 排序是一定要做的 这样方便直接算出个数

比如 某个过程中划分到了 [[A,B,C],[D,E]],[[F,G,H],[I,J]]

对于数B而言，合并到A,B的时候是没有看到B右侧的，当A,B和C合并时，检查C是否小于B并把ABC排序，后续再与
D,E合并时，检查D和E中比B小的，并把ABCDE排序，后续和FGHIJ（有序）合并时双指针检查到G＞B，则直接算出后面一共4个小于B的。

要注意两点，一个是注意归并排序要从大到小，因为我们要看一个数右边比它小的

另一个是当双指针指向的两个数相等时，把右边的先放到暂存数组，这样才能保证不漏且答案正确

```java
class Solution {
   // 
    // 逆归并排序过程中求逆序对
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        return helper(nums, 0, nums.length - 1);
    }

    // 把nums的l~r部分做归并排序从大到小并统计逆序对个数
    private int helper(int[] nums, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return helper(nums, l, mid) +
                helper(nums, mid + 1, r) +
                merge(nums, l, mid, r);
    }

    private int merge(int[] nums, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        int res = 0;
        while (p1 <= mid && p2 <= r) {
            if (nums[p1]>nums[p2]){
                temp[i++]=nums[p1++];
                res += r-p2+1;
            }else {
                temp[i++]=nums[p2++];
            }
        }
        while (p1<=mid){
            temp[i++] = nums[p1++];
        }
        while (p2<=r){
            temp[i++] = nums[p2++];
        }
        for (i = 0; i < temp.length;i++){
            nums[l+i] = temp[i];
        }
        return res;
    }
}
```
## 快速排序

### 荷兰国旗问题

荷兰国旗问题，给定一个数组和一个值 把这个数组从左到右划分为小于等于和大于这个值的三个区域

```java

class NetherlandFlag {

    private static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void nf(int[] a,int k){
        // 三指针 分别是左边小于区域A的右边界（包含） 初始是-1
        // 大于区域B的左边界（包含） 初始是n
        // 遍历数组的指针位置 i
        // 算法逻辑是 若a[i] < k，则A边界的右边第一个元素和a[i]交换 A边界右扩，i++
        // 若a[i] = k，则i++
        // 若a[i] > k，则B边界左边第一个元素和a[i]交换 B边界左扩，i保持不动，i不动的原因是数组后边是我们没遍历过的元素 要继续审查
        if (a == null || a.length < 2){
            return;
        }
        int n = a.length;
        int p1  = -1, p2 = n;
        for (int i = 0; i < p2;) {
            if (a[i] < k){
                swap(a,i++,++p1);
            }else if (a[i] == k){
                i++;
            }else{
                swap(a,i,--p2);
            }
        }

    }
    
}

```

### 快速排序3.0
1.0版本是，对数组a[l~r]选择最后一个数a[r]作为基准值，对l~r-1进行划分，分为<=a[r]
和>a[r]两个区域，最后把<=区域边界右边第一个元素也就是>区域内的第一个元素和a[r]交换位置

2.0版本在1.0的基础上改为荷兰国旗问题，划分<，=和>三个区域，最后=区域边界右边第一个元素也就是>区域内的第一个元素和a[r]交换位置

3.0版本要避免最差情况，所以在2.0基础上，划分前要随机选取一个数和最后一个位置的数交换，这样在数学期望上
复杂度是O(NlogN)
```java
class Solution {
    /**
     * 生成指定范围内的随机数
     * @param min 最小整数
     * @param max 最大整数
     * @return 范围内随机数
     */
    private static int randomInt(int min, int max){
        return new Random().nextInt(max)%(max-min+1)+min;
    }
    private  void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    // 快速排序
    public  void quickSort(int[] a){
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
    private  void helper(int[] a, int l, int r){
        if (l < r){
            int k = randomInt(l,r);
            swap(a,k,r);
            int[] p = partition(a,l,r);
            helper(a,l,p[0]-1);
            helper(a,p[1]+1,r);
        }
    }
    private  int[] partition(int[] a, int l, int r){
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

    public int[] sortArray(int[] nums) {
        quickSort(nums);
        return nums;
    }
}
```
