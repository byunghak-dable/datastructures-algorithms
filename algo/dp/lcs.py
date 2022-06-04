"""
Longest Common subsquence

문자열 s1, s2가 있을 때 s1, s2의 부분집합 중 순서에 맞는 부분집합의 최대 길이
"""


def lcs(s1: str, s2: str):
    dp = [0] * (len(s2) + 1)
    nDp = dp[:]
    for i in range(len(s1) - 1, -1, -1):
        for j in range(len(s2) - 1, -1, -1):
            if s1[i] == s2[j]:
                nDp[j] = 1 + dp[j + 1]
            else:
                nDp[j] = max(nDp[j + 1], dp[j])
        dp, nDp = nDp, dp
    return dp[0]


print(lcs("abcde", "ace"))
