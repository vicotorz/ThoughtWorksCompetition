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
	 * 初始化生命矩阵
	 * 
	 * @param row
	 *            矩阵行
	 * @param col
	 *            矩阵列
	 * @return 初始化二维数组
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
	 * 对矩阵具体位置赋值
	 * 
	 * @param array
	 *            生命矩阵
	 * @param i
	 *            需要改变的矩阵行坐标值
	 * @param j
	 *            需要改变的矩阵列坐标值
	 * @return 经过更改赋值生命的矩阵
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
	 * 对矩阵具体位置赋值
	 * 
	 * @param array
	 *            生命矩阵
	 * @param i
	 *            需要改变的矩阵行坐标值
	 * @param j
	 *            需要改变的矩阵列坐标值
	 * @return 经过更改赋值生命的矩阵
	 * @author Victorz & Hollandxp
	 * 
	 * @Time 2017-6-3
	 */
	public static int[][] PutDot(int[][] array, int i, int j) {
		array[i][j] = 1;
		return array;
	}

	/**
	 * 读取文件初始化数据
	 * 
	 * @param filename
	 *            文件地址
	 *            
	 * @return 赋值的生命的矩阵
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
					throw new Exception(String.format("文件读取错误，第%d行数据错误", i + 2));
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
	 * 根据用户的输入初始化数据
	 * 
	 * @param rows
	 *            	矩阵行
	 * @param cols
	 * 			 	 矩阵列数
	 * @param number
	 * 				生命数
	 * @param delay
	 * 				延迟毫秒数
	 *            
	 * @return 产生用户定义参数的生命的矩阵
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
