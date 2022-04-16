package algo.algorithm;

import java.util.List;

public class Search {
  public <T extends Comparable<T>> int binSearch(List<T> list, T val, boolean isLeft) {
    int cmp = isLeft ? 1 : 0;
    int left = 0;
    int right = list.size() - 1;

    while (left < right) {
      int mid = (left + right) / 2;
      if (val.compareTo(list.get(mid)) < cmp) {
        right = mid - 1;
        continue;
      }
      left = mid + 1;
    }
    return left;
  }
}
