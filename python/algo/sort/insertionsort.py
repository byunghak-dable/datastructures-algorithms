def sort(input_arr: list[int]) -> list[int]:
    arr = input_arr[:]

    for i in range(len(arr) - 1):
        for j in range(i, -1, -1):
            if arr[j] <= arr[j + 1]:
                break
            arr[j], arr[j + 1] = arr[j + 1], arr[j]

    return arr


arr = [8, 5, 6, 2, 4]
sorted_arr = sort(arr)

print("original", arr)
print("sorted", sorted_arr)
