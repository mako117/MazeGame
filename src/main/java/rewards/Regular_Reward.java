package rewards;

public class Regular_Reward extends Reward {
	private int regularNr;
	private static int regularCnt = 1;
	
	public Regular_Reward(){
		super(-1,-1,0);
		regularNr = regularCnt;
		regularCnt++;
	}
	
	public Regular_Reward(int x, int y, int s){
		super(x,y,s);
		regularNr = regularCnt;
		regularCnt++;
	}
}
