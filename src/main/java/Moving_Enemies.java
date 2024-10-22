class Moving_Enemies extends Enemies {
    /**
     * Moving Enemies Constructor
     */
    public Moving_Enemies(int init_x, int init_y) {
        super(init_x,init_y);        
    }

    /**
     * Controls the movement of the enemy
     */
    public void Move(MazeGame game,int x, int y){
        int player_x = game.getXposition();
        int player_y = game.getYposition();
        int time = game.getTick();
    //Unfinished, Implement A* Algorithm
    }
}