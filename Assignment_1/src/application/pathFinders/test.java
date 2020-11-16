package application.pathFinders;

import application.pathFinders.AStar;
import application.pathFinders.utilities.Utilities;

public class test {
    public static void main(String[] args) {

        AStar a = new AStar();
        int[] s0 = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
        int[] s1 = { 5, 1, 2, 3, 4, 8, 6, 7, 0 };
        Utilities u = new Utilities();
        //System.out.println(u.getEucCost(s0,s1));
        System.out.println(a.findPath(s1,"mnhatn"));
        System.out.println(a.CostOfPath());
        System.out.println(a.timeColapsed());
        System.out.println(a.depthOfsearch());
    }
}
