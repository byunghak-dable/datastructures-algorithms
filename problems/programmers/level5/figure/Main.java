import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) {
    // Integer[] arrows = {6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0, 2};
    // Integer[] arrows = {1, 1, 4, 4, 6, 6, 1, 7, 3};
    Integer[] arrows = {2, 7, 2, 5, 0};
    int result = solution(arrows);
    System.out.println(result);
  }

  public static int solution(Integer[] arrows) {
    Graph graph = new Graph();
    List<Vertex> nodes = graph.makeGraph(arrows);
    FigureSearch search = new FigureSearch(nodes);

    return search.getFigureCount();
  }
}

class Graph {
  List<Vertex> makeGraph(Integer[] arrows) {
    List<Vertex> list = new ArrayList<Vertex>();
    Map<String, Vertex> map = new HashMap<String, Vertex>();
    Vertex lastVertex = new Vertex(list.size(), 0, 0);
    map.put(generateKey(0, 0), lastVertex);
    list.add(lastVertex);

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
        currVertex = new Vertex(list.size(), posX, posY);
        map.put(generateKey(posX, posY), currVertex);
        list.add(currVertex);
      }
      currVertex.addNeighbor(lastVertex);
      lastVertex.addNeighbor(currVertex);
      lastVertex = currVertex;
    }
    return list;
  }

  String generateKey(int posX, int posY) {
    return posX + ":" + posY;
  }
}

// base on depth first search
class FigureSearch {
  private List<Vertex> list;
  private boolean[] visitedList;
  private int count = 0;

  FigureSearch(List<Vertex> list) {
    this.list = list;
  }

  int getFigureCount() {
    count = 0;
    visitedList = new boolean[list.size()];
    dfs(0, null);
    return count / 2;
  }

  void dfs(int index, Vertex lastVertex) {
    Vertex target = list.get(index);
    if (visitedList[index]) {
      count++;
      return;
    }
    visitedList[index] = true;
    List<Vertex> neighbors = target.getNeighbors();
    for (Vertex neighbor : neighbors) {
      if (neighbor == lastVertex) continue;
      dfs(neighbor.getIndex(), target);
    }
  }
}

class Vertex {
  private int index;
  private int posX, posY;
  private List<Vertex> neighbors = new ArrayList<Vertex>(8);

  Vertex(int index, int posX, int posY) {
    this.index = index;
    this.posX = posX;
    this.posY = posY;
  }

  boolean addNeighbor(Vertex neighbor) {
    for (Vertex existNeighbor : neighbors) {
      if (isDuplicate(existNeighbor, neighbor)) return false;
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

  int getIndex() {
    return index;
  }

  List<Vertex> getNeighbors() {
    return neighbors;
  }
}
