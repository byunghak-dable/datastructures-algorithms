import sys

input = sys.stdin.readline
n, m = map(int, input().split())
area = [list(map(int, input().split())) for _ in range(n)]
dp = [[0] * (m + 1) for _ in range(n + 1)]

for i in range(1, n + 1):
    for j in range(1, m + 1):
        dp[i][j] = area[i - 1][j - 1] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1]

for _ in range(int(input())):
    i1, j1, i2, j2 = map(int, input().split())
    print(dp[i2][j2] - dp[i1 - 1][j2] - dp[i2][j1 - 1] + dp[i1 - 1][j1 - 1])
