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
  
  void show() {
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