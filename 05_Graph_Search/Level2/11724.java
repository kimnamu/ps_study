// Copyright@2023 Jihoon Lucas Kim <jihoon.lucas.kim@gmail.com>
//연결 요소의 개수
//https://www.acmicpc.net/problem/11724
//힌트
//1. DFS를 활용하여 1~N까지의 연결 요소를 탐색하며, 염결된 정점이 있으면 방문하도록 하자.
//2. 이때 한번 방문한 정점을 반복해서 방문하지 않도록 check 배열을 이용해 준다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean check[];
	static boolean table[][];
	
	static int solve() {
		int answer = 0;
		
		for (int i = 1; i < N + 1; i++) {
			if (!check[i]) {
				answer++;
				DFS(i);
			}
		}
		
		return answer;
	}
	
	static void DFS(int k) {
		check[k] = true;
		
		for (int i = 1; i <= N; i++) {
			if (!check[i] && table[k][i]) {
				DFS(i);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		check = new boolean[N + 1];
		table = new boolean[N + 1][N + 1];			
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			table[a][b] = true;
			table[b][a] = true;
		}
		
		int answer = solve();
		
		System.out.println(answer);
	}
}