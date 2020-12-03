import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    public static Point start;
    public static Point goal;
    public static List<Obstacle> obstacles = new ArrayList<>();
    public static double safeRange = 0.01;
    public static double eps = 1e-2;
    public static double zero = 1e-18;
    public static String testFile = "test1.txt";

    public static void init() {
        try {
            Scanner scanner = new Scanner(new File(testFile));
            start = new Point(scanner.nextDouble(), scanner.nextDouble());
            goal = new Point(scanner.nextDouble(), scanner.nextDouble());

            int numObstacle = scanner.nextInt();
            for(int i = 1; i <= numObstacle; i ++) {
                int numVertex = scanner.nextInt();
                Obstacle obstacle = new Obstacle();
                for(int j = 1; j <= numVertex; j ++) {
                    obstacle.add(new Point(scanner.nextDouble(), scanner.nextDouble()));
                }
                obstacles.add(obstacle);
            }

        } catch (FileNotFoundException e) {
            System.err.println("File test not found");
            System.exit(1);
        }


    }
}
