package com.example.connectfour;

public class ConnectFourGame {
    public static final int ROW = 7;
    public static final int COL = 6;
    public static final int EMPTY = 0;
    public static final int BLUE = 1;
    public static final int RED = 2;
    public static final int DISCS = 42;
    private final int[][] boardGrid;
    public int mPlayer;

    public ConnectFourGame() {
        boardGrid = new int[ROW][COL];
    }

    public void newGame() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                boardGrid[row][col] = EMPTY;
            }
        }
        mPlayer = BLUE;
    }

    public boolean isGameOver(){
        if (isWin())
            return true;

        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                if (boardGrid[r][c] == EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isWin(){
        return checkHorizontal() || checkVertical() || checkDiagonal();
    }

    public int getDisc(int rol, int col){
        return boardGrid[rol][col];
    }

    public String getState() {
        StringBuilder boardString = new StringBuilder();
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                boardString.append(boardGrid[row][col]);
            }
        }

        return boardString.toString();
    }

    public boolean checkHorizontal(){
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                int discColor = boardGrid[row][col];

                if (discColor != EMPTY) {
                    if (col + 3 < COL &&
                            boardGrid[row][col + 1] == discColor &&
                            boardGrid[row][col + 2] == discColor &&
                            boardGrid[row][col + 3] == discColor) {
                        return true;
                    }

                    if (col - 3 >= 0 &&
                            boardGrid[row][col - 1] == discColor &&
                            boardGrid[row][col - 2] == discColor &&
                            boardGrid[row][col - 3] == discColor) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkVertical(){
        for (int row = 0; row <= ROW - 4; row++) {
            for (int col = 0; col < COL; col++) {
                int discColor = boardGrid[row][col];

                if (discColor != EMPTY) {
                    boolean win = true;
                    for (int i = 1; i < 4; i++) {
                        if (row + i >= ROW || row + i < 0) {
                            win = false;
                            break;
                        }

                        if (boardGrid[row + i][col] != discColor) {
                            win = false;
                            break;
                        }
                    }
                    if (win) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkDiagonal(){
        for (int row = 0; row <= ROW - 4; row++) {
            for (int col = 0; col <= COL - 4; col++) {
                int discColor = boardGrid[row][col];

                if (discColor != EMPTY) {
                    boolean win = true;
                    for (int i = 1; i < 4; i++) {
                        if (row + i >= ROW || col + i >= COL || row + i < 0 || col + i < 0) {
                            win = false;
                            break;
                        }

                        if (boardGrid[row + i][col + i] != discColor) {
                            win = false;
                            break;
                        }
                    }
                    if (win) {
                        return true;
                    }
                }
            }
        }

        for (int row = 0; row <= ROW - 4; row++) {
            for (int col = COL - 1; col >= 3; col--) {
                int discColor = boardGrid[row][col];

                if (discColor != EMPTY) {
                    boolean win = true;
                    for (int i = 1; i < 4; i++) {
                        if (row + i >= ROW || col - i >= COL || row + i < 0 || col - i < 0) {
                            win = false;
                            break;
                        }

                        if (boardGrid[row + i][col - i] != discColor) {
                            win = false;
                            break;
                        }
                    }
                    if (win) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void setState(String gameState){
        int index = 0;
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                boardGrid[row][col] = Character.getNumericValue(gameState.charAt(index));
                index++;
            }
        }
    }

    public void selectDisc(int row, int col){
        if (col < 0 || col >= COL) {
            System.out.println("Invalid column selection.");
            return;
        }

        for (int r = ROW - 1; r >= 0; r--) {
            if (boardGrid[r][col] == EMPTY) {
                boardGrid[r][col] = mPlayer;
                mPlayer = (mPlayer == BLUE) ? RED : BLUE;
                break;
            }
        }
    }
}