# 최소 코인 개수
def countCoinChange(coins: list[int], amount: int) -> int:
    dp = [0] + [amount + 1] * amount
    for i in range(amount + 1):
        for c in coins:
            if i >= c:
                dp[i] = min(dp[i], 1 + dp[i - c])
    return dp[-1] if dp[-1] != amount + 1 else -1


# 모든 코인의 경우의 수 - 공간 복잡도 O(n * m)
def caseCoinChange1(coins: list[int], amount: int) -> int:
    dp = [[1 if i == 0 else 0] * (len(coins) + 1) for i in range(amount + 1)]
    for i in range(1, amount + 1):
        for j in range(len(coins) - 1, -1, -1):
            dp[i][j] = dp[i][j + 1]
            if i >= coins[j]:
                dp[i][j] += dp[i - coins[j]][j]
    return dp[-1][0]


# 모든 코인의 경우의 수 - 공간 복잡도 O(n)
def caseCoinChange2(coins: list[int], amount: int) -> int:
    dp = [1] + [0] * amount
    nDp = dp[:]
    for c in coins:
        for j in range(1, amount + 1):
            nDp[j] = dp[j]
            if j >= c:
                nDp[j] += nDp[j - c]
        dp, nDp = nDp, dp
    return dp[amount]


coins = [1, 2, 5]
amount = 11
print(caseCoinChange2(coins, 5))
