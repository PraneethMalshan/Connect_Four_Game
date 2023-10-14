package lk.ijse.dep.service;

public class BoardImpl implements Board {
    private final Piece[][] pieces = new Piece[6][5];
    private final BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 5; ++j) {
                this.pieces[i][j] = Piece.EMPTY;
            }
        }

        this.boardUI = boardUI;
    }

    public BoardUI getBoardUI() {
        return this.boardUI;
    }

    public int findNextAvailableSpot(int col) {
        for (int i = 0; i < 5; ++i) {
            if (this.pieces[col][i] == Piece.EMPTY) {
                return i;
            }
        }

        return -1;
    }

    public boolean isLegalMove(int col) {
        return this.findNextAvailableSpot(col) != -1;
    }

    public boolean existLegalMoves() {
        for (int col = 0; col < 6; ++col) {
            if (this.isLegalMove(col)) {
                return true;
            }
        }

        return false;
    }

    public void updateMove(int col, Piece move) {
        this.pieces[col][this.findNextAvailableSpot(col)] = move;
    }

    public Winner findWinner() {

        int rowCount;

        for (int col = 0; col < NUM_OF_COLS; col++) {
            int rowForLoop = findNextAvailableSpot(col) == -1 ? NUM_OF_ROWS : findNextAvailableSpot(col);
            System.out.println("rowForLoop " + rowForLoop);
            rowCount = 0;
            System.out.println("col " + col);

            for (int row = 1; row < rowForLoop; row++) {
                System.out.println(row);
                System.out.println("pieces[col][row]" + pieces[col][row]);
                if (pieces[col][row] .equals(pieces[col][row-1])) {
                    rowCount++;
                    System.out.println("rowCount " + rowCount);
                    if (rowCount == 3) {

                        return new Winner(pieces[col][row], col, row-3, col, row );
                    }
                } else {
                    rowCount = 0;
                }
            }

        }


        for(int row = 0; row <NUM_OF_ROWS; ++row) {
            int count = 1;

            for(int col = 1; col <NUM_OF_COLS; ++col) {
                if (pieces[col][row] == pieces[col-1][row] ) {
                    count++;
                    if (count == 4) {
                        return new Winner(pieces[col][row], col - 3, row, col, row);
                    }
                } else {
                    count = 1;

                }
            }
        }
        return new Winner(Piece.EMPTY);
    }

}
