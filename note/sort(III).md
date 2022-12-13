# 复杂排序（II）

## 堆

数组表示的完全二叉树。

- i的左孩子下标： 2i+1
- i的右孩子下标： 2i+2
- i的父节点下标： (i-1)/2
- 大顶堆： 在这棵树里每一个节点都比它的任何子树中的节点大
- 小顶堆： 在这棵树里每一个节点都比它的任何子树中的节点小

大顶堆代码：

```java
public class Heap {

    private int[] heap;
    private int heapSize; // 堆大小
    private int count; // 当前元素数目

    Heap(int heapSize) {
        this.heapSize = heapSize;
        heap = new int[heapSize];
    }

    public void swap(int[] a, int i, int j) {
        int x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    // index坐标的元素上浮
    public void up(int index) {
        if (index < count) {
            while (heap[index] > heap[(index - 1) / 2]) {
                // 当前值比父节点大 上浮
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }
    }

    // 往堆里插入a
    public void heapInsert(int a) {
        if (count < heapSize) {
            heap[count++] = a;
            up(count - 1);
        }
    }

    // index坐标元素下沉
    public void down(int index) {
        int left = index * 2 + 1;
        // 如果有左孩子 也就是如果有孩子
        while (left < count) {
            int right = index * 2 + 2;
            // 如果有右孩子且右孩子比左孩子大 则选出右孩子
            int largestIndex = right < count && heap[right] > heap[left] ? right : left;
            // 父亲和最大孩子比较 取得最大值的下标
            largestIndex = heap[index] > heap[largestIndex] ? index : largestIndex;
            // 如果父亲是最大的了 没有必要下移了
            if (largestIndex == index) break;
            // 大孩子比父亲大 交换
            swap(heap, largestIndex, index);
            // 往下走 重复循环
            index = largestIndex;
            left = index * 2 + 1;
        }
    }

    public int getMax() {
        return heap[0];
    }

    // 返回最大值 并且在堆里删除
    public int popMax() {
        int res = heap[0];
        // 最后一个元素放到根节点 总元素数量--
        heap[0] = heap[--count];
        // 下沉该元素
        down(0);
        return res;
    }

    public void printHeap() {
        for (int i = 0; i < count; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }


}

```

堆排序：

用heapInsert数组元素建堆，一个个弹出最大元素即可，注意是顺序还是逆序

```java
class Solution {
    public static void heapSort(int[] a) {
        if (a == null || a.length < 2) return;
        int n = a.length;
        Heap h = new Heap(n);
        for (int i = 0; i < n; i++) {
            h.heapInsert(a[i]);
        }
        for (int i = 0; i < n; i++) {
            a[n - i - 1] = h.popMax();
        }

    }

    public int[] sortArray(int[] nums) {
        heapSort(nums);
        return nums;
    }
}
```

时间复杂度是O(NlogN)

## 堆的应用： 几乎有序的数组排序

> 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离可以不超过k，并且k相对于数组来说比较小。请选择一个合适的排序算法针对这个数据进行排序。

举个例子，假设k=6，我们初始化一个大小为7的小顶堆，把0~6这七个元素装入堆， 顶部元素也就是最小值要放到0，任何数排完序后，下标最多+6或-6，所以7和7后面的数 排完序后是不可能到0的，所以排在0上的一定是堆里的，且是根。那么顶部元素
放到0后，下标7的元素进堆，同理，顶部弹出到1位置，以此类推，堆就类似于 划动窗口，直到堆的右边界和数组边界重合后，直接一个个弹即可。

这里Java里PriorityQueue就是小顶堆 不用自己实现了

```java

class Solution {
    public void almostInOrder(int[] a, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        // k << a.length
        int index = 0;
        while (index <= k) {
            // 把前k+1个数放进堆
            heap.add(a[index++]);
        }
        // 此时index是堆的右边界(开区间)
        int i = 0;
        while (index < a.length) {
            // 直到堆右边界和数组右边界重合停下
            heap.add(a[index++]);
            a[i++] = heap.poll();
        }
        while (!heap.isEmpty()) {
            a[i++] = heap.poll();
        }
    }
}

```

