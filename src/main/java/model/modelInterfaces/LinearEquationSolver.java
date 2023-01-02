package model.modelInterfaces;

import java.util.List;

public interface LinearEquationSolver <T extends Number> {
    List<List<T>> solveSystem(List<List<T>> augMatrix);
}
