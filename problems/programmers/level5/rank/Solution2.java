import java.util.*;

class Solution {
  public static void main(String[] args) {
    Solution sol = new Solution();
    int[][] match = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
    // int[][] match = {{1, 2}, {2, 3}, {3, 4}};
    int result = sol.solution(5, match);
    System.out.println(result);
  }

  class Player {
    int id;
    Map<Integer, Player> above = new HashMap();
    Map<Integer, Player> below = new HashMap();

    public Player(int id) {
      this.id = id;
    }

    public void win(Player loser) {
      below.put(loser.id, loser);
    }

    public void lose(Player winner) {
      above.put(winner.id, winner);
    }

    public void getParents(Map<Integer, Boolean> map) {
      for (Player p : above.values()) {
        map.put(p.id, true);
        p.getParents(map);
      }
    }

    public void getChildren(Map<Integer, Boolean> map) {
      for (Player p : below.values()) {
        map.put(p.id, true);
        p.getChildren(map);
      }
    }
  }

  public int solution(int n, int[][] results) {
    int answer = 0;
    Map<Integer, Player> map = new HashMap<Integer, Player>();
    for (int[] result : results) {
      int winnerId = result[0];
      int loserId = result[1];
      Player winner, loser;
      if (map.containsKey(winnerId)) {
        winner = map.get(winnerId);
      } else {
        winner = new Player(winnerId);
        map.put(winnerId, winner);
      }
      if (map.containsKey(loserId)) {
        loser = map.get(loserId);
      } else {
        loser = new Player(loserId);
        map.put(loserId, loser);
      }
      winner.win(loser);
      loser.lose(winner);
    }

    for (Player p : map.values()) {
      Map<Integer, Boolean> m = new HashMap<Integer, Boolean>();
      p.getParents(m);
      p.getChildren(m);
      if (m.size() == n - 1) answer++;
    }

    return answer;
  }
}
