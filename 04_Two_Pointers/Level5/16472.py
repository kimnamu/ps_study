# Copyright@2023 Jihoon Lucas Kim <jihoon.lucas.kim@gmail.com>
# 고냥이
# https://www.acmicpc.net/problem/16472
# 힌트
# 1.투포인터를 이용하여 포인터사이에 포함된 문자열의 종류의 갯수가 N개 이하일 때 가장 긴 거리를 찾으면 된다.
# 2.포인터 사이 문자열 종류의 갯수가 N보다 적으면 더 긴 문자열을 만들 수 있으므로 right 포인터를 오른쪽으로 한칸 옮기면서 해당 알파벳의 갯수와 종류값을 업데이트 해준다.
# 3.포인터 사이 문자열 종류의 갯수가 N보다 크면 문자열이 더 짤아져야 하기 때문에 left 포인터의 문자열을 하나씩 제거해주면서 오른쪽으로 한칸 이동시켜주고, 그때의 알파벳 변화와 종류의 수를 업데이트 해준다.
import sys

if __name__ == "__main__":
    N = int(input())
    strs = sys.stdin.readline().strip()

    check = [0] * 26

    left, right = 0, 0
    check[ord(strs[0]) - ord('a')] = 1

    cnt = 1
    answer = 1

    while True:
        if cnt <= N:
            answer = max(answer, right - left + 1)
            right += 1
            if right == len(strs):
                break
            if check[ord(strs[right]) - ord('a')] == 0:
                cnt += 1
            check[ord(strs[right]) - ord('a')] += 1
        else:
            check[ord(strs[left]) - ord('a')] -= 1
            if check[ord(strs[left]) - ord('a')] == 0:
                cnt -= 1
            left += 1

    print(answer)