import java.util.Arrays;
import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int arrayLength = scanner.nextInt();
    int seriesLength = scanner.nextInt();
    int[] array = new int[arrayLength];
    for (int i = 0; i < arrayLength; i++) {
      array[i] = scanner.nextInt();
    }
    scanner.close();

    int[] result = reverse_seriesInArray(array, seriesLength);
    System.out.println(Arrays.toString(result));
  }

  /**
   * Reverses the position of the elements of all subarrays with the given seriesLength and any
   * remaining elements that are less than seriesLength.
   *
   * Example: 
   * input: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], seriesLength = 4
   * output: [4, 3, 2, 1, 8, 7, 6, 5, 10, 9]
   *
   * If the value of seriesLength is greater than array.length, then seriesLength is reduced to array.length.
   *
   * @return An array of intgeres with reversed subarrays, as per the stated conditions.
   */
  public static int[] reverse_seriesInArray(int[] array, int seriesLength) {
    if (seriesLength == 0 || seriesLength == 1) {
      return array;
    }

    int elementsToReverse_perSeries = seriesLength <= array.length ? seriesLength : array.length;
    int endIndex_nextSeries = elementsToReverse_perSeries - 1;

    while (endIndex_nextSeries < array.length) {

      for (int i = 0; i < elementsToReverse_perSeries / 2; i++) {
        int head = array[endIndex_nextSeries + 1 - elementsToReverse_perSeries + i];
        int tail = array[endIndex_nextSeries - i];

        array[endIndex_nextSeries + 1 - elementsToReverse_perSeries + i] = tail;
        array[endIndex_nextSeries - i] = head;
      }
      endIndex_nextSeries = endIndex_nextSeries + elementsToReverse_perSeries;
    }

    // Remaining elements of the array, if any, that are less than seriesLength.
    int elementsToReverse_afterLastSeries = array.length % elementsToReverse_perSeries;

    for (int i = 0; i < elementsToReverse_afterLastSeries / 2; i++) {
      int head = array[array.length - elementsToReverse_afterLastSeries + i];
      int tail = array[array.length - 1 - i];

      array[array.length - elementsToReverse_afterLastSeries + i] = tail;
      array[array.length - 1 - i] = head;
    }
    return array;
  }
}
