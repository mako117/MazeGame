public class Character {
    private int x;
    private int y;
    private int score;
    private int speed;
    private int rewardsCollect;

    public Character(Board gameBoard) {
        this.x = 0;
        this.y = 0;
        this.score = 0;
        this.speed = 1;
        this.rewardsCollect = 0;
    }
    public void direction(char input, Block currentBlock) {
        //Block currentBlock = gameBoard.getBlock(x, y); // move to MazeGame
        Mapsite toMoveTo;
        switch(input) {
            case 'W':
            toMoveTo = currentBlock.getSide(Direction.North);
            if(toMoveTo.enter() == true) {
                this.y++;
                // this.y += this.speed;
            }
            case 'S':
            toMoveTo = currentBlock.getSide(Direction.South);
            if(toMoveTo.enter() == true) {
                this.y--;
                // this.y -= this.speed;
            }
            case 'A':
            toMoveTo = currentBlock.getSide(Direction.West);
            if(toMoveTo.enter() == true) {
                this.x--;
                // this.x -= this.speed;
            }
            case 'D':
            toMoveTo = currentBlock.getSide(Direction.East);
            if(toMoveTo.enter() == true) {
                this.x++;
                // this.x += this.speed;
            }
        }
    }
    public void scorechange(int change) {
        this.score += change;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
}
