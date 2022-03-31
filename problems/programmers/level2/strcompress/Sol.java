
// 2020 카카오 블라인드 테스트 : 문자열 압축
class Solution {
  public int solution(String s) {
    int length = s.length();
    int answer = length;
    int slice = 1;
    while (slice <= length / 2) {
      int count = 0;
      String before = s.substring(0, slice);
      String curr = "";
      String result = "";
      int start = 0;
      for (int i = 0; i <= length / slice; i++) {
        int begin = i * slice;
        int end = begin + slice;
        end = end > length ? length : end;
        curr = s.substring(begin, end);
        if (before.equals(curr)) {
          count++;
          continue;
        }
        result += count == 1 ? before : count + before;
        count = 1;
        before = curr;
      }
      result += curr;
      if (answer > result.length()) answer = result.length();
      slice++;
    }
    return answer;
  }
}
