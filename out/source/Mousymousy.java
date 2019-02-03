import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Mousymousy extends PApplet {

final int len = 1280, hei = 960;
Mouse M;
Pickup Cheese;
int gridSize;
int[][] grid;

public void setup() {
  gridSize = 64;
  grid = new int[len / gridSize][hei / gridSize]; // 20W / 15T 
  
  surface.setResizable(true);
  M = new Mouse(3, 3);
  Cheese = new Pickup(5, 5);
}

public void draw() {
  map();
  M.show();
  Cheese.show();
}

public void map() {
  background(0);
  stroke(0);
  fill(195, 82, 19);
  for(int i = 0; i < len/gridSize; i++) { 
    for(int j = 0; j < hei/gridSize; j++) {
      rect(i * gridSize, j * gridSize, gridSize, gridSize);
    }
  } 
}

public void keyPressed() {
  if (key == CODED) {
    if (keyCode == UP && M.y > 0) {
      M.dir = 1;
      grid[M.x][M.y] = 0;
      M.y--;
      if(grid[M.x][M.y] == 2) {
        Cheese.exists = false;
        Cheese.randomLoc();
      } 
      grid[M.x][M.y] = 1;
    } else if (keyCode == DOWN && M.y < 14) {
      M.dir = 3;
      grid[M.x][M.y] = 0;
      M.y++;
      if(grid[M.x][M.y] == 2) {
        Cheese.exists = false;
        Cheese.randomLoc();
      } 
      grid[M.x][M.y] = 1;
    } else if (keyCode == LEFT && M.x > 0) {
      M.dir = 4;
      grid[M.x][M.y] = 0;
      M.x--;
      if(grid[M.x][M.y] == 2) {
        Cheese.exists = false;
        Cheese.randomLoc();
      } 
      grid[M.x][M.y] = 1;
    } else if (keyCode == RIGHT && M.x < 19) {
      M.dir = 2;
      grid[M.x][M.y] = 0;
      M.x++;
      if(grid[M.x][M.y] == 2) {
        Cheese.exists = false;
        Cheese.randomLoc();
      } 
      grid[M.x][M.y] = 1;
    }
  }
}
class Mouse {
  int x, y;
  int dir; // 1 = UP, 2 = RIGHT, 3 = DOWN, 4 = LEFT
  boolean increasing;
  
  PImage mouseUp1;
  PImage mouseUp2;
  PImage mouseLeft1;
  PImage mouseLeft2;
  PImage mouseRight1;
  PImage mouseRight2;
  PImage mouseDown1;
  PImage mouseDown2;

  Mouse(int x_, int y_) {
    x = x_;
    y = y_;
    grid[x][y] = 1;
    dir = 1;
    increasing = true;
    mouseUp1 = loadImage("/Images/Mouse/mouseUp1.png");
    mouseUp2 = loadImage("/Images/Mouse/mouseUp2.png");
    mouseLeft1 = loadImage("/Images/Mouse/mouseLeft1.png");
    mouseLeft2 = loadImage("/Images/Mouse/mouseLeft2.png");
    mouseRight1 = loadImage("/Images/Mouse/mouseRight1.png");
    mouseRight2 = loadImage("/Images/Mouse/mouseRight2.png");
    mouseDown1 = loadImage("/Images/Mouse/mouseDown1.png");
    mouseDown2 = loadImage("/Images/Mouse/mouseDown2.png");
  }
  
  public void show() {
   if(increasing == true) {
     increasing = false;
     switch(dir) {
       case 1:   image(mouseUp1, x * gridSize, y * gridSize);
                 break;
       case 2:   image(mouseRight1, x * gridSize, y * gridSize);
                 break;
       case 3:   image(mouseDown1, x * gridSize, y * gridSize);
                 break;
       case 4:   image(mouseLeft1, x * gridSize, y * gridSize);
                 break;
     }
    
   } else{
     increasing = true;
     switch(dir) {
       case 1:   image(mouseUp2, x * gridSize, y * gridSize);
                 break;
       case 2:   image(mouseRight2, x * gridSize, y * gridSize);
                 break;
       case 3:   image(mouseDown2, x * gridSize, y * gridSize);
                 break;
       case 4:   image(mouseLeft2, x * gridSize, y * gridSize);
                 break;
     }
  }
 }
 
}  
class Pickup {
    int x, y;
    int type; // 1 = Cheese
    boolean exists;

    Pickup(int x_, int y_) {
        x = x_;
        y = y_;
        exists = true;
        grid[x][y] = 2;
    }

    public void show() {
        if(exists == true) {
            stroke(0);
            fill(255, 255, 0);
            ellipse(x * 64 + 32, y * 64 + 32, 32, 32);
        }
    }

    public void randomLoc() {
        x = PApplet.parseInt(random(0, 19));
        y = PApplet.parseInt(random(0, 14));
        grid[x][y] = 2;
        exists = true;
    }
}
  public void settings() {  size(1280,960); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Mousymousy" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
