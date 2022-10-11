public class TacTicComp{
	
    private int board[][];
    public static final int EMPTY = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;

    public TacTicComp() {
		board = new int[3][3];    //it will initialize all value in board to 0.
    }

    public int getCoordinates(int i,int j) {
		if(i < 0 || i >= 3) return EMPTY;     //if position (i,j) is outside the boundary it will return EMPTY

		if(j < 0 || j >= 3) return EMPTY;
        return board[i][j];
    }

    public void setCoordinates(int i,int j,int token) {
		if(i < 0 || i >= 3) return;
        if(j < 0 || j >= 3) return;
        board[i][j] = token;
    }

    public int []nextwinnningPlay(int token) {                
		for(int i=0;i<3;i++)                                      //looks for empty spaces
			for(int j=0;j<3;j++)                      //next winning move tells the computer the possible options to play
				if(getCoordinates(i, j)==EMPTY) {
					board[i][j] = token;
                    boolean win = whoWin(token);
                    board[i][j] = EMPTY;
                    if(win) return new int[]{i,j};                //when win boolean is true, return that it has value/something
                }
        return null;
    }

    public int whichToken(int token) {
		return token==ONE ? TWO : ONE;   //if token == ONE then make token two else one
    }

    public int []nextMove(int token) {

        if(getCoordinates(1, 1)==EMPTY) return new int[]{1,1};           //first puts token in the center if empty

        int winMove[] = nextwinnningPlay(token);      //tells you which position is suitable for X to win
        if(winMove!=null) return winMove;          //returns position that has X

        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(getCoordinates(i, j)==EMPTY)
                {
                    board[i][j] = token;
                    boolean ok = nextwinnningPlay(whichToken(token)) == null;     //if next winning move of token(O) == null
                    board[i][j] = EMPTY;
                    if(ok) return new int[]{i,j};        //returns position to block opponent
                }

        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(getCoordinates(i, j)==EMPTY)
                    return new int[]{i,j};                 //use that pos to play token

        return null;
    }
	
    public boolean whoWin(int token) {
		final int DI[]={-1,0,1,1};
		final int DJ[]={1,1,1,0};
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++) {
				if(getCoordinates(i, j)!=token) continue;
				for(int k=0;k<4;k++) {
					int count = 0;
					while(getCoordinates(i+DI[k]*count, j+DJ[k]*count)==token) count++;   //checks if the cell has a value/token if not it will put in token
					if(count==3) return true;
                }
            }
        return false;
    }
	
} 
