package modelTests;

import model.GridFunc;
import model.Point2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestGridFunc {

    private List<List<Integer>> grid2;
    private List<List<Integer>> grid3;


    @BeforeEach
    public void init(){
        grid2 = GenTestGrid.genGrid(2);
        grid3 = GenTestGrid.genGrid(3);
    }

    @Test
    public void testGridFunc2MaxReach(){
        GridFunc f = new GridFunc(grid2.size());
        Point2D p = new Point2D(0, 0);
        List<List<Integer>> after = f.apply(grid2, p);
        Assertions.assertTrue(assertSameSize(grid2, after));
        Assertions.assertTrue(assertInverted(grid2, after, p, f));
        Assertions.assertTrue(assertNotModified(grid2, after, p, f));

    }

    @Test
    public void testGridFunc3MinReach(){
        GridFunc f = new GridFunc(0);
        Point2D p = new Point2D(0, 0);
        List<List<Integer>> after = f.apply(grid3, p);
        Assertions.assertTrue(assertSameSize(grid3, after));
        Assertions.assertTrue(assertInverted(grid3, after, p, f));
        Assertions.assertTrue(assertNotModified(grid3, after, p, f));
    }

    @Test
    public void testGridFunc3MidReachA(){
        GridFunc f = new GridFunc(1);
        Point2D p = new Point2D(0, 0);
        List<List<Integer>> after = f.apply(grid3, p);
        Assertions.assertTrue(assertSameSize(grid3, after));
        Assertions.assertTrue(assertInverted(grid3, after, p, f));
        Assertions.assertTrue(assertNotModified(grid3, after, p, f));
    }

    @Test
    public void testGridFunc3MidReachB(){
        GridFunc f = new GridFunc(1);
        Point2D p = new Point2D(1, 1);
        List<List<Integer>> after = f.apply(grid3, p);
        Assertions.assertTrue(assertSameSize(grid3, after));
        Assertions.assertTrue(assertInverted(grid3, after, p, f));
        Assertions.assertTrue(assertNotModified(grid3, after, p, f));
    }

    @Test
    public void testGridFunc3MaxReach(){
        GridFunc f = new GridFunc(grid3.size());
        Point2D p = new Point2D(0, 0);
        List<List<Integer>> after = f.apply(grid3, p);
        Assertions.assertTrue(assertSameSize(grid3, after));
        Assertions.assertTrue(assertInverted(grid3, after, p, f));
        Assertions.assertTrue(assertNotModified(grid3, after, p, f));
    }


    private boolean assertSameSize(List<List<Integer>> m1, List<List<Integer>> m2){
        return m1.size() == m2.size() && m1.get(0).size() == m2.get(0).size();
    }

    private boolean assertInverted(List<List<Integer>> m1, List<List<Integer>> m2, Point2D p, GridFunc f){

        for (int i = -f.getReach(); i <= f.getReach(); i++){
            Point2D py = new Point2D(p.getX(), p.getY() + i);
            if (inBounds(m1, py)){
                int e1 = m1.get(py.getY()).get(py.getX());
                int e2 = m2.get(py.getY()).get(py.getX());
                if (e1 == e2){
                    return false;
                }
            }
            Point2D px = new Point2D(p.getX() + i, p.getY());
            if (inBounds(m1, px)){
                int e1 = m1.get(px.getY()).get(px.getX());
                int e2 = m2.get(px.getY()).get(px.getX());
                if (e1 == e2){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean assertNotModified(List<List<Integer>> m1, List<List<Integer>> m2, Point2D p, GridFunc f){
        for (int i = 0; i < m1.size(); i++){
            for (int j = 0; j < m1.get(0).size(); j++){
                if (i != p.getY() && j != p.getX()){
                    int e1 = m1.get(i).get(j);
                    int e2 = m2.get(i).get(j);
                    if (e1 != e2){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private <T> boolean inBounds(List<List<T>> m , Point2D p){
        return 0 <= p.getY() && p.getY() < m.size() && 0 <= p.getX() && p.getX() < m.get(0).size();
    }
}
