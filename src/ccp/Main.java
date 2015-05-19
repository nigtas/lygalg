import java.util.*;
import java.io.*;

public class Main {

  

  public static void main(String[] args) {
    
    long allTime = 0;
      for(int a=0; a<1; ++a) {
        // System.out.println("START");
        long startTime = System.currentTimeMillis();
         int threadNr = 1;
        ArrayList<Thread> threadList = new ArrayList<Thread>();
        try {
            int image[][] = readMatrixFromFile("img2.txt");
        // System.out.println(Arrays.deepToString(image));
        //  for (int[] arr : image) {
        //     System.out.println(Arrays.toString(arr));
        // }
            CCL ccl = new CCL(image, threadNr);
            for(int i=0; i < threadNr; i++){
                threadList.add(new Thread(ccl));
            }
            for(int i=0; i < threadList.size(); i++){
              threadList.get(i).start();         
            };
        for(int i=0; i < threadList.size(); i++){
            threadList.get(i).join();            
        };
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
         allTime = allTime + totalTime;
        // System.out.println("tt " + totalTime);
         }
     System.out.println("avg: " + (double)allTime / (double)100);
    return;      
    }  

    public static int[][] readMatrixFromFile(String filename) throws Exception {
        int[][] matrix;

        File inFile = new File(filename);
        Scanner in = new Scanner(inFile);

        int collumns = in.nextLine().length();
        int rows = 1;

        while (in.hasNextLine()) {
            ++rows;
            in.nextLine();
        }

        in.close();

        matrix = new int[rows][collumns];

        in = new Scanner(inFile);

        int line = 0;
        while (in.hasNextLine()) {
            String currentLine = in.nextLine();
            for (int i = 0; i < currentLine.length(); ++i) {
                matrix[line][i] = Character.getNumericValue(currentLine.charAt(i));
            }
            ++line;
        }                             
        return matrix;
    }

 public static int[][] create2DIntMatrixFromFile(String filename) throws Exception {
    int[][] matrix = {{1}, {2}};

    File inFile = new File(filename);
    Scanner in = new Scanner(inFile);

    
    String[] length = in.nextLine().trim().split("\\s+");
    int intLength = 1;
    do {
        ++intLength;
        in.nextLine();
    } while (in.hasNextLine());
    in.close();
    matrix = new int[intLength][length.length];
    in = new Scanner(inFile);
    int lineCount = 0;
    while (in.hasNextLine()) {
        String[] currentLine = in.nextLine().trim().split("\\s+"); 
        for (int i = 0; i < currentLine.length; i++) {
            matrix[lineCount][i] = Integer.parseInt(currentLine[i]);
        }
        lineCount++;
    }                              
    return matrix;
 }
}
