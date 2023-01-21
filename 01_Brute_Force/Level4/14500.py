# Copyright@2023 Jihoon Lucas Kim <jihoon.lucas.kim@gmail.com>
# 테트로미노
# https://www.acmicpc.net/problem/14500
# 힌트
# 1. 반례세트 : https://www.acmicpc.net/board/view/61597
# 2. dfs를 활용하여 길이(크기)가 4일때까지의 합 중 가장 큰 값을 정답으로 갱신한다.
# 3. 같은 위치의 같은 모양이 중복되지 않도록, limit_i, limit_j를 지정해주어
#    추가되는 블록이 limit_i보다 위에나 limit_i와 같거나 limit_j보다 작은쪽으로 생성되지 않도록 한다.
# 4. dp를 사용하여 이미 점유된 블록은 추가로 점유하지 않도록 한다.

def dfs(i, j, length):
    if i < 0 or i >= N or j < 0 or j >= M:
        return 0
    # 3. 같은 위치의 같은 모양이 중복되지 않도록, limit_i, limit_j를 지정해주어
    #    추가되는 블록이 limit_i보다 위에나 limit_i와 같거나 limit_j보다 작은쪽으로 생성되지 않도록 한다.
    if i < limit_i or (i == limit_i and j < limit_j):
        return 0

    if dp[i][j]:
        return 0

    dp[i][j] = True
    ret = 0

    # 크기가 4가 아니라면 다음 블록을 추가로 생성해야한다. 상하좌우 네 방향으로 시도후 가장 높은 점수로 갱신.
    if length != 4:
        ret = max(ret, dfs(i + 1, j, length + 1))
        ret = max(ret, dfs(i, j + 1, length + 1))
        ret = max(ret, dfs(i - 1, j, length + 1))
        ret = max(ret, dfs(i, j - 1, length + 1))

    # 크기가 1이면 상하좌우 외에도 아래 1칸 + 오른쪽 2칸 혹은 아래 2칸 + 오른쪽 1칸이 가능하다.
    # 예를 들어, 아래 블록 모양
    # 134  14
    # 4    3
    #    , 4
    if length == 1:
        ret = max(ret, dfs(i + 1, j, length + 2) + dfs(i, j + 1, length + 3))
        ret = max(ret, dfs(i + 1, j, length + 3) + dfs(i, j + 1, length + 2))

    # 크기가 2이면 상하좌우 외에도 아래 모양이 가능하다.
    # 124   1    1  1
    #  4   424  42  24
    #    ,    ,  4, 4 ,
    if length == 2:
        ret = max(ret, dfs(i + 1, j, length + 2) + dfs(i, j + 1, length + 2))
        ret = max(ret, dfs(i, j - 1, length + 2) + dfs(i, j + 1, length + 2))
        ret = max(ret, dfs(i, j - 1, length + 2) + dfs(i + 1, j, length + 2))
        ret = max(ret, dfs(i + 1, j, length + 2) + dfs(i, j + 1, length + 2))

    dp[i][j] = False
    ret += scores[i][j]

    return ret


def solve():
    global answer, limit_i, limit_j

    for i in range(N):
        for j in range(M):
            limit_i = i
            limit_j = j
            answer = max(answer, dfs(i, j, 1))


if __name__ == "__main__":
    N, M = map(int, input().split())
    scores = []
    dp = [[False] * M for _ in range(N)]

    for _ in range(N):
        scores.append(list(map(int, input().split())))

    answer, limit_i, limit_j = 0, 0, 0
    solve()

    print(answer)
