package controllers.commands;

import javafx.application.Platform;
import model.GameFacade;
import model.gameSolver.LightsOutSolver;
import model.Point2D;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class for running a solution at a human speed
 * */

public class RunSolutionCommand implements ICommand{

    private GameFacade facade;
    private final LightsOutSolver solver = new LightsOutSolver();
    private int  index = 0;
    private Timer timer = new Timer();


    public RunSolutionCommand(GameFacade facade){
        this.facade = facade;
    }

    @Override
    public void apply() {
        List<Point2D> solution = solver.solveGrid(facade.getGrid(), facade.getReach());
        runSolution(solution);

    }

    private void runSolution(List<Point2D> solution){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (index < solution.size()){
                        facade.toggleCell(solution.get(index));
                        index++;
                    } else {
                        this.cancel();
                        timer.cancel();
                    }

                });

            }
        }, 0, 300);


    }
}
