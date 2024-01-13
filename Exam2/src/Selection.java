import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Selection {
	
	static int defaultValue = 99999;
	/**
	 * <H2>Printing the map</H2>
	 * <p>
	 * reading the file name of the map
	 * functionality if to select another map
	 * functionality to find the shortest path
	 * functionality to exit the Program
	 * </p>
	 */
	
	public static void mapPrinting() {
		
		ShortestPath.menu();
		int size = 2000;
		char[][] arrayGrid = new char[size][size];
		int[][] weightedGraph = new int[size][size];
		int numberOfVertex;
		int[][] twoArr = new int [size][size];
		
		LinkedList<String> lines = new LinkedList<>();
		ArrayList<Character> vertices = new ArrayList<>();
		
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();

		while (num > 0 && num < 4) {
			switch (num) {
				
				//Selecting the Map
				case 1 -> {
					lines.clear();
					numberOfVertex = 0;
					vertices.clear();
					
					//Enter file name
					System.out.println("\nEnter map filename");
					try {
						String file = scanner.next();
						File myObj = new File(file);
						Scanner myReader = new Scanner(myObj);
						while (myReader.hasNextLine()) {
							String data = myReader.nextLine();
							lines.add(data);
						}
						myReader.close();
					} catch (FileNotFoundException e) {
						System.err.println("File not found!!!");
					}
					
					System.out.println("\n       THE GIVEN MAP       ");
					System.out.println("===========================");
					
					for (int i = 0; i < lines.size(); i++) {
						for (int j = 0; j < lines.get(i).length(); j++) {
							arrayGrid[i][j] = lines.get(i).charAt(j);
							if (Character.isLetterOrDigit(lines.get(i).charAt(j))) {
								vertices.add(lines.get(i).charAt(j));
								numberOfVertex++;
							}
						}
					}
					
					int count = 0;
					int col;
					int row = 0;
					for (int i = 0; i < numberOfVertex; i++) {
						for (int j = 0; j < numberOfVertex; j++) {
							weightedGraph[i][j] = 0;
						}
					}
					
					for (int i = 0; i < lines.size(); i++) {
						for (int j = 0; j < lines.get(i).length(); j++) {
							System.out.print(arrayGrid[i][j]);
							if (Character.isLetterOrDigit(arrayGrid[i][j])) {
								col = vertices.indexOf(arrayGrid[i][j]);
								if ((arrayGrid[i][j + 1] == '*')) {
									while (arrayGrid[i][j + 1 + count] == '*')
										count++;
								}
								if (Character.isLetterOrDigit(arrayGrid[i][j + 1 + count])) {
									if (vertices.contains(arrayGrid[i][j + 1 + count]))
										row = vertices.indexOf(arrayGrid[i][j + 1 + count]);
								}
								if (weightedGraph[col][row] == 0 && col != row) {
									weightedGraph[col][row] = count;
									weightedGraph[row][col] = count;
									count = 0;
								}
							}
						}
						System.out.println();
					}
					row = 0;
					int count1 = 0;
					for (int i = 0; i < lines.size(); i++) {
						for (int j = 0; j < lines.get(i).length(); j++) {
							if (Character.isLetterOrDigit(arrayGrid[i][j])) {
								col = vertices.indexOf(arrayGrid[i][j]);
								if (arrayGrid[i + 1][j] == '*') {
									while (arrayGrid[i + 1 + count1][j] == '*')
										count1++;
								}
								if (Character.isLetterOrDigit(arrayGrid[i + 1 + count1][j])) {
									row = vertices.indexOf(arrayGrid[i + 1 + count1][j]);
								}
								if (weightedGraph[col][row] == 0 && col != row) {
									weightedGraph[col][row] = count1;
									weightedGraph[row][col] = count1;
									count1 = 0;
								}
							}
						}
					}
					for (int i = 0; i < numberOfVertex; i++) {
						for (int j = 0; j < numberOfVertex; j++) {
							if (weightedGraph[i][j] == 0 && i != j) {
								weightedGraph[i][j] = defaultValue;
							}
						}
					}
					System.out.println("===========================");
					
					twoArr = ShortestPath.FloydAlgorithm(weightedGraph, vertices);
					System.out.println();
					ShortestPath.menu();
					num = scanner.nextInt();
				}
				
				//Getting the shortest path
				case 2 -> {
					System.out.println("\nEnter start point:");
					String start = scanner.next();
					System.out.println("Enter end point:");
					String end = scanner.next();
					char start1 = start.charAt(0);
					ShortestPath.resultOfTravel(weightedGraph, twoArr, vertices, start1, end);
					System.out.println();
					ShortestPath.menu();
					num = scanner.nextInt();
				}
				//Exiting the Program
				case 3 -> {
					System.out.println("=== End of Program ===");
					return;
				}
			}
		}
		System.out.println("=== End of Program ===");
		scanner.close();
	}
}