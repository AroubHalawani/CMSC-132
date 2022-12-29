
package imageblocks;
import java.awt.Color;
import java.util.Stack;

import utils.Picture;
public class ImageBlocks {
    static Color BLACK = new Color(0,0,0);
    static Color WHITE = new Color(255,255,255);                
    private int height;
    private int width;
    boolean [][] visited;
    Picture pic;
    public class image{
    	public int x;
    	public int y;
    	public image(int x, int y) {
    		this.x=x;
    		this.y=y;
    	}
    }
    public ImageBlocks(Picture pic) {
    	this.pic = pic;
    	height = pic.height();
    	width = pic.width();
    	visited= new boolean [height][width];
    }
    
    
    private boolean isBlack(int x,int y){
        return pic.get(x,y).equals(BLACK);
    }
    private boolean isWhite(int x,int y){
        return pic.get(x,y).equals(WHITE);
    }
	private void visitedInitializtion() {
		for (int row = 0; row < visited.length; row++) {
			for (int column = 0; column < visited[row].length; column++) {
				visited[row][column] = false;
			}
		}
	}
    /**
     * count the number of image blocks in the given image
     * Counts the number of connected blocks in the binary digital image
     * @return number of black blocks
     */
    public int countConnectedBlocks(){
    	visitedInitializtion();
		int index = 0;
		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				Color color = pic.get(column, row);
				if (visited[row][column] == false && color.equals(BLACK)) {
					Stack<image> stack = new Stack<image>();
					stack.add(new image(row, column));
					visited[row][column] = true;
					recursion(stack);
					index++;
				}
			}
		}
		return index;
	}
    private boolean helper(int column, int row) {
		if(isBlack(column,row) && ! visited[row][column]) {
			return true;
		}
		return false;
	}
    private void recursion(Stack<image> stack) {
		if (stack.size() == 0) {
			return;
		}
		image image = stack.pop();
		if (image.x - 1 >= 0 && helper(image.y,image.x-1)) {
			stack.add(new image(image.x - 1, image.y));
			visited[image.x-1][image.y] = true;
		}
		if (image.x - 1 >= 0 && image.y + 1 < pic.width()&& helper(image.y+1,image.x-1)) {
			stack.add(new image(image.x - 1, image.y + 1));
			visited[image.x-1][image.y+1] = true;
		}
		if (image.y + 1 < pic.width()&& helper(image.y+1,image.x)) {	
			stack.add(new image(image.x, image.y + 1));
			visited[image.x][image.y+1] = true;
		}
		if (image.x + 1 < pic.height() && image.y + 1 < pic.width()&& helper(image.y+1,image.x+1)) {
			stack.add(new image(image.x + 1, image.y + 1));
			visited[image.x+1][image.y+1] = true;
		}
		if (image.x + 1 < pic.height()&& helper(image.y,image.x+1)) {
			stack.add(new image(image.x + 1, image.y));
			visited[image.x+1][image.y] = true;
		}
		if (image.x + 1 < pic.height() && image.y - 1 >= 0 && helper(image.y-1,image.x+1)) {
			stack.add(new image(image.x + 1, image.y - 1));
			visited[image.x+1][image.y-1] = true;
		}
		if (image.y - 1 >= 0&& helper(image.y-1,image.x)) {
			stack.add(new image(image.x, image.y - 1));
			visited[image.x][image.y-1] = true;
		}
		if (image.x - 1 >= 0 && image.y - 1 >= 0 && helper(image.y-1,image.x-1)) {
			stack.add(new image(image.x - 1, image.y - 1));
			visited[image.x-1][image.y-1] = true;
		}
		recursion(stack);
	}
    /**
     * Removes all neighboring black pixels of the provided pixel (x,y)
     * @param x
     * @param y
     * @return updated picture
     */
    public Picture delete(int x, int y) {
    	for (int row = 0; row < visited.length; row++) {
			for (int column = 0; column < visited[row].length; column++) {
				visited[row][column] = false;
			}
		}

		Stack<image> stack = new Stack<image>();
		stack.add(new image(x, y));
		visited[y][x] = true;
		recursion(stack);

		return pic;
	}   
    /**
     * Crops an image by setting all the pixels outside the provided
     * indices to the color white
     * delete everything not inside the bounds of the parameters (inclusive)
     * @param row_start
     * @param col_start
     * @param row_end
     * @param col_end
     * @return updated picture
     */
    public Picture crop(int x_start, int x_end, int y_start, int y_end) {
    	for (int column = 0; column < pic.width(); column++) {
			for (int row = 0; row < pic.height(); row++) {
				boolean X = column < x_start || column > x_end;
				boolean Y = row < y_start || row > y_end;
				if (X && Y) {
					pic.set(column, row, WHITE);
				}
			}
		}
		return pic;
	}
    
    public static void main(String[] args) {
        
        String fileName = "images/14.png";
        Picture p = new Picture(fileName);
        ImageBlocks block = new ImageBlocks(p);
        try{
            int c1 = block.countConnectedBlocks();
            block.delete(4, 8);
            int c2 = block.countConnectedBlocks();
            p = block.crop(0, 90, 0, 90);
            int c3 = block.countConnectedBlocks();
            System.out.println("Number of connected blocks="+c1);
            System.out.println("Number of connected blocks after delete="+c2);
            System.out.println("Number of connected blocks after crop="+c3);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
