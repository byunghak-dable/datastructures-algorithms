class Solution {
  public int[] solution(String[][] places) {
    int[] answer = new int[places.length];
    for (int k = 0; k < places.length; k++) {
      String[] room = places[k];
      char[][] conf = new char[5][5];
      boolean isFailed = false;
      for (int i = 0; i < room.length; i++) {
        char[] chars = room[i].toCharArray();
        for (int j = 0; j < chars.length; j++) {
          conf[i][j] = chars[j];
          if (conf[i][j] != 'P') continue;
          isFailed = check(conf, i, j) || check1(conf, i, j, chars) || check2(conf, i, j);
          if (isFailed) break;
        }
        if (isFailed) break;
      }
      if (!isFailed) answer[k] = 1;
    }
    return answer;
  }
  // i = curr
  private boolean check(char[][] conf, int i, int j) {
    if (j - 1 < 0) return false;
    if (conf[i][j - 1] == 'P') return true;
    if (j - 2 < 0) return false;
    if (conf[i][j - 2] == 'P' && conf[i][j - 1] != 'X') return true;
    return false;
  }

  // i = curr - 1
  private boolean check1(char[][] conf, int i, int j, char[] chars) {
    if (i - 1 < 0) return false;
    if (conf[i - 1][j] == 'P') return true;
    if (j > 0) {
      if (conf[i - 1][j - 1] == 'P' && (conf[i - 1][j] != 'X' || chars[j - 1] != 'X')) return true;
    }
    if (j < 4) {
      if (conf[i - 1][j + 1] == 'P' && (conf[i - 1][j] != 'X' || chars[j + 1] != 'X')) return true;
    }
    return false;
  }

  // i = curr -2
  private boolean check2(char[][] conf, int i, int j) {
    if (i - 2 < 0) return false;
    if (conf[i - 2][j] == 'P' && conf[i - 1][j] != 'X') return true;
    return false;
  }
}