# 桶排序

## 计数排序

计数排序应用比较窄，类似统计词频，比如员工年龄一定是18~60，那么有个员工年龄 数组[20,21,18,...]，需要排序，那么就整个数组a[60-18+1],a[0]代表18岁员工的
个数，以此类推，那么最后有了这个频度统计，剩下的顺序就是还原回去。

## 基数排序

举个例子 假设数组是[100,72,13,25,17],排序，我们准备10个桶（队列，10进制数就10个桶） 第一步，按最多位数补齐所有数，[100,072,013,025,017]

第二步，按个位数进桶：0号桶{100},2号桶{72},3号桶{13},5号桶{25}, 7号桶{17}

第三步，从左往右把数字从桶里倒出来 [100,072,013,025,017]

第四步，按十位数进桶：0号桶{100}，1号桶{013,017},2号桶{025},7号桶{072}

第五步，从左往右把数字从桶里倒出来，这里注意桶是队列，先进先出
[100,013,017,025,072]

第六步， 按百位数进桶： 0号桶{013,017,025,072},1号桶{100}，这里其实可以看到0号桶已经按 十位数和个位数有序了

第七步， 从左往右把数字从桶里倒出来 [013,017,025,072,100]，结束

可以看到核心就是10进制准备10个队列，从低位到高位入桶，从左桶到右桶出。 因为高位的影响力肯定是最大的，所以越高的位排序越靠后。

**只能用于非负整数**

```java

class RadixSort {
    private static int maxBits(int[] a) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            max = Math.max(max, a[i]);
        }
        int res = 0;
        while (max > 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    // 获得n的第i位数，个位为第0位
    private static int getCertainDigit(int n, int i) {
        int res = 0; // n总共多少位
        int b = n;
        while (b > 0) {
            res++;
            b /= 10;
        }
        n %= Math.pow(10, i + 1);
        n /= Math.pow(10, i);
        return n;
    }

    public static void radixSort(int[] a) {
        if (a == null || a.length < 2) return;
        List<Deque<Integer>> queues = new ArrayList<>();
        final int radix = 10;
        List<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < radix; i++) {
            queues.add(new ArrayDeque<>());
        }
        int n = a.length;
        // 找出数组中位数最多的数的位数
        int maxB = maxBits(a);
        // 有多少位就进出多少次
        for (int i = 0; i < maxB; i++) {
            // a[j]按照第i位的数字进桶
            for (int j = 0; j < n; j++) {
                queues.get(getCertainDigit(a[j], i)).addLast(a[j]);
            }
            // 从左到右出桶
            for (int j = 0; j < radix; j++) {
                while (!queues.get(j).isEmpty()) {
                    tmp.add(queues.get(j).pollFirst());
                }
            }
            for (int j = 0; j < n; j++) {
                a[j] = tmp.get(j);
            }
            tmp.clear();
        }

    }
}
```

## 稳定性

同样值的个体之间，如果不因为排序而改变相对次序，就是这个排序是有稳定性的;否则就没有。

不具备稳定性的排序:

选择排序、快速排序、堆排序

具备稳定性的排序:

冒泡排序、插入排序、归并排序、一切桶排序思想下的排序

目前没有找到时间复杂度O(N*logN)，额外空间复杂度0(1)，又稳定的排序

## 总结

| 排序算法 |   时间复杂度   | 空间复杂度   | 稳定性 |
|------|:---------:|---------|-----|
| 选择排序 |  O(N^2)   | O(1)    | ×   |
| 冒泡排序 |  O(N^2)   | O(1)    | √   |
| 插入排序 |  O(N^2)   | O(1)    | √   |
| 归并排序 | O(N*logN) | O(N)    | √   |
| 快速排序 | O(N*logN) | O(logN) | ×   |
| 堆排序  | O(N*logN) | O(1)    | ×   |
