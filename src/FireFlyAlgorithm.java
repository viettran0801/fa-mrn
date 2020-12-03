import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FireFlyAlgorithm {

    public static FireFly getBestFireFly(Point p) {


        int numGen = 10;
        int numFirefly = 100;


        List<FireFly> fireFlies = new ArrayList<>();
        for(int i = 1; i <= numFirefly; i ++) {
            fireFlies.add(FireFly.getRandomFireFlyAround(p));
        }
        System.out.printf("[Start]: %s\n", p);
        for(FireFly fireFly : fireFlies) {
            System.out.printf("[Gen]: %s\n", fireFly);
        }
        FireFly bestFireFly = fireFlies.get(0);

        CostFunction.init();;

        for(int gen = 1; gen <= numGen; gen ++) {
            for(FireFly fireFly1 : fireFlies) {
                for (FireFly fireFly2 : fireFlies) {
                    if (fireFly1.cost() < fireFly2.cost()) {
                        fireFly2.moveTo(fireFly1);
                    }
                }
            }
            for(FireFly fireFly : fireFlies) {
                if(fireFly.cost() < bestFireFly.cost()) {
                    bestFireFly = fireFly;
                }
            }
            System.out.printf("[Gen %s]:-----\n", gen);
//            for(FireFly fireFly : fireFlies) {
//                System.out.printf("[FA]: %s\n", fireFly);
//            }
            System.out.printf("[BEST FA]: %s\n", bestFireFly);
            CostFunction.alpha *= CostFunction.alphaDec;
            //Test.gui.drawPoint(bestFireFly.getPoint(), Color.BLUE);
        }

        System.out.println("----[END]--------");
        return bestFireFly;
    }
}
