import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2019 카카오 블라인드 테스트 : 오픈 채팅방
class Solution {
  public String[] solution(String[] record) {
    Map<String, String> map = new HashMap<String, String>();
    for (String r : record) {
      String[] split = r.split(" ");
      String action = split[0];
      if (action.equals("Leave")) continue;
      String idx = split[1];
      String name = split[2];
      map.put(idx, name);
    }
    List<String> list = new ArrayList<String>();
    for (int i = 0; i < record.length; i++) {
      String r = record[i];
      String[] split = r.split(" ");
      String action = split[0];
      if (action.equals("Change")) continue;
      String idx = split[1];
      if (action.equals("Enter")) action = " 들어왔습니다.";
      if (action.equals("Leave")) action = " 나갔습니다.";
      String msg = map.get(idx) + "님이" + action;
      list.add(msg);
    }
    String[] answer = new String[list.size()];
    for (int i = 0; i < list.size(); i++) answer[i] = list.get(i);
    return answer;
  }
}
