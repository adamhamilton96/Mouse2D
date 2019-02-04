class Level {
    int[][] levelGrid;
    
    Level() {
        levelGrid = new int[len / gridWidth][hei / gridHeight];
        levelGrid[7][7] = 2;
        levelGrid[7][8] = 2;
    }

    public int[][] getGrid() {
        return levelGrid;
    }

}