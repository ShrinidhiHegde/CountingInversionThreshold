// Java implementation of the approach 
import java.util.Arrays;
import java.util.Scanner;

public class solution {

  // Function to count the number of inversions
  // during the merge process
  private static int mergeAndCount(int[] arr, int l, int m, int r, int threshold)
  {

    // Left subarray
    int[] left = Arrays.copyOfRange(arr, l, m + 1);

    // Right subarray
    int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

    int i = 0, j = 0, k = l, swaps = 0, var=0;

    while (i < left.length && j < right.length) {
      if (left[i] <= right[j])
        arr[k++] = left[i++];
      else {
        if (left[i] > threshold * right[j]) {
          swaps += (m + 1) - (l + i);
        }
        if((left[i] > right[j]) && left[i] <= threshold * right[j]) {
          var = i;
          while(var < left.length) {

            if(left[var] > threshold * right[j]) {
              swaps += 1;
            }
            var += 1;
          }
        }
        arr[k++] = right[j++];
      }
    }

    // Fill from the rest of the left subarray
    while (i < left.length)
      arr[k++] = left[i++];

    // Fill from the rest of the right subarray
    while (j < right.length)
      arr[k++] = right[j++];

    return swaps%1000000007;
  }

  // Merge sort function
  private static int mergeSortAndCount(int[] arr, int l, int r, int threshold)
  {

    // Keeps track of the inversion count at a
    // particular node of the recursion tree
    int count = 0;

    if (l < r) {
      int m = (l + r) / 2;

      // Total inversion count = left subarray count
      // + right subarray count + merge count

      // Left subarray count
      count += mergeSortAndCount(arr, l, m, threshold);

      // Right subarray count
      count += mergeSortAndCount(arr, m + 1, r, threshold);

      // Merge count
      count += mergeAndCount(arr, l, m, r, threshold);
    }

    return count%1000000007;
  }

  // Driver code
  public static void main(String[] args)
  {
    int n;
    int threshold;
    Scanner input= new Scanner(System.in);
    threshold = input.nextInt();
    n = input.nextInt();
    int[] arr = new int[n];
    for (int i=0; i<n; i++) {
      arr[i] = input.nextInt();
    }
    int count = mergeSortAndCount(arr, 0, arr.length - 1, threshold);
    count = count % 1000000007;
    System.out.println(count);
  }
}
