package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The GridFunc is a class that flicks lights in the grid
 * By flicking the light at position p, the GridFunc will
 * flick the light at p and its adjacent lights in its row and
 * column that are at a distant at most "reach" away.
 * */

public class GridFunc {

    private final int reach;

    public GridFunc(int reach){
        this.reach = reach;
    }

    public int getReach(){ return reach; }

    /**
     * Requires: grid is a n*m matrix of integers, where n and m are non-negative integers.
     *           p is a point within the bounds of the grid
     * Ensures: Elements in grid are XORed by 1 on the two lines, crossing Point2D p, and
     *          that are at most at a distance "reach" away from p in the two directions
     *          All other elements are not modified
     *          The resulting matrix will have the same dimensions
     *          The input is not mutated
     *
     * @param grid n*m matrix of integers
     * @param p point within the bounds of the matrix
     * @return n*m matrix with elements on row p.y and column p.x within range "reach" in either
     *         direction of input grid XORed by 1
     */

    public List<List<Integer>> apply(List<List<Integer>> grid, Point2D p){
        int cell;
        List<List<Integer>> out = copyGrid(grid);
        for (int i = -reach; i <= reach; i++){
            Point2D py = new Point2D(p.getX(), p.getY() + i);
            if (inBounds(out, py)){
                cell = out.get(py.getY()).get(py.getX());
                out.get(py.getY()).set(py.getX(), cell ^ 1);
            }

            Point2D px = new Point2D(p.getX() + i, p.getY());
            if (inBounds(out, px)){
                cell = out.get(px.getY()).get(px.getX());
                out.get(px.getY()).set(px.getX(), cell ^ 1);
            }
        }

        cell = out.get(p.getY()).get(p.getX());
        out.get(p.getY()).set(p.getX(), cell ^ 1);

        return out;
    }

    public List<List<Integer>> apply(List<List<Integer>> grid, int x, int y){
        return apply(grid, new Point2D(x, y));
    }


    private boolean inBounds(List<List<Integer>> grid, Point2D p){
        return 0 <= p.getX() && p.getX() < grid.get(0).size() && 0 <= p.getY() && p.getY() < grid.size();
    }

    private List<List<Integer>> copyGrid(List<List<Integer>> grid){
        List<List<Integer>> out = new ArrayList<>();
        for (List<Integer> row : grid){
            List<Integer> newRow = new ArrayList<>(row);
            out.add(newRow);
        }
        return out;
    }
}
