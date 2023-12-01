package week4.SnakeGame.field;

import java.util.Objects;

public class Position{
    private int col;
    private int row;

    public Position(){

    }
    public Position(int col, int row){
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Position position = (Position) object;
        return col == position.col && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
