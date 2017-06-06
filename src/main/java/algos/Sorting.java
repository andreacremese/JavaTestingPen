package main.java.algos;

/**
 * Created by acremese on 5/22/17.
 */

// how to make a static class in Java?
public interface Sorting {
    static void BubbleSort(int[] arr){
        if (arr == null) { return; }
        Boolean changed = false;

        do {
            changed = false;
            for (int i = 0; i < arr.length -1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    changed = true;
                }
            }
        } while (changed);
        return;
    }
}
