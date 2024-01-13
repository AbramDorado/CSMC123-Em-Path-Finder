import java.util.ArrayList;

public class ShortestPath {
	/**
	 * <H2>The Selection Menu of the Program</H2>
	 */
	public static void menu() {
		
		System.out.println("[1] Select Map ");
		System.out.println("[2] Find shortest path ");
		System.out.println("[3] Exit ");
		System.out.println();
		System.out.print("Input number: ");
	}
	
	/**
	 * Getting the vertices and finding the shortest path possible 
	 * @param g
	 * @param vertices
	 * @return matrix
	 */
	static int[][] FloydAlgorithm(int[][] g, ArrayList<Character> vertices) {
		int i, j, k;
		int n = vertices.size();
		int[][] travel;
		travel = g;

		int[][] matrix = new int[n][n];
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				if (i != j)
					matrix[i][j] = j + 1;
		}
		for (k = 0; k < n; k++) {
			for (j = 0; j < n; j++) {
				for (i = 0; i < n; i++) {
					if (travel[i][k] + travel[k][j] < travel[i][j]) {
						travel[i][j] = travel[i][k] + travel[k][j];
						matrix[i][j] = matrix[i][k];
					}
				}
			}
		}
		return matrix;
	}
	/**
	 * Printing the results of the selected path
	 * @param travel
	 * @param twoArr
	 * @param vertices
	 * @param start
	 * @param end
	 */
	static void resultOfTravel(int[][] travel, int[][] twoArr, ArrayList<Character> vertices, char start, String end) {
		System.out.println();
		int u, v;
		char getChar = end.charAt(0);
		
		for (int i = 0; i < vertices.size(); i++) {
			for (int j = 0; j < vertices.size(); j++) {
				if (i != j) {
					u = i + 1;
					v = j + 1;
					char b = vertices.get(u - 1);
					char a;
					
					StringBuilder path = new StringBuilder("" + b);
					do {
						u = twoArr[u - 1][v - 1];
						a = vertices.get(u - 1);
						path.append(", ").append(a);
					} while (u != v);
					
					char c = vertices.get(v - 1);
					String c1 = "" + c;
					if (!(vertices.contains(start) && vertices.contains(getChar))) {
						System.out.println("Points were not found");
						return;
					}
					if (start == getChar) {
						int index = vertices.indexOf(getChar);
						System.out.println("Path: \n" + getChar + "\nDistance: \n" + travel[index][index]);
						return;
					}
					if ((start == b && path.toString().contains(end) && end.equals(c1))) 
						System.out.println("Path: \n" + path + "\nDistance: \n" + travel[i][j] + "km");
				}
			}
		}
	}
}