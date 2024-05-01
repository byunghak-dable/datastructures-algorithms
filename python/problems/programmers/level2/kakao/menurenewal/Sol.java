import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 카카오 메뉴 리뉴얼(DFS + SORTING)
class Solution {
  public String[] solution(String[] orders, int[] course) {
    List<String> answer = new ArrayList<>();
    Set<Integer> set = new HashSet<>();
    Map<Integer, Map<String, Integer>> map = new HashMap<>();

    for (int v : course) set.add(v);
    for (String s : orders) {
      char[] chars = s.toCharArray();
      List<Character> left = new ArrayList<>();
      for (char v : chars) left.add(v);
      Collections.sort(left);
      search(new ArrayList<Character>(), left, set, map);
    }

    for (int k : map.keySet()) {
      Map<String, Integer> sub = map.get(k);
      int max = 0;
      List<String> keys = new ArrayList<>();
      for (String s : sub.keySet()) {
        int val = sub.get(s);
        if (val < 2) continue;
        if (max < val) {
          keys.clear();
          keys.add(s);
          max = val;
          continue;
        }
        if (max == val) keys.add(s);
      }
      for (String key : keys) answer.add(key);
    }
    return order(answer);
  }

  private void search(
      List<Character> curr,
      List<Character> left,
      Set<Integer> course,
      Map<Integer, Map<String, Integer>> map) {
    int size = curr.size();
    if (course.contains(size)) {
      String key = "";
      for (char c : curr) key += c;
      if (!map.containsKey(size)) map.put(size, new HashMap<String, Integer>());
      Map<String, Integer> innerMap = map.get(size);

      if (!innerMap.containsKey(key)) innerMap.put(key, 0);
      innerMap.put(key, innerMap.get(key) + 1);
    }
    if (left.isEmpty()) return;
    for (int i = 0; i < left.size(); i++) {
      char c = left.get(i);
      List<Character> newLeft = new ArrayList<>();
      for (int j = i + 1; j < left.size(); j++) {
        newLeft.add(left.get(j));
      }
      curr.add(c);
      search(curr, newLeft, course, map);
      curr.remove(curr.size() - 1);
    }
  }

  private String[] order(List<String> list) {
    for (int i = 0; i < list.size(); i++) {
      for (int j = 0; j < list.size() - 1 - i; j++) {
        String curr = list.get(j);
        String next = list.get(j + 1);
        if (compare(curr, next) < 0) continue;
        list.set(j, next);
        list.set(j + 1, curr);
      }
    }
    return list.toArray(new String[list.size()]);
  }

  private int compare(String left, String right) {
    boolean isLeftLonger = left.length() > right.length();
    int length = isLeftLonger ? right.length() : left.length();
    char[] lChars = left.toCharArray();
    char[] rChars = right.toCharArray();
    for (int i = 0; i < length; i++) {
      char l = lChars[i];
      char r = rChars[i];
      int cmp = Character.compare(l, r);
      if (cmp == 0) continue;

      return cmp;
    }
    return isLeftLonger ? 1 : -1;
  }
}
