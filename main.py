t = "11100"
t += "1"
tmp = 0
m = 0
for i, v in enumerate(t):
    if v == "0":
        tmp += 1
    else:
        m = max(m, tmp)
        tmp = 0

print(m)
