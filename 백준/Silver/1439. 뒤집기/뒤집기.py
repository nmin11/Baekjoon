s = input()
nums = [int(char) for char in s]
zero_islands = 0
one_islands = 0
cur_islands = nums[0]
if cur_islands == 0:
    zero_islands += 1
else:
    one_islands += 1

for i in range(1, len(nums)):
    if nums[i] == cur_islands:
        continue
    elif cur_islands == 0:
        one_islands += 1
        cur_islands = 1
    elif cur_islands == 1:
        zero_islands += 1
        cur_islands = 0

print(min(zero_islands, one_islands))
