import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// 카카오 프렌즈 컬러링 북
class Solution {
  public int[] solution(int m, int n, int[][] picture) {
    int numberOfArea = 0;
    int maxSizeOfOneArea = 0;
    Map<Integer, Set<String>> map = new HashMap<Integer, Set<String>>();

    for (int i = 0; i < picture.length; i++) {
      for (int j = 0; j < picture[i].length; j++) {
        int color = picture[i][j];
        if (color == 0) continue;
        if (!map.containsKey(color)) map.put(color, new HashSet<String>());
        map.get(color).add(encode(i, j));
      }
    }
    for (Set<String> set : map.values()) {
      while (!set.isEmpty()) {
        Queue<String> queue = new LinkedList<String>();
        String first = set.iterator().next();
        queue.offer(first);
        set.remove(first);
        int areaCount = 0;
        while (!queue.isEmpty()) {
          String key = queue.poll();
          areaCount++;
          for (String s : getNeighbors(set, key)) {
            queue.offer(s);
          }
        }
        System.out.println("");
        numberOfArea++;
        if (areaCount > maxSizeOfOneArea) maxSizeOfOneArea = areaCount;
      }
    }
    int[] answer = new int[2];
    answer[0] = numberOfArea;
    answer[1] = maxSizeOfOneArea;
    return answer;
  }

  private String encode(int i, int j) {
    return i + ":" + j;
  }

  private int[] decode(String s) {
    String[] split = s.split(":");
    int[] result = {Integer.parseInt(split[0]), Integer.parseInt(split[1])};
    return result;
  }

  private List<String> getNeighbors(Set<String> set, String key) {
    int[] split = decode(key);
    int x = split[0];
    int y = split[1];
    String[] array = {encode(x + 1, y), encode(x - 1, y), encode(x, y + 1), encode(x, y - 1)};
    List<String> list = new ArrayList<String>();
    for (String s : array) {
      if (!set.contains(s)) continue;
      set.remove(s);
      list.add(s);
    }
    return list;
  }
}
