package com.example.springcloud;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {0,5,1,2,7,4,8,3,9};
        //int[] arr = {3,2,1};
        QuickSort quickSort = new QuickSort();
        int[] result = quickSort.getLeastNumbers(arr, 2);
        System.out.println("quicksort");

    }

    public int[] getLeastNumbers(int[] arr, int k) {
        //快速排序
        //quick(arr, 0, arr.length-1);

        //堆排序
        heapSort(arr);

        int[] result = new int[k];
        for(int i = 0; i < k; i++){
            result[i] = arr[i];
        }
        return result;
    }

    public void heapSort(int[] arr){
        //构造堆
        int N = arr.length;
        for(int k = N/2; k >= 1; k--){
            sink(arr, k, N);
        }
        //下沉排序
        while( N > 1){
            exch(arr, 1, N--);
            sink(arr, 1, N);
        }
    }

    public void sink(int[] arr, int k, int N){
        //对arr[k]下沉
        while(2*k <= N){
            int j = 2*k;
            if(j < N && less(arr[j], arr[j+1])){
                j++;
            }
            if(!less(arr[k], arr[j])){
                break;
            }
            exch(arr, k, j);
            k = j;
        }
    }

    public void quick(int[] arr, int lo, int hi){
        if(hi <= lo){
            return;
        }
        int j = partition(arr, lo, hi);
        quick(arr, lo, j-1);
        quick(arr, j+1, hi);
    }

    private int partition(int[] arr, int lo, int hi){
        int i = lo, j = hi+1;
        Random random = new Random();
        int k = random.nextInt(hi-lo+1) + lo;
        exch(arr, lo, k);
        int v = arr[lo];
        while(true){
            while(less(arr[++i], v)){
                if(i == hi){
                    break;
                }
            }
            while(less(v, arr[--j])){
                if(j == lo){
                    break;
                }
            }
            if(i >= j){
                break;
            }
            exch(arr, i, j);
        }
        exch(arr, lo, j);
        return j;
    }

    private void exch(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private boolean less(Integer a, Integer b){
        return a.compareTo(b) < 0;
    }

}
