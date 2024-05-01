class Solution {
  public int[] solution(int rows, int columns, int[][] queries) {
    int[] answer = new int[queries.length];
    int[][] table = new int[rows][columns];
    int trav = 1;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        table[i][j] = trav;
        trav++;
      }
    }
    for (int i = 0; i < queries.length; i++) {
      int[] q = queries[i];
      int[] xOrder = checkOrder(q[0], q[2]);
      int[] yOrder = checkOrder(q[1], q[3]);
      int x1 = xOrder[0];
      int x2 = xOrder[1];
      int y1 = yOrder[0];
      int y2 = yOrder[1];
      int temp = table[x1][y1];
      int travX = x1;
      int travY = y1;
      int smallest = temp;
      while (travY != y2) {
        int nextY = travY + 1;
        int target = table[travX][nextY];
        table[travX][nextY] = temp;
        temp = target;
        travY = nextY;
        smallest = checkSmallest(smallest, temp);
      }
      while (travX != x2) {
        int nextX = travX + 1;
        int target = table[nextX][travY];
        table[nextX][travY] = temp;
        temp = target;
        travX = nextX;
        smallest = checkSmallest(smallest, temp);
      }
      while (travY != y1) {
        int nextY = travY - 1;
        int target = table[travX][nextY];
        table[travX][nextY] = temp;
        temp = target;
        travY = nextY;
        smallest = checkSmallest(smallest, temp);
      }
      while (travX != x1) {
        int nextX = travX - 1;
        int target = table[nextX][travY];
        table[nextX][travY] = temp;
        temp = target;
        travX = nextX;
        smallest = checkSmallest(smallest, temp);
      }
      answer[i] = smallest;
    }
    return answer;
  }

  int[] checkOrder(int x1, int x2) {
    x1 = x1 - 1;
    x2 = x2 - 1;
    if (x1 < x2) return new int[] {x1, x2};
    return new int[] {x2, x1};
  }

  int checkSmallest(int target, int subject) {
    return target < subject ? target : subject;
  }
}
