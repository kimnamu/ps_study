// Copyright@2023 Jihoon Lucas Kim <jihoon.lucas.kim@gmail.com>
// 택배
// https://www.acmicpc.net/problem/1719
// 힌트
// 1. 모든 지점간의 최단 거리를 계산해주는 플로이드 와샬 알고리즘을 이용한다.
//    입력받는 지점의 출발점을 지정해 두었다가 최소거리가 갱신되면 갱신된 지점의 출발점으로 현재 출발점을 바꾸어주는 원리이다.
#include <iostream>
#include <vector>
#define INF 1e9
#define MAXNUM 201
using namespace std;

int n, m;
int a, b, c;
int dist[MAXNUM][MAXNUM];
int from[MAXNUM][MAXNUM];

void floydwarshall()
{
    for (int k = 1; k <= n; k++)
    {
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (dist[i][j] > dist[i][k] + dist[k][j])
                {
                    dist[i][j] = dist[i][k] + dist[k][j];
                    from[i][j] = from[i][k];
                }
            }
        }
    }
}

int main()
{
    cin >> n >> m;

    for (int i = 0; i < MAXNUM; i++)
    {
        for (int j = 0; j < MAXNUM; j++)
        {
            if (i == j)
            {
                dist[i][j] = 0;
            }
            else
            {
                dist[i][j] = INF;
            }
        }
    }

    for (int i = 0; i < m; i++)
    {
        cin >> a >> b >> c;
        dist[a][b] = c;
        dist[b][a] = c;
        from[a][b] = b;
        from[b][a] = a;
    }

    floydwarshall();

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            if (i == j)
            {
                cout << '-' << ' ';
            }
            else
            {
                cout << from[i][j] << ' ';
            }
        }
        cout << '\n';
    }
}