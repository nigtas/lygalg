import java.util.*;
import java.io.*;

public class CCL implements Runnable {

// direction vectors
static int dx[] = {+1, 0, -1, 0};
static int dy[] = {0, +1, 0, -1};

// matrix dimensions
int rowCount;
int colCount;
int component = 2;
GUI gui;

// the input matrix
int m[][];

// the labels, 0 means unlabeled
int label[][];

//number of threads
int threadNr;

public CCL(int[][] image, int nr) {
  // gui = new GUI(image);
  threadNr = nr;
  rowCount = image.length;
  colCount = image[0].length;
  m = image;
  label = new int[rowCount][colCount];
}

public void writeLabels() {
    PrintWriter writer;
   
    try
    {
      writer = new PrintWriter("output.txt");
      
        for (int i = 0; i<label.length; i++){
            for(int j = 0; j<label[i].length; j++){
              if(label[i][j] < 10){
                 writer.print("0" + label[i][j] + " ");
              }
              else {  writer.print(label[i][j] + " ");
                      
              }

            }
            writer.println();
      }
      if (writer!=null) writer.close(); 
      
    }
    catch (FileNotFoundException e){
      System.out.println("Error: " + e.getMessage());     
    }
  }

public void run(){  
  int nr = Integer.parseInt(Thread.currentThread().getName().replaceAll("\\D+",""));
  // System.out.println("thread is running..." + nr);
  if(threadNr == 1) {
    findComponents(0, rowCount, 0, colCount);
  }
  if(threadNr == 4) {
    switch(nr % 4) {
      case 0 : findComponents(0, rowCount/2, 0, colCount/2);
               break;
      case 1 : findComponents(0, rowCount/2, colCount/2 + 1, colCount);
               break;
      case 2 : findComponents(rowCount/2 + 1, rowCount, 0, colCount/2);
               break;
      case 3 : findComponents(rowCount/2 + 1, rowCount, colCount/2 + 1, colCount);
               break;
    }
  }
  if(threadNr == 8) {
    switch(nr % 8) {
      case 0 : findComponents(0, rowCount/2, 0, colCount/4);
               break;
      case 1 : findComponents(0, rowCount/2, colCount/4 + 1, colCount/2);
               break;
      case 2 : findComponents(rowCount/2 + 1, rowCount, 0, colCount/4);
               break;
      case 3 : findComponents(rowCount/2 + 1, rowCount, colCount/4 + 1, colCount/2);
               break;
      case 4 : findComponents(0, rowCount/2, colCount/2 + 1, colCount/4 * 3);
               break;
      case 5 : findComponents(0, rowCount/2, colCount/4 * 3 + 1, colCount);
               break;
      case 6 : findComponents(rowCount/2 + 1, rowCount, colCount/2 + 1, colCount/4 * 3);
               break;
      case 7 : findComponents(rowCount/2 + 1, rowCount, colCount/4 * 3 + 1, colCount);
               break;
    }
  }
  // System.out.println("end " + nr);
  return;
}  

 void dfs(int x, int y, int current_label) {
  if (x < 0 || x == rowCount) return; // out of bounds
  if (y < 0 || y == colCount) return; // out of bounds
  if (m[x][y] == 0) return; // not marked with 1 in m

  if(label[x][y] > current_label || label[x][y] == current_label) {
    return;
  } //labeled with bigger nr or the same
  
  // mark the current cell
  label[x][y] = current_label;

  //show live labeling
  
  // try{
  //   Thread.sleep(50);
  // }
  // catch(Exception ex) {
  //   System.out.println(ex);
  // }
  // gui.update(label);

  // recursively mark the neighbors
  for (int direction = 0; direction < 4; ++direction) {
    dfs(x + dx[direction], y + dy[direction], current_label);
  }
}

void findComponents(int rowsStart, int rowsFinnish, int colsStart, int colsFinnish) {
  for (int i = rowsStart; i < rowsFinnish; ++i) 
    for (int j = colsStart; j < colsFinnish; ++j) 
      if (label[i][j] == 0 && m[i][j] == 1){
        dfs(i, j, ++component);
      }
  return;
}

// public int[][] getImage() {
//   System.out.println("wtfff");
//   for (int[] arr : label) {
//             System.out.println(Arrays.toString(arr));
//         }
//   return label;
// }

}
