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
    Map<Integer, Player> above = new HashMap<Integer, Player>();
    Map<Integer, Player> below = new HashMap<Integer, Player>();

    Player(int id) {
      this.id = id;
    }

    void win(Player loser, Map<Integer, Boolean> a, int n) {
      for (Player p : above.values()) {
        p.win(loser, a, n);
      }
      if (below.containsKey(loser.id)) return;
      below.put(loser.id, loser);
      for (Player p : loser.below.values()) {
        if (below.containsKey(p.id)) continue;
        below.put(p.id, p);
      }
      System.out.println(below.size() + ":" + above.size());
      if (below.size() + above.size() == n - 1) a.put(id, true);
    }

    void lose(Player winner, Map<Integer, Boolean> a, int n) {
      for (Player p : below.values()) {
        p.lose(winner, a, n);
      }
      if (above.containsKey(winner.id)) return;
      above.put(winner.id, winner);
      for (Player p : winner.above.values()) {
        if (above.containsKey(p.id)) continue;
        above.put(p.id, p);
      }
      if (below.size() + above.size() == n - 1) a.put(id, true);
    }
  }

  public int solution(int n, int[][] results) {
    int answer = 0;
    Map<Integer, Player> map = new HashMap<Integer, Player>();
    Map<Integer, Boolean> a = new HashMap<Integer, Boolean>();
    for (int[] result : results) {
      int winnerId = result[0];
      int loserId = result[1];
      Player winner, loser;
      // add winner
      if (map.containsKey(winnerId)) {
        winner = map.get(winnerId);
      } else {
        winner = new Player(winnerId);
        map.put(winnerId, winner);
      }
      // add loser
      if (map.containsKey(loserId)) {
        loser = map.get(loserId);
      } else {
        loser = new Player(loserId);
        map.put(loserId, loser);
      }
      winner.win(loser, a, n);
      loser.lose(winner, a, n);
    }
    return a.size();
  }
}
