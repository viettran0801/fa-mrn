import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static GUIRobotics gui = new GUIRobotics(500, 10, 11);
    public static void main(String[] args) throws InterruptedException {
        Input.init();

        // UI
        Thread.sleep(100);
        gui.generateEnvironment();



        Point robot = Input.start;
        while(!robot.isReach()) {
            robot = FireFlyAlgorithm.getBestFireFly(robot).getPoint();
            Thread.sleep(100);
            gui.drawPoint(robot, Color.BLUE);
        }
    }
}
