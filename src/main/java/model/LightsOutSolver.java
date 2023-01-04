package model;

import model.modelInterfaces.LinearEquationSolver;
import model.staticHelpers.AdjacencyMatrixFactory;

import java.util.ArrayList;
import java.util.List;

public class LightsOutSolver {

    private LinearEquationSolver<Integer> solver = new EquationSolverModTwo();

    public List<Point2D> solveGrid(List<List<Integer>> m, int reach){
        List<List<Integer>> augM = AdjacencyMatrixFactory.getAdjacencyMatrix(m.size(), reach);
        for (int i = 0; i < m.size() * m.size(); i++){
            augM.get(i).add(m.get(i / m.size()).get(i % m.size()));
        }
        List<List<Integer>> solved = solver.solveSystem(augM);
        List<Point2D> solution = new ArrayList<>();
        for (int i = 0; i < m.size() * m.size(); i++){
            if (m.get(i / m.size()).get(i % m.size()) == 1){
                solution.add(new Point2D(i % m.size(), i / m.size()));
            }
        }
        return solution;
    }
}
