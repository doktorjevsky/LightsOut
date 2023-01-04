package modelTests;

import model.GridModel;
import model.LightsOutSolver;
import model.Point2D;
import model.staticHelpers.RandomModelFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class TestSolver {

    private final LightsOutSolver solver = new LightsOutSolver();


    @Test
    public void testSolverRandom3(){
        for (int i = 0; i < 100; i++) {
            GridModel model = RandomModelFactory.buildModel(3, 3);
            List<Point2D> solution = solver.solveGrid(model.getGrid(), 3);
            boolean result = isSolution(model, solution);
            Assertions.assertTrue(result);
        }

    }

    private boolean isSolution(GridModel model, List<Point2D> solution){
        for (Point2D p : solution){
            model.toggleCell(p);
        }
        return model.lightsAreOut();
    }
}
