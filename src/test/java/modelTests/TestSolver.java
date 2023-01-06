package modelTests;

import model.GridModel;
import model.LightsOutSolver;
import model.Point2D;
import model.staticHelpers.RandomModelFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;


public class TestSolver {

    private final LightsOutSolver solver = new LightsOutSolver();
    private final Random rd = new Random();


    @Test
    public void testSolverRandom3(){
        for (int i = 0; i < 100; i++) {
            GridModel model = RandomModelFactory.buildModel(3, 3);
            List<Point2D> solution = solver.solveGrid(model.getGrid(), 3);
            boolean result = isSolution(model, solution);
            Assertions.assertTrue(result);
        }

    }

    @Test
    public void testSolverRandomSize(){
        for (int i = 0; i < 200; i++){
            int size = rd.nextInt(20) + 2;
            int reach = size;
            GridModel model = RandomModelFactory.buildModel(reach, size);
            List<Point2D> solution = solver.solveGrid(model.getGrid(), reach);
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
