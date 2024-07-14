def sort(input_arr: list[int]) -> list[int]:
    arr = input_arr[:]
    n = len(arr)
    gap = n // 2

    while gap > 0:
        for i in range(gap, n):
            temp = arr[i]
            j = i

            while j >= gap and arr[j - gap] > temp:
                arr[j] = arr[j - gap]
                j -= gap

            arr[j] = temp

        gap //= 2

    return arr


arr = [8, 5, 6, 2, 4]
sorted_arr = sort(arr)

print("original", arr)
print("sorted", sorted_arr)
