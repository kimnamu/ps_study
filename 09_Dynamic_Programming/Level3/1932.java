// Copyright@2023 Jihoon Lucas Kim <jihoon.lucas.kim@gmail.com>
// 정수 삼각형
// https://www.acmicpc.net/problem/1932
// 힌트
// 1. 삼각형의 아래로 한줄씩 내려가며, 이전 위 양쪽 값 중 큰 값을 이어 받도록 구한다.
// 2. 각 줄의 양쪽 끝은 선택지 없이 끝값을 그대로 이어받도록 구현한다.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] scores = new int[n][n];
		int[][] dp = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < i + 1; j++) {
				scores[i][j] = Integer.parseInt(st.nextToken());
			}
		}

	    dp[0][0] = scores[0][0];
	    int answer = dp[0][0];
	    for (int i = 1; i < n; i++)
	    {
	        for (int j = 0; j < i + 1; j++)
	        {
	            int score;
	            if (j == 0)
	            {
	                score = dp[i - 1][0];
	            }
	            else if (j == i)
	            {
	                score = dp[i - 1][j - 1];
	            }
	            else
	            {
	                score = Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
	            }
	            dp[i][j] = scores[i][j] + score;

	            if (i == n - 1)
	                answer = Math.max(answer, dp[i][j]);
	        }
	    }
		
		System.out.println(answer);
	}

}
