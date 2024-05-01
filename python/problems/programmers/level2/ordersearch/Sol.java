import java.util.*;

class Solution {
  public int[] solution(String[] info, String[] query) {
    Map<String, Set<Ptc>> lang = new HashMap<>();
    Map<String, Set<Ptc>> type = new HashMap<>();
    Map<String, Set<Ptc>> carrer = new HashMap<>();
    Map<String, Set<Ptc>> food = new HashMap<>();
    Map<Integer, Set<Ptc>> score = new HashMap<>();
    for (String i : info) {
      newPtc(i, lang, type, carrer, food, score);
    }
    int[] answer = new int[query.length];
    for (int i = 0; i < query.length; i++) {
      answer[i] = query(query[i], lang, type, carrer, food, score);
    }
    return answer;
  }

  private int query(
      String q,
      Map<String, Set<Ptc>> lang,
      Map<String, Set<Ptc>> type,
      Map<String, Set<Ptc>> carrer,
      Map<String, Set<Ptc>> food,
      Map<Integer, Set<Ptc>> score) {
    String[] split = q.replaceAll(" and", "").split(" ");
    String langCond = split[0];
    String typeCond = split[1];
    String carrerCond = split[2];
    String foodCond = split[3];
    String scoreCond = split[4];

    Queue<Set<Ptc>> pq =
        new PriorityQueue<>(
            new Comparator<Set<Ptc>>() {
              @Override
              public int compare(Set<Ptc> l, Set<Ptc> r) {
                return l.size() < r.size() ? -1 : 1;
              }
            });
    addCond(pq, lang, langCond);
    addCond(pq, type, typeCond);
    addCond(pq, carrer, carrerCond);
    addCond(pq, food, foodCond);
    addScoreCond(pq, score, scoreCond);
    if (pq.isEmpty()) return 0;
    Set<Ptc> set = pq.poll();

    int count = set.size();
    for (Ptc p : set) {
      boolean is = true;
      for (Set<Ptc> s : pq) {
        if (!s.contains(p)) {
          count--;
          break;
        }
      }
    }
    return count;
  }

  private void addCond(Queue<Set<Ptc>> pq, Map<String, Set<Ptc>> map, String key) {
    if (!map.containsKey(key)) return;
    pq.add(map.get(key));
  }

  private void addScoreCond(Queue<Set<Ptc>> pq, Map<Integer, Set<Ptc>> score, String scoreStr) {
    if (scoreStr.equals("-")) return;
    Set<Ptc> list = new HashSet<>();
    int s = Integer.parseInt(scoreStr);

    for (int v : score.keySet()) {
      if (v >= s) list.addAll(score.get(v));
    }

    pq.add(list);
  }

  private void newPtc(
      String q,
      Map<String, Set<Ptc>> lang,
      Map<String, Set<Ptc>> type,
      Map<String, Set<Ptc>> carrer,
      Map<String, Set<Ptc>> food,
      Map<Integer, Set<Ptc>> score) {
    String[] split = q.split(" ");
    Ptc p = new Ptc(split[0], split[1], split[2], split[3], split[4]);
    add(lang, p.lang, p);
    add(type, p.type, p);
    add(carrer, p.carrer, p);
    add(food, p.food, p);
    add(score, p.score, p);
  }

  private <T> void add(Map<T, Set<Ptc>> map, T key, Ptc p) {
    if (!map.containsKey(key)) map.put(key, new HashSet<>());
    map.get(key).add(p);
  }

  class Ptc {
    String lang, type, carrer, food;
    int score;

    Ptc(String lang, String type, String carrer, String food, String score) {
      this.lang = lang;
      this.type = type;
      this.carrer = carrer;
      this.food = food;
      this.score = Integer.parseInt(score);
    }
  }
}
