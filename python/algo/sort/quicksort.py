import random


# WARN: Not working now
def sort(arr: list[int]):
    if len(arr) < 1:
        return
    pIdx = int(random.randrange(0, len(arr)))
    lastIdx = len(arr) - 1
    arr[pIdx], arr[lastIdx] = arr[lastIdx], arr[pIdx]

    lIdx, rIdx = 0, lastIdx - 1
    while True:
        while lIdx <= lastIdx and arr[lIdx] < arr[pIdx]:
            lIdx += 1
        while rIdx >= 0 and arr[rIdx] > arr[pIdx]:
            rIdx -= 1

        if lIdx >= rIdx:
            arr[lIdx], arr[lastIdx] = arr[pIdx], arr[lIdx]
            break
        arr[lIdx], arr[rIdx] = arr[rIdx], arr[lIdx]
    left, right = arr[:lIdx], arr[lIdx + 1 :]

    sort(left)
    sort(right)

    for i, v in enumerate(left):
        arr[i] = v
    for i, v in enumerate(right):
        arr[lIdx + i + 1] = v


arr = [3, 5, 9, 7, 1]

print("original", arr)

sort(arr)

print("sorted", arr)
