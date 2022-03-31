import java.util.*;

class Solution {
  public static void main(String[] args) {
    Solution s = new Solution();
    int[] arrows = {6, 0, 3, 0, 5, 2, 6, 0, 3, 0, 5};
    int result = s.solution(arrows);
    System.out.println(result);
  }

  class Pair {
    public int x;
    public int y;

    public Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int hashCode() {
      return Objects.hash(x, y);
    }

    public boolean equals(Object o) {
      return this.x == ((Pair) o).x && this.y == ((Pair) o).y;
    }
  }

  public int[] move(int i) {
    int[] r = new int[2];
    switch (i) {
      case 0:
        r[0] = 0;
        r[1] = 1;
        break;
      case 1:
        r[0] = 1;
        r[1] = 1;
        break;
      case 2:
        r[0] = 1;
        r[1] = 0;
        break;
      case 3:
        r[0] = 1;
        r[1] = -1;
        break;
      case 4:
        r[0] = 0;
        r[1] = -1;
        break;
      case 5:
        r[0] = -1;
        r[1] = -1;
        break;
      case 6:
        r[0] = -1;
        r[1] = 0;
        break;
      case 7:
        r[0] = -1;
        r[1] = 1;
        break;
      default:
        r[0] = 0;
        r[1] = 0;
        break;
    }
    return r;
  }

  public int opposite(int i) {
    switch (i) {
      case 0:
        return 4;
      case 1:
        return 5;
      case 2:
        return 6;
      case 3:
        return 7;
      case 4:
        return 0;
      case 5:
        return 1;
      case 6:
        return 2;
      case 7:
        return 3;
      default:
        return 0;
    }
  }

  public int solution(int[] arrows) {
    int answer = 0;
    int len = arrows.length;
    Map<Pair, boolean[]> map = new HashMap<>();
    boolean[] vis = new boolean[8];
    int[] cur = {0, 0};

    map.put(new Pair(0, 0), vis);

    for (int i : arrows) {
      Pair now = new Pair(cur[0], cur[1]);
      boolean[] nowVisit = map.get(now);
      Pair temp;
      if (i == 1) {
        temp = new Pair(cur[0], cur[1] + 1);
        boolean[] check = map.get(temp);
        if (check != null && check[3] && !nowVisit[1]) {
          answer++;
        }
      }
      if (i == 3) {
        temp = new Pair(cur[0] + 1, cur[1]);
        boolean[] check = map.get(temp);
        if (check != null && check[5] && !nowVisit[3]) {
          answer++;
        }
      }
      if (i == 5) {
        temp = new Pair(cur[0] - 1, cur[1]);
        boolean[] check = map.get(temp);
        if (check != null && check[3] && !nowVisit[5]) {
          answer++;
        }
      }

      if (i == 7) {
        temp = new Pair(cur[0], cur[1] + 1);
        boolean[] check = map.get(temp);
        if (check != null && check[5] && !nowVisit[7]) {
          answer++;
        }
      }
      nowVisit[i] = true;

      int[] dis = move(i);
      cur[0] = cur[0] + dis[0];
      cur[1] = cur[1] + dis[1];
      Pair next = new Pair(cur[0], cur[1]);
      boolean[] visited = map.get(next);
      if (visited == null) {
        boolean[] vis2 = new boolean[8];
        vis2[opposite(i)] = true;
        map.put(next, vis2);
      } else {
        if (!visited[opposite(i)]) {
          visited[opposite(i)] = true;
          answer++;
        }
      }
    }
    return answer;
  }
}
