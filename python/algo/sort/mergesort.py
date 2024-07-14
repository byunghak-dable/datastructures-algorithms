# TODO: fix to use copied array


def sort(arr: list[int]):
    if len(arr) == 1:
        return arr

    midIdx = len(arr) // 2
    left, right = arr[:midIdx], arr[midIdx:]

    sort(left)
    sort(right)
    merge(arr, left, right)


def merge(arr, lArr, rArr):
    trav, lTrav, rTrav = 0, 0, 0
    while lTrav != len(lArr) and rTrav != len(rArr):
        lVal, rVal = lArr[lTrav], rArr[rTrav]
        if lVal < rVal:
            arr[trav] = lVal
            lTrav += 1
        else:
            arr[trav] = rVal
            rTrav += 1
        trav += 1
    (xArr, xTrav) = (rArr, rTrav) if lTrav == len(lArr) else (lArr, lTrav)
    while xTrav != len(xArr):
        arr[trav] = xArr[xTrav]
        xTrav += 1
        trav += 1


arr = [8, 5, 6, 2, 4]

print("original", arr)

sort(arr)

print("sorted", arr)
