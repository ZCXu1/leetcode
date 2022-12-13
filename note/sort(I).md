# 简单排序

## 选择排序

每一轮确定把本轮最小的放在最左边 每一轮在本轮左侧确定一个最小的数

```java

class SelectSort{
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

}

```
## 冒泡排序

通过相邻的交换 每一轮把大的往右放 每一轮在本轮右侧确定一个最大的数

```java
class BubbleSort{
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
    
}


```

## 插入排序

// 第i轮已知0~i-1有序 通过相邻比较和交换 让0~i有序
```java
class InsertSort{
    
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
    
}

```

## swap方法更快速的写法
可以通过异或运算实现交换，但是要注意两个数内容可以一样但是地址不能一样。
即`a[i]可以与a[j]相等，但是i≠j`。
```java
class Swap{
    public void swap(int[] a, int i, int j){
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
    }
}
    
```
