package competition;

import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;

/**
 * ThoughtWorks ��Ա���人���� ������ ��������Ϸ����
 * 
 * @author Victorz & Hollandxp
 * 
 * @Time 2017-6-3
 * 
 */
public class Competition {
	private static Terminal terminal;

	/**
	 * ��������״̬
	 * 
	 * @param array
	 *            ��������
	 * @param row
	 *            ��������
	 * @param col
	 *            ��������
	 * @return �����ݻ���ľ���
	 * @author Victorz & Hollandxp
	 * 
	 * @Time2017-6-3
	 */
	public static int[][] Update(int[][] array, int row, int col) {
		int[][] current = new int[row][col];// ��ǰ״̬
		int[][] temp = new int[row + 2][col + 2];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				temp[i + 1][j + 1] = array[i][j];
			}
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				current[i][j] = Judge(temp, i + 1, j + 1);
			}
		}
		return current;
	}

	/**
	 * �жϴ���λ�õ�����״̬
	 * 
	 * @param array
	 *            ��������
	 * @param i
	 *            �в���
	 * @param j
	 *            �в���
	 * @return �ݻ��жϺ�λ�õ�����״̬
	 * @author Victorz & Hollandxp
	 * 
	 * @Time2017-6-3
	 */
	public static int Judge(int[][] array, int i, int j) {
		int status = 0;
		status = array[i - 1][j - 1] + array[i - 1][j] + array[i - 1][j + 1] + array[i][j - 1] + array[i][j + 1]
				+ array[i + 1][j - 1] + array[i + 1][j] + array[i + 1][j + 1];
		if (array[i][j] == 1) {
			if (status == 2 || status == 3) {
				return 1;
			} else {
				return 0;
			}
		} else {
			if (status == 3)
				return 1;
			else
				return 0;
		}
	}

	/**
	 * Terminal����ʾ״̬
	 * 
	 * @param maxtrix
	 *            ��������
	 * @param row
	 *            ������
	 * @param col
	 *            ������
	 * @author Victorz & Hollandxp
	 * 
	 * @Time2017-6-3
	 */
	public static void Disp(int[][] matrix, int row, int col) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 0) {
					terminal.applyForegroundColor(Terminal.Color.RED);
					terminal.moveCursor(j, i);
					terminal.putCharacter('+');
				} else {
					terminal.applyForegroundColor(Terminal.Color.WHITE);
					terminal.moveCursor(j, i);
					terminal.putCharacter('*');
				}
			}
		}
	}

	/**
	 * �������0-1״̬
	 * 
	 * @param maxtrix
	 *            ��������
	 * @param row
	 *            ������
	 * @param col
	 *            ������
	 * @author Victorz & Hollandxp
	 * 
	 * @Time2017-6-3
	 */
	public static void Print(int[][] matrix, int row, int col) {
		String info = "/u001b[23";
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 1)
					System.out.print(info);
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}

	/**
	 * Terminal�ն˿���
	 * 
	 * �û�������ʾ���������У��У����������Լ��ӳ�ʱ��
	 *
	 * @author Victorz & Hollandxp
	 * 
	 * @Time2017-6-3
	 */
	public void MTerminal() throws Exception {
		int rows = 10, cols = 10, number = 50;
		int delay = 200;
		int type = 0;
		int[][] matrix = null;

		Scanner scanner = new Scanner(System.in);
		System.out.println("=========================================================");
		System.out.println("===================The Game of Life======================");
		System.out.println("Please Choose Case:");
		System.out.println("1.(3*1)");
		System.out.println("2.(10*1)");
		System.out.println("3.(33*1)");
		System.out.println("4.(41*1)");
		System.out.println("5.(5_3__4)");
		System.out.println("6.(9____10_____3)");
		System.out.println("7.(3___4_12_____3_____10____9)");
		System.out.println("8.User-defined");
		System.out.print("Your choice is :");
		while (true) {
			try {
				String line = scanner.nextLine();
				type = Integer.valueOf(line);
			} catch (Exception e1) {
				System.out.println("Invalid input,please input again!");
				System.out.print("Your choice is :");
				
				continue;
			}
			while (type < 1 || type > 8) {
				System.out.println("Invalid input,please input again!");
				System.out.print("Your choice is :");
				try {
					String line = scanner.nextLine();
					type = Integer.valueOf(line);
				} catch (Exception e1) {
					System.out.println("Invalid input,please input again!");
					System.out.print("Your choice is :");
					continue;
				}
			}
			if (type > 0 && type <= 8)
				break;
		}
		if (type != 8) {
			String str = "case/case" + String.valueOf(type) + ".txt";
			matrix = CaseFactory.InitMatrixFromFile(str);
		} else {
			System.out.print("Please input the row of the matrix:");
			rows = scanner.nextInt();
			System.out.print("Please input the col of the matrix:");
			cols = scanner.nextInt();
			System.out.print("Please input the number of life in the matrix:");
			number = scanner.nextInt();
			System.out.print("Please input the delay time of each refresh (ms):");
			delay = scanner.nextInt();
			matrix = CaseFactory.UserDefineMatrix(rows, cols, number, delay);
		}
		scanner.close();
		rows = CaseFactory.rows;
		cols = CaseFactory.cols;
		number = CaseFactory.number;
		delay = CaseFactory.delay;

		// create the terminal
		terminal = TerminalFacade.createSwingTerminal(cols, rows);

		// required by Lanterna framework to initialise
		terminal.enterPrivateMode();
		terminal.setCursorVisible(false);
		terminal.clearScreen();

		// set close operation so program with exit if "X" clicked
		if (terminal instanceof SwingTerminal) {
			((SwingTerminal) terminal).getJFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		while (true) {
			try {
				// Print(matrix, rows, cols);

				Disp(matrix, rows, cols);
				Thread.sleep(delay);
				terminal.clearScreen();
				matrix = Update(matrix, rows, cols);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new Competition().MTerminal();
	}
}
