import java.util.*;

class Solution {
  public static void main(String[] args) {
    Solution sol = new Solution();
    int[] arrows = {6, 0, 3, 0, 5, 2, 6, 0, 3, 0, 5};
    int result = sol.solution(arrows);
    System.out.println(result);
  }

  class Vertex {
    private int x, y;

    Vertex(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public boolean equals(Object o) {
      Vertex target = (Vertex) o;
      return target.getX() == x && target.getY() == y;
    }

    public int hashCode() {
      return Objects.hash(x, y);
    }

    int getX() {
      return x;
    }

    int getY() {
      return y;
    }
  }

  public int solution(int[] arrows) {
    int result = 0;
    Map<Vertex, boolean[]> map = new HashMap<Vertex, boolean[]>();
    Vertex currVertex = new Vertex(0, 0);
    map.put(currVertex, new boolean[8]);
    for (int dir : arrows) {
      Vertex nextVertex;
      int moveX, moveY, oppositeDir;
      boolean[] currVertexDir = map.get(currVertex);
      switch (dir) {
        case 0:
          moveX = 0;
          moveY = 1;
          oppositeDir = 4;
          break;
        case 1:
          moveX = 1;
          moveY = 1;
          oppositeDir = 5;
          // check digonal figure
          if (!currVertexDir[dir]) {
            boolean[] possibleV1 = map.get(new Vertex(currVertex.getX(), currVertex.getY() + 1));
            boolean[] possibleV2 = map.get(new Vertex(currVertex.getX() + 1, currVertex.getY()));
            if ((possibleV1 != null && possibleV1[3]) || possibleV2 != null && possibleV2[7]) {
              result++;
            }
          }
          break;
        case 2:
          moveX = 1;
          moveY = 0;
          oppositeDir = 6;
          break;
        case 3:
          moveX = 1;
          moveY = -1;
          oppositeDir = 7;
          // check digonal figure
          if (!currVertexDir[dir]) {
            boolean[] possibleV1 = map.get(new Vertex(currVertex.getX(), currVertex.getY() - 1));
            boolean[] possibleV2 = map.get(new Vertex(currVertex.getX() + 1, currVertex.getY()));
            if ((possibleV1 != null && possibleV1[1]) || possibleV2 != null && possibleV2[5]) {
              result++;
            }
          }
          break;
        case 4:
          moveX = 0;
          moveY = -1;
          oppositeDir = 0;
          break;
        case 5:
          moveX = -1;
          moveY = -1;
          oppositeDir = 1;
          // check digonal figure
          if (!currVertexDir[dir]) {
            boolean[] possibleV1 = map.get(new Vertex(currVertex.getX(), currVertex.getY() - 1));
            boolean[] possibleV2 = map.get(new Vertex(currVertex.getX() - 1, currVertex.getY()));
            if ((possibleV1 != null && possibleV1[7]) || possibleV2 != null && possibleV2[3]) {
              result++;
            }
          }
          break;
        case 6:
          moveX = -1;
          moveY = 0;
          oppositeDir = 2;
          break;
        case 7:
          moveX = -1;
          moveY = 1;
          oppositeDir = 3;
          // check digonal figure
          if (!currVertexDir[dir]) {
            boolean[] possibleV1 = map.get(new Vertex(currVertex.getX(), currVertex.getY() + 1));
            boolean[] possibleV2 = map.get(new Vertex(currVertex.getX() - 1, currVertex.getY()));
            if ((possibleV1 != null && possibleV1[5]) || possibleV2 != null && possibleV2[1]) {
              result++;
            }
          }
          break;
        default:
          throw new RuntimeException("wrong dir");
      }
      currVertexDir[dir] = true;
      nextVertex = new Vertex(currVertex.getX() + moveX, currVertex.getY() + moveY);
      boolean[] nextVertexDir = map.get(nextVertex);
      if (nextVertexDir != null) {
        // opposite side check
        if (!nextVertexDir[oppositeDir]) {
          nextVertexDir[oppositeDir] = true;
          result++;
        }
      } else {
        nextVertexDir = new boolean[8];
        nextVertexDir[oppositeDir] = true;
        map.put(nextVertex, nextVertexDir);
      }
      currVertex = nextVertex;
    }
    return result;
  }
}
