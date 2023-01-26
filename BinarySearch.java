import java.util.Comparator;

public class BinarySearch {

  // Common description of the below functions:
  // * Precondition: `a` is sorted according to the given comparator.
  // * Precondition: all arguments are non-null (no need to check).
  // * Required complexity: O(log(n)) comparisons where n is the length of `a`.

  // Check if the array `a` contains the given search key.
  public static <T> boolean contains(T[] a, T key, Comparator<T> comparator) {

    int startA = 0;
    int endA = a.length -1;

    while(startA <= endA){

      int mid = (startA + endA) / 2;
      int c = comparator.compare(a[mid], key);

      if(c < 0){
        startA = mid +1;
      }
      if(c > 0){
        endA = mid -1;
      }
      if(c == 0){
        return true;
      }
    }
    // if num can't be found in array, it will return -1.
    return false;
  }

  // Return the *first position* of `key` in `a`, or -1 if `key` does not occur.
  public static <T> int firstIndexOf(T[] a, T key, Comparator<T> comparator) {
    return firstIndexOf(a, key, 0, a.length-1, comparator);
  }

  public static <T> int firstIndexOf(T[] a, T key, int low, int high, Comparator<T> comparator) {

    if (low > high || low == a.length) {
      return -1;
    }

    int middle = (low + high) / 2;
    int c = comparator.compare(a[middle], key);

    // If key matches with mid, then the result index will be saved in the variable leftMatch (left as leftmost found), and it will keep
    // doing the recursion. If no more matches are found, it will return left else it will return middle since that
    // is the first match in the array
    if (c == 0) {
      int leftMatch = firstIndexOf(a, key, low, middle - 1, comparator);
      return leftMatch != -1 ? leftMatch : middle;
    }
    else if (c > 0) {
      return firstIndexOf(a, key, low, middle - 1, comparator);
    }
    else {
      return firstIndexOf(a, key, middle + 1, high, comparator);
    }
  }

  //If c < 0, then key is greater than a[middle]
  //If c = 0, then x is equal to y.
  //If c > 0, then a[middle] is greater than key.


  // Versions of the above functions that use the natural ordering of the type T.
  // T needs to be "comparable" (i.e., implement the interface Comparable).
  // Examples: Integer, String (the alphabetic ordering)

  public static <T extends Comparable<? super T>> boolean contains(T[] a, T key) {
    return contains(a, key, Comparator.naturalOrder());
  }

  public static <T extends Comparable<? super T>> int firstIndexOf(T[] a, T key) {
    return firstIndexOf(a, key, Comparator.naturalOrder());
  }

  // Your tests.

  public static void main(String[] args) {
    Integer[] a = { 1, 3, 5, 7, 9 };
    assert contains(a, 1);
    assert !contains(a, 4);
    assert contains(a, 7);

    String[] b = { "cat", "cat", "cat", "dog", "turtle", "turtle" };
    assert firstIndexOf(b, "cat") == 0;
    assert firstIndexOf(b, "dog") == 3;
    assert firstIndexOf(b, "turtle") == 4;
    assert firstIndexOf(b, "zebra") == -1;
    assert firstIndexOf(b, "bee") == -1;
  }
}
