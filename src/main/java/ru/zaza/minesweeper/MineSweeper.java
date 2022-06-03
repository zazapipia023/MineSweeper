package ru.zaza.minesweeper;

public class MineSweeper {
    protected int[][] area = new int[8][8];
    protected boolean[][] isChecked = new boolean[8][8];
    private int bombsCreated = 0;

    MineSweeper() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                area[i][j] = 0;
            }
        }

        while(bombsCreated != 10) {
            int i = 0 + (int) (Math.random() * 8);
            int j = 0 + (int) (Math.random() * 8);
            if(area[i][j] == 9) continue;
            area[i][j] = 9;
            bombsCreated++;
        }
        makeNums();

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                isChecked[i][j] = false;
            }
        }

        System.out.println("Created");
    }

    // drawing field in console
    public void drawField() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                System.out.print(area[i][j] + " ");
            }
            System.out.println();
        }
    }

    // making fields with the number of mines around
    public void makeNums() {
        int count;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                count = 0;

                if(area[i][j] == 9) continue;

                // vertical and horizontal check
                if(i > 0 && area[i - 1][j] == 9) count++;
                if(i < 7 && area[i + 1][j] == 9) count++;
                if(j > 0 && area[i][j - 1] == 9) count++;
                if(j < 7 && area[i][j + 1] == 9) count++;
                // diagonal check
                if(i > 0 && j > 0 && area[i - 1][j - 1] == 9) count++;
                if(i < 7 && j < 7 && area[i + 1][j + 1] == 9) count++;
                if(i > 0 && j < 7 && area[i - 1][j + 1] == 9) count++;
                if(i < 7 && j > 0 && area[i + 1][j - 1] == 9) count++;

                area[i][j] = count;
            }
        }
    }

}
