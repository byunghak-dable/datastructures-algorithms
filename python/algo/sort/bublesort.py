def sort(original_arr: list[int]) -> list[int]:
    arr = original_arr[:]

    for i in range(len(arr)):
        for j in range(len(arr) - i - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]

    return arr


arr = [8, 5, 6, 2, 4]
sorted_arr = sort(arr)

print("original", arr)
print("sorted", sorted_arr)
