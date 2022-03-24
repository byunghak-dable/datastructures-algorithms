import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) {
    Integer[] arrows = {6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0};
    solution(arrows);
  }

  public static int solution(Integer[] arrows) {
    Graph graph = new Graph();
    graph.makeGraph(arrows);
    System.out.println(graph.getStart().getNeighbors());
    return 0;
  }
}

class Graph {
  Map<String, Vertex> map = new HashMap<String, Vertex>();

  void makeGraph(Integer[] arrows) {
    if (!map.isEmpty()) throw new RuntimeException("need to clear graph");
    Vertex lastVertex = new Vertex(0, 0);
    map.put(generateKey(0, 0), lastVertex);

    for (int i = 0; i < arrows.length; i++) {
      int lastPosX = lastVertex.getPosX();
      int lastPosY = lastVertex.getPosY();
      int posX, posY;
      switch (arrows[i]) {
        case 0:
          posX = lastPosX;
          posY = lastPosY + 1;
          break;
        case 1:
          posX = lastPosX + 1;
          posY = lastPosY + 1;
          break;
        case 2:
          posX = lastPosX + 1;
          posY = lastPosY;
          break;
        case 3:
          posX = lastPosX + 1;
          posY = lastPosY - 1;
          break;
        case 4:
          posX = lastPosX;
          posY = lastPosY - 1;
          break;
        case 5:
          posX = lastPosX - 1;
          posY = lastPosY - 1;
          break;
        case 6:
          posX = lastPosX - 1;
          posY = lastPosY;
          break;
        case 7:
          posX = lastPosX - 1;
          posY = lastPosY + 1;
          break;
        default:
          throw new IllegalArgumentException();
      }
      String mapKey = generateKey(posX, posY);
      Vertex currVertex;
      if (map.containsKey(mapKey)) {
        currVertex = map.get(mapKey);
      } else {
        currVertex = new Vertex(posX, posY);
        map.put(generateKey(posX, posY), currVertex);
      }
      currVertex.addNeighbor(lastVertex);
      lastVertex.addNeighbor(currVertex);
      lastVertex = currVertex;
    }
  }

  int bfs(Vertex start) {
    return 0;
  }

  String generateKey(int posX, int posY) {
    return posX + ":" + posY;
  }

  Vertex getStart() {
    return map.get("0:0");
  }
}

class Vertex {
  private List<Vertex> neighbors = new ArrayList<Vertex>(8);
  private int posX, posY;

  Vertex(int posX, int posY) {
    this.posX = posX;
    this.posY = posY;
  }

  boolean addNeighbor(Vertex neighbor) {
    for (Vertex existNeighbor : neighbors) {
      if (isDuplicate(existNeighbor, neighbor)) {
        return false;
      }
    }
    if (neighbors.size() > 8) throw new RuntimeException("too many neighbors");
    neighbors.add(neighbor);
    return true;
  }

  boolean isDuplicate(Vertex target, Vertex subject) {
    return target.getPosX() == subject.getPosX() && target.getPosY() == subject.getPosY();
  }

  int getPosX() {
    return posX;
  }

  int getPosY() {
    return posY;
  }

  List<Vertex> getNeighbors() {
    return neighbors;
  }
}
