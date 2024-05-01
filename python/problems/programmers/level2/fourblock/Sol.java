import java.util.*;

class Solution {
  String[] board;
  Map<Character, Set<Loc>> map = new HashMap<>();

  public int solution(int m, int n, String[] b) {
    int answer = 0;
    board = b;
    while (true) {
      divide();
      int result = delete();
      if (result == 0) break;
      answer += result;
      refresh();
    }
    return answer;
  }

  private int delete() {
    int result = 0;
    for (Set<Loc> set : map.values()) {
      Set<Loc> remove = new HashSet<>();
      for (Loc loc : set) find(loc, remove);
      for (Loc rm : remove) {
        setVal(rm, ' ');
        set.remove(rm);
      }
      result += remove.size();
    }
    return result;
  }

  private void refresh() {
    List<List<Character>> list = new ArrayList<>();
    for (int i = board.length - 1; i >= 0; i--) {
      for (int j = 0; j < board[i].length(); j++) {
        char val = board[i].charAt(j);
        if (list.size() == j) list.add(new ArrayList<>());
        if (val != ' ') list.get(j).add(board[i].charAt(j));
      }
    }
    String[] nBoard = new String[board.length];
    for (int i = 0; i < board.length; i++) {
      nBoard[i] = "";
      for (int j = 0; j < board[i].length(); j++) {
        List<Character> l = list.get(j);
        nBoard[i] += l.size() > board.length - i - 1 ? l.get(board.length - i - 1) : ' ';
      }
    }
    board = nBoard;
    map.clear();
  }

  private void find(Loc loc, Set<Loc> set) {
    int top = loc.i - 1;
    int left = loc.j - 1;
    Loc[] lt = {new Loc(top, loc.j), new Loc(loc.i, left), new Loc(top, left)};

    if (!checkSq(lt, loc)) return;
    set.add(loc);
    for (Loc nb : lt) set.add(nb);
  }

  private boolean checkSq(Loc[] nbs, Loc loc) {
    char val = getVal(loc);
    for (Loc nb : nbs) {
      if (!map.get(val).contains(nb)) return false;
      char nbVal = checkValid(nb);
      if (nbVal != val) return false;
    }
    return true;
  }

  private void divide() {
    Queue<Loc> q = new LinkedList<>();
    for (int i = 0; i < board[0].length(); i++) {
      q.offer(new Loc(board.length - 1, i));
    }

    while (!q.isEmpty()) {
      Loc curr = q.poll();
      for (Loc nb : getNb(curr)) q.offer(nb);
    }
  }

  private List<Loc> getNb(Loc curr) {
    List<Loc> list = new ArrayList<>(4);
    Loc[] nbs = {new Loc(1, 0), new Loc(0, 1), new Loc(-1, 0), new Loc(0, -1)};
    for (Loc nb : nbs) {
      nb.i += curr.i;
      nb.j += curr.j;
      char nbVal = checkValid(nb);
      if (nbVal == ' ') continue;
      if (!map.containsKey(nbVal)) map.put(nbVal, new HashSet<>());
      if (map.get(nbVal).add(nb)) list.add(nb);
    }
    return list;
  }

  private char checkValid(Loc nb) {
    if (nb.i < 0 || nb.i >= board.length) return ' ';
    if (nb.j < 0 || nb.j >= board[0].length()) return ' ';

    return getVal(nb);
  }

  private char getVal(Loc loc) {
    return board[loc.i].charAt(loc.j);
  }

  private void setVal(Loc loc, char val) {
    StringBuilder sb = new StringBuilder(board[loc.i]);
    sb.setCharAt(loc.j, val);
    board[loc.i] = sb.toString();
  }

  class Loc {
    Integer i, j;

    Loc(int i, int j) {
      this.i = i;
      this.j = j;
    }

    public int hashCode() {
      return Objects.hash(i, j);
    }

    public boolean equals(Object o) {
      Loc target = (Loc) o;
      return target.i == i && target.j == j;
    }
  }
}
