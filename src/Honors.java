import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
//Haochen Liu
//COMP251 A3 Q1
public class Honors {
	private class Vertex{
		private final int xCoord;
		private final int yCoord;

		public Vertex(int x,int y){
			this.xCoord = x;
			this.yCoord = y;
		}
		public int getX(){
			return this.xCoord;
		}
		public int getY(){
			return this.yCoord;
		}
	}

	public int min_moves(int[][] board) {
		int nRow = board.length;
		int nColumn = board[0].length;
		if(board[0][0]>=nRow && board[0][0]>= nColumn){
			return -1;
		}
		int[][] result = new int[nRow][nColumn];

		Vertex origin = new Vertex(0,0);
		result[0][0]=1;
		LinkedList<Vertex> q= new LinkedList<>();
		q.add(origin);

		while(result[nRow-1][nColumn-1]==0 && q.size()!=0){
			Vertex u = q.removeFirst();
			int x = u.getX();
			int y = u.getY();
			int step = board[x][y];
			int distance = result[x][y];
			//right y+step
			if((y+step)<nColumn){
				if(result[x][y+step]==0){
					Vertex right = new Vertex(x, y+step);
					q.add(right);
					result[x][y+step] = distance+1;
				}
			}
			//left y-step
			if((y-step)>=0){
				if(result[x][y-step]==0){
					Vertex left = new Vertex(x, y-step);
					q.add(left);
					result[x][y-step] = distance+1;
				}
			}
			//down x+step
			if((x+step)<nRow){
				if(result[x+step][y]==0) {
					Vertex down = new Vertex(x+step, y);
					q.add(down);
					result[x + step][y] = distance+1;
				}
			}
			//up x-step
			if((x-step)>=0){
				if(result[x-step][y]==0 ){
					Vertex up = new Vertex(x-step, y);
					q.add(up);
					result[x-step][y] = distance+1;
				}
			}
		}
		//
		if(result[nRow-1][nColumn-1]==0){
			return -1;
		}
		return result[nRow-1][nColumn-1]-1;
	}

	public void test(String filename) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(filename));
		int num_rows = sc.nextInt();
		int num_columns = sc.nextInt();
		int [][]board = new int[num_rows][num_columns];
		for (int i=0; i<num_rows; i++) {
			char line[] = sc.next().toCharArray();
			for (int j=0; j<num_columns; j++) {
				board[i][j] = (int)(line[j]-'0');
			}

		}
		sc.close();
		int answer = min_moves(board);
		//System.out.println(answer);
	}

	public static void main(String[] args) throws FileNotFoundException{
		Honors honors = new Honors();
		honors.test(args[0]); // run 'javac Honors.java' to compile, then run 'java Honors testfilename'
	}

}

