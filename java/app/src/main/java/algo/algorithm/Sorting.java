package algo.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Sorting {

  public <T extends Comparable<T>> void bubbleSort(T[] array) {
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array.length - 1 - i; j++) {
        T curr = array[j];
        T next = array[j + 1];
        if (curr.compareTo(next) < 0) continue;
        array[j] = next;
        array[j + 1] = curr;
      }
    }
  }

  public <T extends Comparable<T>> void selectionSort(T[] array) {
    for (int i = 0; i < array.length; i++) {
      int minIndex = i;
      for (int j = i + 1; j < array.length; j++) {
        if (array[minIndex].compareTo(array[j]) > 0) minIndex = j;
      }
      T temp = array[i];
      array[i] = array[minIndex];
      array[minIndex] = temp;
    }
  }

  public <T extends Comparable<T>> void insertionSort(T[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      if (array[i].compareTo(array[i + 1]) < 0) continue;
      for (int j = i; j >= 0; j--) {
        if (array[j].compareTo(array[j + 1]) < 0) break;
        T temp = array[j];
        array[j] = array[j + 1];
        array[j + 1] = temp;
      }
    }
  }

  public <T extends Comparable<T>> void mergeSort(T[] array) {
    if (array.length == 1) return;
    int middleIndex = array.length / 2;
    T[] left = Arrays.copyOfRange(array, 0, middleIndex);
    T[] right = Arrays.copyOfRange(array, middleIndex, array.length);
    mergeSort(left);
    mergeSort(right);
    merge(array, left, right);
  }

  private <T extends Comparable<T>> T[] merge(T[] org, T[] left, T[] right) {
    int trav = 0;
    int leftTrav = 0;
    int rightTrav = 0;
    while (leftTrav != left.length && rightTrav != right.length) {
      T leftVal = left[leftTrav];
      T rightVal = right[rightTrav];
      if (leftVal.compareTo(rightVal) < 0) {
        org[trav] = leftVal;
        leftTrav++;
      } else {
        org[trav] = rightVal;
        rightTrav++;
      }
      trav++;
    }
    T[] x;
    int xTrav;
    int xLimit;
    if (leftTrav == left.length) {
      x = right;
      xTrav = rightTrav;
      xLimit = right.length;
    } else {
      x = left;
      xTrav = leftTrav;
      xLimit = left.length;
    }
    while (xTrav != xLimit) {
      org[trav] = x[xTrav];
      xTrav++;
      trav++;
    }
    return org;
  }

  public <T extends Comparable<T>> void quickSort(T[] array) {
    if (array.length < 1) return;
    int pIndex = (int) Math.random() * array.length;
    T pVal = array[pIndex];
    int lastIndex = array.length - 1;
    array[pIndex] = array[lastIndex];
    array[lastIndex] = pVal;

    int leftIndex = 0;
    int rightIndex = lastIndex - 1;
    while (true) {
      while (leftIndex <= lastIndex && pVal.compareTo(array[leftIndex]) > 0) leftIndex++;
      while (rightIndex >= 0 && pVal.compareTo(array[rightIndex]) < 0) rightIndex--;
      T leftVal = array[leftIndex];
      if (leftIndex >= rightIndex) {
        array[leftIndex] = pVal;
        array[lastIndex] = leftVal;
        break;
      }
      array[leftIndex] = array[rightIndex];
      array[rightIndex] = leftVal;
    }
    T[] left = Arrays.copyOfRange(array, 0, leftIndex);
    T[] right = Arrays.copyOfRange(array, leftIndex + 1, array.length);
    quickSort(left);
    quickSort(right);
    for (int i = 0; i < left.length; i++) array[i] = left[i];
    for (int i = 0; i < right.length; i++) array[leftIndex + i + 1] = right[i];
  }

  public int[] countingSort(int[] array) {
    int max = array[0];
    int min = array[0];
    for (int v : array) {
      if (v > max) max = v;
      if (v < min) min = v;
    }
    int[] indexList = new int[max - min + 1];
    for (int v : array) {
      indexList[v - min] += 1;
    }
    for (int i = 1; i < indexList.length; i++) {
      indexList[i] += indexList[i - 1];
    }
    int[] result = new int[array.length];
    for (int i = 0; i < array.length; i++) {
      int v = array[i];
      result[indexList[v - min] - 1] = v;
      indexList[v - min] -= 1;
    }
    return result;
  }

  // least significant digit(lsd) 방식
  public int[] radixSort(int[] array) {
    int[] temp = new int[array.length];
    int digit = 1;
    while (true) {
      boolean isRepeat = false;
      Map<Integer, Queue<Integer>> map = new HashMap<Integer, Queue<Integer>>();
      for (int i = 0; i < array.length; i++) {
        int orgNum = array[i];
        int digitNum = getDigitNum(orgNum, digit);
        temp[i] = digitNum;
        if (!isRepeat && digitNum != 0) isRepeat = true;
        if (!map.containsKey(digitNum)) map.put(digitNum, new LinkedList<Integer>());
        map.get(digitNum).add(orgNum);
      }
      if (!isRepeat) break;
      int[] orderedList = countingSort(temp);
      for (int i = 0; i < orderedList.length; i++) {
        array[i] = map.get(orderedList[i]).poll();
      }
      digit++;
    }
    return array;
  }

  public int getDigitNum(int target, int digit) {
    if (digit < 0) throw new RuntimeException("wrong digit");
    int x = target / (int) Math.pow(10, digit - 1);
    int y = (int) Math.floor(x / 10) * 10;
    return x - y;
  }

  public void sortPrimaryNum(List<Integer> list) {
    int[] targets = {2, 3, 5, 7};
    for (int t : targets) {
      for (int i = 0; i < list.size(); i++) {
        int v = list.get(i);
        if (v < 2) list.remove(i);
        if (v == t) continue;
        if (v % t == 0) list.remove(i);
      }
    }
  }
}
