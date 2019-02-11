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

// Grid Allocations 0 = EMPTY, 1 = MOUSE, 2 = WALL, 3 = CHEESE, 4 = KEY
final int len = 640, hei = 640;
Mouse M;
Pickup Cheese;
Pickup Key;
Level level1;
int gridHeight;
int gridWidth;
int gridSize;
int[][] grid;
int score;

public void setup() {
  gridSize = 20;
  gridHeight = height / gridSize;
  gridWidth = width / gridSize;
  
  surface.setResizable(true);
  level1 = new Level();
  grid = level1.getGrid();
  //grid = new int[len / gridWidth][hei / gridHeight]; //20x20
  M = new Mouse(3, 3);
  Cheese = new Pickup(5, 5, 1);
  Key = new Pickup(9, 5, 2);
  score = 0;
}

public void draw() {
  println(frameRate);
  gridHeight = height / gridSize;
  gridWidth = width / gridSize;
  map();
  M.show();
  Cheese.show();
  Key.show();
  fill(255);
  textSize(32);
  text(score, 0, 30);
}

public void map() {
  background(0);
  noStroke();
  for(int i = 0; i < gridSize; i++) { 
    for(int j = 0; j < gridSize; j++) {
      if(grid[i][j] == 2) {
        fill(0);
      } else fill(195, 82, 19);
      rect(i * gridWidth, j * gridHeight, gridWidth, gridHeight);
    }
  } 
}

public void keyPressed() {
  if (key == CODED) {
    if (keyCode == UP && M.y > 0 && grid[M.x][M.y - 1] != 2) {
      M.dir = 1;
      grid[M.x][M.y] = 0;
      M.y--;
      if(grid[M.x][M.y] == 3) {
        Cheese.exists = false;
        Cheese.randomLoc();
      } else if(grid[M.x][M.y] == 4) Key.exists = false;
      grid[M.x][M.y] = 1;
    } else if (keyCode == DOWN && M.y < 19 && grid[M.x][M.y + 1] != 2) {
      M.dir = 3;
      grid[M.x][M.y] = 0;
      M.y++;
      if(grid[M.x][M.y] == 3) {
        Cheese.exists = false;
        Cheese.randomLoc();
      } else if(grid[M.x][M.y] == 4) Key.exists = false;
      grid[M.x][M.y] = 1;
    } else if (keyCode == LEFT && M.x > 0 && grid[M.x - 1][M.y] != 2) {
      M.dir = 4;
      grid[M.x][M.y] = 0;
      M.x--;
      if(grid[M.x][M.y] == 3) {
        Cheese.exists = false;
        Cheese.randomLoc();
      } else if(grid[M.x][M.y] == 4) Key.exists = false;
      grid[M.x][M.y] = 1;
    } else if (keyCode == RIGHT && M.x < 19 && grid[M.x + 1][M.y] != 2) {
      M.dir = 2;
      grid[M.x][M.y] = 0;
      M.x++;
      if(grid[M.x][M.y] == 3) {
        Cheese.exists = false;
        Cheese.randomLoc();
      } else if(grid[M.x][M.y] == 4) Key.exists = false;
      grid[M.x][M.y] = 1;
    }
  }
}
class Level {
    int[][] levelGrid;
    Table table;
    
    Level() { // Pass name to load specific level
        levelGrid = new int[len / gridWidth][hei / gridHeight];
        table = loadTable("E:/Programming/Processing/Mouse/LevelEditor/level/level1.csv", "header");
        for (int i = 0; i < gridSize; i++) {
            for(int j = 0; j < gridSize; j++) {
                levelGrid[i][j] = table.getRow(i).getInt(j);
            }
        }
    }

    public int[][] getGrid() {
        return levelGrid;
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
       case 1:   image(mouseUp1, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
                 break;
       case 2:   image(mouseRight1, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
                 break;
       case 3:   image(mouseDown1, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
                 break;
       case 4:   image(mouseLeft1, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
                 break;
     }
      
   } else{
     increasing = true;
     switch(dir) {
       case 1:   image(mouseUp2, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
                 break;
       case 2:   image(mouseRight2, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
                 break;
       case 3:   image(mouseDown2, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
                 break;
       case 4:   image(mouseLeft2, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
                 break;
     }
  }
 }
 
}  
class Pickup {
    int x, y;
    int type; // 1 = Cheese, 2 = Key
    int img;
    boolean exists;

    PImage cheeseUp;
    PImage cheeseLeft;
    PImage cheeseRight;
    PImage cheeseDown;
    PImage keyUp;

    Pickup(int x_, int y_, int type_) {
        loadImages();
        x = x_;
        y = y_;
        type = type_;
        exists = true;
        if(type == 1) {
            grid[x][y] = 3;
            img = 1;
        } else if(type == 2) {
            grid[x][y] = 4;
        }

    }

    public void show() {
        if(exists == true) {
            stroke(0);
            fill(255, 255, 0);
            if(type == 1) {
                switch(img) {
                    case 1: image(cheeseUp, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
                            break;
                    case 2: image(cheeseLeft, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
                            break;
                    case 3: image(cheeseRight, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
                            break;
                    case 4: image(cheeseDown, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
                            break;
                }
            } else if(type == 2) {
                image(keyUp, x * gridWidth, y * gridHeight, gridWidth, gridHeight);
            }
        }
    }

    public void randomLoc() {
        x = PApplet.parseInt(random(0, 19));
        y = PApplet.parseInt(random(0, 14));
        if(grid[x][y] == 0) grid[x][y] = 3;
        else randomLoc();
        exists = true;
        img = PApplet.parseInt(random(1, 5));
        score++;
    }

    public void loadImages() {
        cheeseUp = loadImage("/Images/Cheese/cheeseUp.png");
        cheeseLeft = loadImage("/Images/Cheese/cheeseLeft.png");
        cheeseRight = loadImage("/Images/Cheese/cheeseRight.png");
        cheeseDown = loadImage("/Images/Cheese/cheeseDown.png");
        keyUp = loadImage("/Images/Keys/keyUp.png");
    }
}
  public void settings() {  size(640,640); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Mousymousy" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
