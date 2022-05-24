"""
괄호의 값(https://www.acmicpc.net/problem/2504)

* 알고리즘 분류
구현, 자료 구조, 스택, 재귀
"""
import sys


def sol(s):
    stack = []
    ans = 0
    tmp = 1
    for i, v in enumerate(s):
        if v == "(" or v == "[":
            stack.append(v)
            tmp *= 2 if v == "(" else 3
            continue
        if not stack:
            return 0
        if (v == ")" and stack[-1] == "(") or (v == "]" and stack[-1] == "["):
            if (v == ")" and stack[-1] == "]") or (v == "]" and stack[-1] == ")"):
                return 0
            if (v == ")" and s[i - 1] == "(") or (v == "]" and s[i - 1] == "["):
                ans += tmp
            tmp //= 2 if v == ")" and stack[-1] == "(" else 3
            stack.pop()
    return 0 if stack else ans


string = sys.stdin.readline()
print(sol(string.rstrip()))
