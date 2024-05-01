"""
어구전철 문제

https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem?isFullScreen=true
"""


def sherlockAndAnagrams(s):
    ans = 0
    for i in range(1, len(s)):
        for j in range(len(s) - i + 1):
            jArr = sorted(s[j : i + j])
            for k in range(j + 1, len(s) - i + 1):
                if jArr == sorted(s[k : i + k]):
                    ans += 1
    return ans
