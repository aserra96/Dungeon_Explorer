package cs2012final;
/* Angel Serrano
 * CS 2012
 * Section 05
 * Description: This is the dungeon structure generator which places the rooms
 * and levels together (thank you joge)
 */
import java.util.Random;

public class StructureGenerator {

	static boolean[][] structure;
	
	void generate(int vertex) {

        Random generator = new Random();
        structure = new boolean[vertex][vertex];
        
        //Making the graph
        for (int i = 0; i < vertex; i++) {
        	for (int j = 0; j < vertex; j++) {
        		structure[j][i] = false;
        		if (i > 0 && j > 0) {
        			structure[i][i - 1] = true;
                    structure[j - 1][j] = true;
                }
            }
        }
        //Random cycles
        for (int i = 0; i < vertex; i++) {
            if (i == 0 || i == (vertex - 1)) {
                for (int index = 0; index < 2; index++) {
                    if (generator.nextInt(2) == 0) {
                        int vertex_got = generator.nextInt(vertex);
                        structure[i][vertex_got] = true;
                        structure[vertex_got][i] = true;
                    }
                }
            } else {
                if (generator.nextInt(2) == 0) {
                    int vertex_got = generator.nextInt(vertex);
                    structure[i][vertex_got] = true;
                    structure[vertex_got][i] = true;
                }
            }
        }
        //calls upon showing which shows the floors and rooms and door available in each
        //Showing(vertex);
    }

//    private void Showing(int vertex) {
//        for (int i = 0; i < vertex; i++) {
//            for (int j = 0; j < vertex; j++) {
//                if (structure[i][j])
//                    System.out.print("1 ");
//                else
//                    System.out.print("0 ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }
}

