# 이차 배열 90도 회전
def rotate90(arr):
    return list(zip(*arr[::-1]))


arr = [1, 1, 4, 2, 3, 2, 2, 3]
print(arr.index(2))
