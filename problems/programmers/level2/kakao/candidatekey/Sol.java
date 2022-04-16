import java.util.*;

class Solution {
  List<List<String>> chart = new ArrayList<>();
  List<Set<Integer>> uniq = new ArrayList<>();

  public int solution(String[][] relation) {
    List<Integer> left = new ArrayList<>(relation[0].length);
    for (int i = 0; i < relation.length; i++) {
      chart.add(new ArrayList<>());
      for (int j = 0; j < relation[0].length; j++) {
        if (i == 0) left.add(j);
        chart.get(i).add(relation[i][j]);
      }
    }
    dfs(new ArrayList<>(), left);
    return getUniqCnt();
  }

  private void dfs(List<Integer> curr, List<Integer> left) {
    if (check(curr)) return;

    List<Integer> nl = new ArrayList<>(left);
    for (int i = 0; i < left.size(); i++) {
      nl.remove(0);
      curr.add(left.get(i));
      dfs(curr, nl);
      curr.remove(curr.size() - 1);
    }
  }

  private boolean check(List<Integer> curr) {
    if (curr.isEmpty()) return false;
    Set<String> set = new HashSet<>();

    for (List<String> list : chart) {
      String key = "";
      for (int v : curr) key += list.get(v);
      set.add(key);
    }

    if (set.size() != chart.size()) return false;
    uniq.add(new HashSet<>(curr));
    return true;
  }

  private int getUniqCnt() {
    int count = 0;
    for (int i = 0; i < uniq.size() - 1; i++) {
      Set<Integer> l = uniq.get(i);
      for (int j = i + 1; j < uniq.size(); j++) {
        Set<Integer> r = uniq.get(j);
        if (l.size() < r.size()) {
          if (!r.containsAll(l)) continue;
          count++;
          continue;
        }
        if (!l.containsAll(r)) continue;
        count++;
        break;
      }
    }
    return uniq.size() - count;
  }
}
