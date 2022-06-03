package ru.zaza.minesweeper;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Controller {
    private MineSweeper game;
    private boolean isEnded = false;
    private int minesRemaining = 10;

    @FXML
    private GridPane pane;
    @FXML
    private Label textMines;

    private Image closedArea = new Image(getClass().getResourceAsStream("closed.png"));
    private Image emptyArea = new Image(getClass().getResourceAsStream("zero.png"));
    private Image flagedArea = new Image(getClass().getResourceAsStream("flaged.png"));
    private Image informArea = new Image(getClass().getResourceAsStream("inform.png"));
    private Image bombedArea = new Image(getClass().getResourceAsStream("bombed.png"));
    private Image bombArea = new Image(getClass().getResourceAsStream("bomb.png"));
    private Image num1 = new Image(getClass().getResourceAsStream("num1.png"));
    private Image num2 = new Image(getClass().getResourceAsStream("num2.png"));
    private Image num3 = new Image(getClass().getResourceAsStream("num3.png"));
    private Image num4 = new Image(getClass().getResourceAsStream("num4.png"));
    private Image num5 = new Image(getClass().getResourceAsStream("num5.png"));
    private Image num6 = new Image(getClass().getResourceAsStream("num6.png"));
    private Image num7 = new Image(getClass().getResourceAsStream("num7.png"));
    private Image num8 = new Image(getClass().getResourceAsStream("num8.png"));

    // starting new game
    public void reset() {
        ObservableList<Node> areas = pane.getChildren();
        for (Node node :
                areas) {
            ImageView temp = (ImageView) node;
            temp.setImage(closedArea);
        }
        minesRemaining = 10;
        textMines.setText(String.valueOf(minesRemaining));
        isEnded = false;
        game = new MineSweeper();
        game.drawField();
    }


    public void startGame(ActionEvent event) {
        reset();
    }

    public void areaClick(MouseEvent event) {
        if(game == null) textMines.setText("Press Start");
        if(isEnded == true) {return;}

        MouseButton button = event.getButton();
        int i, j;
        ImageView temp = (ImageView) event.getPickResult().getIntersectedNode();

        if(button == MouseButton.SECONDARY) {
            if(temp.getImage() == flagedArea) {
                minesRemaining += 1;
                textMines.setText(String.valueOf(minesRemaining));
                temp.setImage(closedArea);
                return;
            }
            if(temp.getImage() != closedArea) return;
            minesRemaining -= 1;
            textMines.setText(String.valueOf(minesRemaining));
            temp.setImage(flagedArea);
            return;
        }

        if(button == MouseButton.PRIMARY) {
            if(temp.getImage() == flagedArea) return;

            if (pane.getRowIndex(temp) == null)
                i = 0;
            else
                i = pane.getRowIndex(temp);
            if (pane.getColumnIndex(temp) == null)
                j = 0;
            else
                j = pane.getColumnIndex(temp);

            if (game.area[i][j] == 9) {
                temp.setImage(bombedArea);
                isEnded = true;
                showBombs(i, j);
                return;
            }

            if (game.area[i][j] != 0) {
                switch (game.area[i][j]) {
                    case 1:
                        temp.setImage(num1);
                        break;
                    case 2:
                        temp.setImage(num2);
                        break;
                    case 3:
                        temp.setImage(num3);
                        break;
                    case 4:
                        temp.setImage(num4);
                        break;
                    case 5:
                        temp.setImage(num5);
                        break;
                    case 6:
                        temp.setImage(num6);
                        break;
                    case 7:
                        temp.setImage(num7);
                        break;
                    case 8:
                        temp.setImage(num8);
                        break;
                }
                return;
            }

            if (game.area[i][j] == 0) {
                temp.setImage(emptyArea);
                game.isChecked[i][j] = true;
                if (i + 1 <= 7) checkArea(i + 1, j, game.isChecked);
                if (j + 1 <= 7) checkArea(i, j + 1, game.isChecked);
                if (i - 1 >= 0) checkArea(i - 1, j, game.isChecked);
                if (j - 1 >= 0) checkArea(i, j - 1, game.isChecked);
                if (i > 0 && j > 0) checkArea(i - 1, j - 1, game.isChecked);
                if (i < 7 && j < 7) checkArea(i + 1, j + 1, game.isChecked);
                if (i > 0 && j < 7) checkArea(i - 1, j + 1, game.isChecked);
                if (i < 7 && j > 0) checkArea(i + 1, j - 1, game.isChecked);
            }
        }


    }

    // checks fields around clicked field
    public void checkArea(int i, int j, boolean[][] isChecked) {
        ObservableList<Node> areas = pane.getChildren();

        if (isChecked[i][j] == true) return;

        if (game.area[i][j] != 0) {
            int row, column;
            switch (game.area[i][j]) {
                case 1:
                    for (Node node :
                            areas) {
                        if (pane.getRowIndex(node) == null)
                            row = 0;
                        else
                            row = pane.getRowIndex(node);
                        if (pane.getColumnIndex(node) == null)
                            column = 0;
                        else
                            column = pane.getColumnIndex(node);
                        if (row == i && column == j) {
                            ImageView temp = (ImageView) node;
                            temp.setImage(num1);
                            return;
                        }
                    }
                    break;
                case 2:
                    for (Node node :
                            areas) {
                        if (pane.getRowIndex(node) == null)
                            row = 0;
                        else
                            row = pane.getRowIndex(node);
                        if (pane.getColumnIndex(node) == null)
                            column = 0;
                        else
                            column = pane.getColumnIndex(node);
                        if (row == i && column == j) {
                            ImageView temp = (ImageView) node;
                            temp.setImage(num2);
                            return;
                        }
                    }
                    break;
                case 3:
                    for (Node node :
                            areas) {
                        if (pane.getRowIndex(node) == null)
                            row = 0;
                        else
                            row = pane.getRowIndex(node);
                        if (pane.getColumnIndex(node) == null)
                            column = 0;
                        else
                            column = pane.getColumnIndex(node);
                        if (row == i && column == j) {
                            ImageView temp = (ImageView) node;
                            temp.setImage(num3);
                            return;
                        }
                    }
                    break;
                case 4:
                    for (Node node :
                            areas) {
                        if (pane.getRowIndex(node) == null)
                            row = 0;
                        else
                            row = pane.getRowIndex(node);
                        if (pane.getColumnIndex(node) == null)
                            column = 0;
                        else
                            column = pane.getColumnIndex(node);
                        if (row == i && column == j) {
                            ImageView temp = (ImageView) node;
                            temp.setImage(num4);
                            return;
                        }
                    }
                    break;
                case 5:
                    for (Node node :
                            areas) {
                        if (pane.getRowIndex(node) == null)
                            row = 0;
                        else
                            row = pane.getRowIndex(node);
                        if (pane.getColumnIndex(node) == null)
                            column = 0;
                        else
                            column = pane.getColumnIndex(node);
                        if (row == i && column == j) {
                            ImageView temp = (ImageView) node;
                            temp.setImage(num5);
                            return;
                        }
                    }
                    break;
                case 6:
                    for (Node node :
                            areas) {
                        if (pane.getRowIndex(node) == null)
                            row = 0;
                        else
                            row = pane.getRowIndex(node);
                        if (pane.getColumnIndex(node) == null)
                            column = 0;
                        else
                            column = pane.getColumnIndex(node);
                        if (row == i && column == j) {
                            ImageView temp = (ImageView) node;
                            temp.setImage(num6);
                            return;
                        }
                    }
                    break;
                case 7:
                    for (Node node :
                            areas) {
                        if (pane.getRowIndex(node) == null)
                            row = 0;
                        else
                            row = pane.getRowIndex(node);
                        if (pane.getColumnIndex(node) == null)
                            column = 0;
                        else
                            column = pane.getColumnIndex(node);
                        if (row == i && column == j) {
                            ImageView temp = (ImageView) node;
                            temp.setImage(num7);
                            return;
                        }
                    }
                    break;
                case 8:
                    for (Node node :
                            areas) {
                        if (pane.getRowIndex(node) == null)
                            row = 0;
                        else
                            row = pane.getRowIndex(node);
                        if (pane.getColumnIndex(node) == null)
                            column = 0;
                        else
                            column = pane.getColumnIndex(node);
                        if (row == i && column == j) {
                            ImageView temp = (ImageView) node;
                            temp.setImage(num8);
                            return;
                        }
                    }
                    break;
            }
            return;
        }

        if (game.area[i][j] == 0) {
            int row, column;
            for (Node node :
                    areas) {
                if (pane.getRowIndex(node) == null)
                    row = 0;
                else
                    row = pane.getRowIndex(node);
                if (pane.getColumnIndex(node) == null)
                    column = 0;
                else
                    column = pane.getColumnIndex(node);
                if (row == i && column == j) {
                    ImageView temp = (ImageView) node;
                    temp.setImage(emptyArea);
                    isChecked[i][j] = true;
                    if (i + 1 <= 7) checkArea(i + 1, j, game.isChecked);
                    if (j + 1 <= 7) checkArea(i, j + 1, game.isChecked);
                    if (i - 1 >= 0) checkArea(i - 1, j, game.isChecked);
                    if (j - 1 >= 0) checkArea(i, j - 1, game.isChecked);
                    if (i > 0 && j > 0) checkArea(i - 1, j - 1, game.isChecked);
                    if (i < 7 && j < 7) checkArea(i + 1, j + 1, game.isChecked);
                    if (i > 0 && j < 7) checkArea(i - 1, j + 1, game.isChecked);
                    if (i < 7 && j > 0) checkArea(i + 1, j - 1, game.isChecked);
                }
            }
        }
    }

    public void showBombs(int x, int y) {
        ObservableList<Node> areas = pane.getChildren();
        int row, column;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (game.area[i][j] == 9 && !(i == x && j == y)) {
                    for (Node node :
                            areas) {
                        if (pane.getRowIndex(node) == null)
                            row = 0;
                        else
                            row = pane.getRowIndex(node);
                        if (pane.getColumnIndex(node) == null)
                            column = 0;
                        else
                            column = pane.getColumnIndex(node);
                        if (row == i && column == j) {
                            ImageView temp = (ImageView) node;
                            temp.setImage(bombArea);
                        }
                    }
                }
            }
        }
    }
}
