package model;

import model.staticHelpers.AdjacencyMatrixFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that solves a grid
 * */

public class LightsOutSolver {

    private final EquationSolverModTwo solver = new EquationSolverModTwo();

    /**
     * Requires: the grid m has been modified by a GridFunc of reach "reach"
     * Ensures: a list of points that solves the grid
     *
     * @param m the lights out grid to be solved
     * @param reach the reach of the function that has modified the grid
     * @return a List of positions of lights in the grid to click to solve the grid
     * */

    public List<Point2D> solveGrid(List<List<Integer>> m, int reach){
        List<List<Integer>> augM = AdjacencyMatrixFactory.getAdjacencyMatrix(m.size(), reach);
        for (int i = 0; i < m.size() * m.size(); i++){
            augM.get(i).add(m.get(i / m.size()).get(i % m.size()));
        }
        List<List<Integer>> solved = solver.solveSystem(augM);
        List<Point2D> solution = new ArrayList<>();
        for (int i = 0; i < solved.size(); i++){
            if (solved.get(i).get(solved.get(0).size() - 1) == 1){
                solution.add(new Point2D(i % m.size(), i / m.size()));
            }
        }
        return solution;
    }
}
