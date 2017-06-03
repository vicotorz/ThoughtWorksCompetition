package competition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

public class CaseFactory {
	public static int rows;
	public static int cols;
	public static int number;
	public static int delay;

	/**
	 * ��ʼ����������
	 * 
	 * @param row
	 *            ������
	 * @param col
	 *            ������
	 * @return ��ʼ����ά����
	 * @exception Exception
	 * 
	 * @author Victorz & Hollandxp
	 * 
	 * @Time 2017-6-3
	 */
	public static int[][] Init(int row, int col) throws Exception {
		if (row <= 0 || col <= 0) {
			throw new Exception("Invalid param in Init()");
		}
		int[][] array = new int[row][col];
		return array;
	}

	/**
	 * �Ծ������λ�ø�ֵ
	 * 
	 * @param array
	 *            ��������
	 * @param i
	 *            ��Ҫ�ı�ľ���������ֵ
	 * @param j
	 *            ��Ҫ�ı�ľ���������ֵ
	 * @return �������ĸ�ֵ�����ľ���
	 * @author Victorz & Hollandxp
	 * 
	 * @Time 2017-6-3
	 */
	public static int[][] RandomInit(int[][] array, int row, int col, int number) {
		for (int n = 0; n < number; n++) {
			Random random_row = new Random();
			Random random_col = new Random();
			random_row.setSeed(System.currentTimeMillis());
			random_col.setSeed(System.currentTimeMillis() / 2);
			int i = random_row.nextInt(row);
			int j = random_col.nextInt(col);
			if (array[i][j] == 0)
				array = PutDot(array, i, j);
			else
				n--;
		}
		return array;
	}

	/**
	 * �Ծ������λ�ø�ֵ
	 * 
	 * @param array
	 *            ��������
	 * @param i
	 *            ��Ҫ�ı�ľ���������ֵ
	 * @param j
	 *            ��Ҫ�ı�ľ���������ֵ
	 * @return �������ĸ�ֵ�����ľ���
	 * @author Victorz & Hollandxp
	 * 
	 * @Time 2017-6-3
	 */
	public static int[][] PutDot(int[][] array, int i, int j) {
		array[i][j] = 1;
		return array;
	}

	/**
	 * ��ȡ�ļ���ʼ������
	 * 
	 * @param filename
	 *            �ļ���ַ
	 *            
	 * @return ��ֵ�������ľ���
	 * @author Victorz & Hollandxp
	 * 
	 * @Time 2017-6-3
	 */
	public static int[][] InitMatrixFromFile(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			String line = reader.readLine();
			String[] words = line.split(" ");
			rows = Integer.valueOf(words[0]);
			cols = Integer.valueOf(words[1]);
			number = Integer.valueOf(words[2]);
			delay = Integer.valueOf(words[3]);
			System.out.println(rows + "," + cols + "," + number + "," + delay);
			int[][] matrix = new int[rows][cols];
			for (int i = 0; i < rows; i++) {
				line = reader.readLine();
				words = line.split(" ");
				if (words.length < cols)
					throw new Exception(String.format("�ļ���ȡ���󣬵�%d�����ݴ���", i + 2));
				for (int j = 0; j < cols; j++) {
					matrix[i][j] = Integer.valueOf(words[j]);
				}
			}
			return matrix;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �����û��������ʼ������
	 * 
	 * @param rows
	 *            	������
	 * @param cols
	 * 			 	 ��������
	 * @param number
	 * 				������
	 * @param delay
	 * 				�ӳٺ�����
	 *            
	 * @return �����û���������������ľ���
	 * @author Victorz & Hollandxp
	 * 
	 * @Time 2017-6-3
	 */
	public static int[][] UserDefineMatrix(int rows,int cols,int number,int delay){
		CaseFactory.rows = rows;
		CaseFactory.cols = cols;
		CaseFactory.number = number;
		CaseFactory.delay = delay;
		int[][] matrix = null;
		try {
			matrix = CaseFactory.Init(rows, cols);
			matrix = CaseFactory.RandomInit(matrix, rows, cols, number);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matrix;
	}
}
