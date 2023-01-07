package model.gameSolver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Solves a system of equation where each coefficient is in Z_2
 *
 **/

public class EquationSolverModTwo {

    private Comparator<List<Integer>> comp = new Comparator<List<Integer>>() {
        @Override
        public int compare(List<Integer> o1, List<Integer> o2) {
            for (int i = 0; i < o1.size(); i++) {
                if (o2.get(i) > o1.get(i)) {
                    return 1;
                } else if (o2.get(i) < o1.get(i)) {
                    return -1;
                }
            }
            return 0;
        }
    };

    /**
     * Requires: the augmented matrix of the system to solve
     *           the target vector is the last column in the matrix
     * Ensures: a fully reduced augmented matrix
     * */
    public List<List<Integer>> solveSystem(List<List<Integer>> augMatrix) {
        List<List<Integer>> mCopy = copyMatrix(augMatrix);
        mCopy.sort(comp);
        forwardElimination(mCopy);
        backtrackElimination(mCopy);
        return mCopy;
    }

    private void forwardElimination(List<List<Integer>> m){
        int n = m.size();
        for (int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++){
                if (m.get(i).get(i) == 1 && m.get(j).get(i) == 1){
                List<Integer> newRow = new ArrayList<>();
                for (int k = 0; k < m.get(i).size(); k++){
                    newRow.add(m.get(i).get(k) ^ m.get(j).get(k));
                }
                m.set(j, newRow);
                }
            }
            m.sort(comp);
        }
    }

    private void backtrackElimination(List<List<Integer>> m){
        for (int i = m.size() - 1; i >= 0; i--){
            for (int j = i - 1; j >= 0; j--){
                if (m.get(i).get(i) == 1 && m.get(j).get(i) == 1){
                    List<Integer> newRow = new ArrayList<>();
                    for (int k = 0; k < m.get(i).size(); k++){
                        newRow.add(m.get(i).get(k) ^ m.get(j).get(k));
                    }
                    m.set(j, newRow);
                }
            }
        }
    }

    private List<List<Integer>> copyMatrix(List<List<Integer>> m ){
        List<List<Integer>> out = new ArrayList<>();
        for (List<Integer> is : m){
            List<Integer> newRow = new ArrayList<>(is);
            out.add(newRow);
        }
        return out;
    }
}
