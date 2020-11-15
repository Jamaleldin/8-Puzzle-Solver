package application.pathFinders;

import application.pathFinders.AStar;

public class test {
    public static void main(String[] args) {
        //System.out.println(7%3);
        AStar a = new AStar();
        int[] s0 = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
        int[] s1 = { 5, 1, 2, 3, 4, 0, 6, 7, 8 };

        System.out.println(a.findPath(s1,"mnhatn").size());
    }
}
