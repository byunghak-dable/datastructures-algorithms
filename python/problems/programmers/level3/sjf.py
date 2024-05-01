"""
프로그래머스 우선순위 큐 문제
    sjf : short job first

문제 : https://programmers.co.kr/learn/courses/30/lessons/42627#
CPU 프로세스 스케쥴링 : https://junboom.tistory.com/13
"""
import heapq


class Job:
    def __init__(self, job):
        self.req = job[0]
        self.per = job[1]

    def __lt__(l, r):
        return l.per <= r.per


# short job first
def solution(jobs):
    answer = 0
    jobs.sort(key=lambda x: x[0])
    pq = []
    idx = 0
    time = 0
    while idx < len(jobs) or pq:
        while idx < len(jobs) and jobs[idx][0] <= time:
            heapq.heappush(pq, Job(jobs[idx]))
            idx += 1
        if not pq:
            time = jobs[idx][0]
        else:
            job = heapq.heappop(pq)
            answer += time + job.per - job.req
            time += job.per
    return answer // len(jobs)


assert solution([[0, 3], [1, 9], [2, 6]]) == 9
