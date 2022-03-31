package algo;

import algo.algorithm.Sorting;
import java.util.*;

public class App {
  public static void main(String[] args) {
    Sorting s = new Sorting();
    int[] array = {234, -19, 323234234, 4, 37, -6};
    array = s.radixSort(array);
    for (int v : array) System.out.println(v);
  }
}
