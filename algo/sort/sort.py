"""
정렬 

"""
import random

# bubble sort
def bubbleSort(arr):
    for i in range(len(arr)):
        for j in range(len(arr) - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]


def selectionSort(arr):
    for i in range(len(arr)):
        minIdx = i
        for j in range(i + 1, len(arr)):
            if arr[minIdx] > arr[j]:
                minIdx = j
        arr[i], arr[minIdx] = arr[minIdx], arr[i]


def insertionSort(arr):
    for i in range(len(arr) - 1):
        if arr[i] <= arr[i + 1]:
            continue
        for j in range(i, -1, -1):
            if arr[j] <= arr[j + 1]:
                break
            arr[j], arr[j + 1] = arr[j + 1], arr[j]


def mergeSort(arr):
    if len(arr) == 1:
        return
    midIdx = len(arr) // 2
    l, r = arr[:midIdx], arr[midIdx:]
    mergeSort(l)
    mergeSort(r)
    merge(arr, l, r)


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


# Not working now
def quickSort(arr):
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
    l, r = arr[:lIdx], arr[lIdx + 1 :]
    quickSort(l)
    quickSort(r)
    for i, v in enumerate(l):
        arr[i] = v
    for i, v in enumerate(r):
        arr[lIdx + i + 1] = v


def radixSort(arr):
    tArr = [0] * len(arr)


arr = [3, 5, 9, 7, 1]
# bubbleSort(arr)
# selectionSort(arr)
# insertionSort(arr)
# quickSort(arr)
print(arr)
