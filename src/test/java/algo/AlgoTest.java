package test.java.algo;

import main.java.algos.Sorting;
import org.junit.Test;
import org.junit.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;


public class AlgoTest {

        @Test
        public void canBubbleSortWhenInInverseOrderAndWithDuplicate() {
            // arrange
            int[] arr = { 3, 2, 1, 1 };
            // act
            Sorting.BubbleSort(arr);
            // assert
            TestSortedArray(arr);
            TestContains(arr, new int[] { 3, 2, 1 });
            // assert - with streams!
            TestContainsWithStream(arr, new int[] { 3, 2, 1 });
            TestSortedArrayWithStream(arr);

        }

        private void TestSortedArray(int[] arr) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    Assert.assertFalse("array not sorted, out of order elements were found in the array", true);
                }
            }
        }

        private void TestSortedArrayWithStream(int[] arr) {
            Arrays.stream(arr).reduce(Integer.MIN_VALUE, (min, elem) -> {
                Assert.assertTrue("array not sorted, out of order elements were found in the array", elem > min);
                return Integer.min(min, elem);
            });
        }



        private void TestContains(int[] arr, int[] elems) {
            HashSet<Integer> set = new HashSet<Integer>();
            for(int e : elems) {
                set.add(e);
            }
            for (int i = 0; i < elems.length; i++) {
                Assert.assertTrue("Extraneous element in sorted array",set.contains(arr[i]));
            }
        }

        private void TestContainsWithStream(int[] arr, int[] elems) {
            HashSet<Integer> set = new HashSet<Integer>();
            for(int e : elems) {set.add(e);}
            Arrays.stream(arr).forEach(e -> Assert.assertTrue("Extraneous element in sorted array",set.contains(e)));
        }
}