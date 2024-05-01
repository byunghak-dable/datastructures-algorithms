import java.util.*;

// 타겟 넘버(BFS / DFS)
class Solution {
  public int solution(int[] numbers, int target) {
    int answer = 0;

    return check(numbers, -1, target);
  }

  int check(int[] numbers, int index, int target) {
    int count = 0;
    if (numbers.length != index + 1) {
      index++;
      count += check(numbers, index, target);
      numbers[index] *= -1;
      count += check(numbers, index, target);
      return count;
    }
    int result = 0;
    for (int i = 0; i < numbers.length; i++) {
      result += numbers[i];
    }
    return result == target ? 1 : 0;
  }
}
