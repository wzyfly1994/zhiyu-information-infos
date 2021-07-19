package com.zhiyu.common;

import java.util.Arrays;

/**
 * @author wengzhiyu
 * @date 2021/3/22
 */
public class QuickSort {
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void quickSort(int[] arr, int start, int end) {
        if (start >= end)
            return;
        int k = arr[start];
        int i = start, j = end;
        while (i != j) {
            while (i < j && arr[j] >= k)
                --j;
            swap(arr, i, j);
            while (i < j && arr[i] <= k)
                ++i;
            swap(arr, i, j);
        }
        quickSort(arr, start, i - 1);
        quickSort(arr, i + 1, end);
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 9, 1, 3, 4, 8, 7, 10};
        new QuickSort().quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
