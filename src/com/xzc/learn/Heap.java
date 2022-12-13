package com.xzc.learn;

/**
 * @Author: ZCXu1
 * @Date: 2022/12/12 16:22
 * @Version: 1.0.0
 * @Description:
 */
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
            swap(heap,largestIndex,index);
            // 往下走 重复循环
            index = largestIndex;
            left = index * 2 + 1;
        }
    }

    public int getMax() {
        return heap[0];
    }

    public int popMax(){
        int res = heap[0];
        // 最后一个元素放到根节点 总元素数量--
        heap[0] = heap[--count];
        // 下沉该元素
        down(0);
        return res;
    }

    public void printHeap(){
        for (int i = 0; i < count; i++) {
            System.out.print(heap[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Heap h = new Heap(16);
        h.heapInsert(2);
        h.printHeap();
        h.heapInsert(3);
        h.printHeap();
        h.heapInsert(4);
        h.printHeap();
        h.heapInsert(1);
        h.printHeap();
        System.out.println(h.getMax());
        System.out.println(h.popMax());
        h.printHeap();
    }

}
