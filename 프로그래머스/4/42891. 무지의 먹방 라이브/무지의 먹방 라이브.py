import heapq

def solution(food_times, k):
    if sum(food_times) <= k:
        return -1

    q = []
    for i in range(len(food_times)):
        heapq.heappush(q, (food_times[i], i + 1))

    sum_time = 0
    prev_time = 0
    food_count = len(food_times)

    while sum_time + ((q[0][0] - prev_time) * food_count) <= k:
        cur_food_time = heapq.heappop(q)[0]
        sum_time += (cur_food_time - prev_time) * food_count
        food_count -= 1
        prev_time = cur_food_time
    
    answer = sorted(q, key = lambda x: x[1])
    return answer[(k - sum_time) % food_count][1]