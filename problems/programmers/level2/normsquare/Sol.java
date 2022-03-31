// 멀쩡한 사각형
class Solution {
  public long solution(int a, int b) {
    long w = (long) a;
    long h = (long) b;
    if (w == h) return w * h - h;
    long x = w;
    long y = h;
    for (int i = 1; i < w; i++) {
      double result = checkX(w, h, i);
      if (result == Math.floor(result)) {
        x = i;
        y = h - Double.valueOf(result).longValue();
        break;
      }
    }
    long smallResult = x * y - (x + y - 1);
    return w * h - (w / x) * (x * y - smallResult);
  }

  double checkX(long w, long h, double x) {
    return -h * x / w + h;
  }
}
