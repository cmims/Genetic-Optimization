package com.slethron.geneticoptimization.domain.test;

import com.slethron.geneticoptimization.domain.SudokuBoard;
import javafx.util.Pair;
import org.junit.Before;

import java.util.ArrayList;

public class SudokuBoardTest {
    private int[][] board;
    private SudokuBoard sudokuBoard;

    @Before
    public void before() {
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

        for (int[] column : board) {
            for (var j = 0; j < board.length; j++) {
                System.out.print(column[j] == 0 ? ". " : column[j] + " ");
                if (j == 8) {
                    System.out.println();
                }
            }
        }

        var staticCells = new ArrayList<Pair<Integer, Integer>>() {{
            for (var i = 0; i < board.length; i++) {
                for (var j = 0; j < board.length; j++) {
                    if (board[i][j] != 0) {
                        add(new Pair<>(i, j));
                    }
                }
            }
        }};

        sudokuBoard = new SudokuBoard(board, staticCells);
    }
}
