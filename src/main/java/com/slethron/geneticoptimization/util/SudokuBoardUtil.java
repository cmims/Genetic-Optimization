package com.slethron.geneticoptimization.util;

import com.slethron.geneticoptimization.domain.SudokuBoard;

public class SudokuBoardUtil {
    private SudokuBoardUtil() {
    }
    
    /**
     * Temporary method to use to get a SudokuBoard until further development of SudokuBoard generator
     *
     * @return SudokuBoard X
     */
    public static SudokuBoard getUnsolvedBoardX() {
        var board = new int[][] {
                { 4, 0, 0, 0, 0, 2, 8, 3, 0 },
                { 0, 8, 0, 1, 0, 4, 0, 0, 2 },
                { 7, 0, 6, 0, 8, 0, 5, 0, 0 },
                { 1, 0, 0, 0, 0, 7, 0, 5, 0 },
                { 2, 7, 0, 5, 0, 0, 0, 1, 9 },
                { 0, 3, 0, 9, 4, 0, 0, 0, 6 },
                { 0, 0, 8, 0, 9, 0, 7, 0, 5 },
                { 3, 0, 0, 8, 0, 6, 0, 9, 0 },
                { 0, 4, 2, 7, 0, 0, 0, 0, 3 }
        };
        
        var staticCells = new boolean[9][9];
        for (var i = 0; i < board.length; i++) {
            for (var j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    staticCells[i][j] = true;
                }
            }
        }
        
        return new SudokuBoard(board, staticCells);
    }
}
