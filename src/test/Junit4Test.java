package test;

import org.junit.Test;

import competition.CaseFactory;
import competition.Competition;

public class Junit4Test {

	// ≤‚ ‘≥ı ºªØ
	@Test
	public void Test_Init() throws Exception {
		int[][] matrix = CaseFactory.Init(10, 10);
		int[][] answer = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				assert matrix[i][j] == answer[i][j] : "Init_Test assertion failed!";
			}
		}

	}

	// ≤‚ ‘≈◊µ„
	@Test
	public void Test_Pot() throws Exception {
		int[][] answer = new int[10][10];
		int[][] matrix = CaseFactory.Init(10, 10);
		matrix = CaseFactory.PutDot(matrix, 1, 1);
		matrix = CaseFactory.PutDot(matrix, 2, 5);
		matrix = CaseFactory.PutDot(matrix, 6, 7);
		answer[1][1] = 1;
		answer[2][5] = 1;
		answer[6][7] = 1;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				assert matrix[i][j] == answer[i][j] : "Test_Pot assertion failed!";
			}
		}

	}

	// ≤‚ ‘æ≤Ã¨—›±‰£®…˙¥Ê«Èøˆ£©
	@Test
	public void Test_Result() throws Exception {
		int[][] answer = new int[4][4];
		answer[1][1] = 1;
		answer[1][2] = 1;
		answer[2][1] = 1;
		answer[2][2] = 1;
		int[][] matrix = CaseFactory.Init(4, 4);
		matrix = CaseFactory.PutDot(matrix, 1, 1);
		matrix = CaseFactory.PutDot(matrix, 1, 2);
		matrix = CaseFactory.PutDot(matrix, 2, 1);
		matrix = CaseFactory.PutDot(matrix, 2, 2);

		matrix = Competition.Update(matrix, 4, 4);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assert matrix[i][j] == answer[i][j] : "Test_Result assertion failed!";
			}
		}
	}

	// ≤‚ ‘æ≤Ã¨…˙√¸—›±‰£®À¿Õˆ«Èøˆ£©
	@Test
	public void Test_DeadResult() throws Exception {
		int[][] answer = new int[4][4];
		int[][] matrix = CaseFactory.Init(4, 4);
		matrix = CaseFactory.PutDot(matrix, 0, 0);
		matrix = CaseFactory.PutDot(matrix, 0, 3);
		matrix = CaseFactory.PutDot(matrix, 3, 0);
		matrix = CaseFactory.PutDot(matrix, 3, 3);

		matrix = Competition.Update(matrix, 4, 4);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assert matrix[i][j] == answer[i][j] : "Test_DeadResult assertion failed!";
			}
		}
	}

	// ≤‚ ‘≈–∂œ∫Ø ˝
	@Test
	public void Test_Judge() {
		int[][] answer = new int[6][6];
		answer[1][4] = 1;
		answer[2][2] = 1;
		answer[3][2] = 1;
		answer[3][3] = 1;
		answer[4][1] = 1;
		assert Competition.Judge(answer, 1, 4) == 0 : "Test_Judge assertion failed!";
		assert Competition.Judge(answer, 2, 2) == 1 : "Test_Judge assertion failed!";
		assert Competition.Judge(answer, 3, 2) == 1 : "Test_Judge assertion failed!";
		assert Competition.Judge(answer, 3, 3) == 1 : "Test_Judge assertion failed!";
		assert Competition.Judge(answer, 4, 1) == 0 : "Test_Judge assertion failed!";

	}

}
