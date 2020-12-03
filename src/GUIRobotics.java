import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class GUIRobotics {

    private Frame mainFrame;
    private Panel controlPanel;
    public MyCanvas canvas;
    private static int size = 500;
    private static double range = 150;
    private static int numOfRange = 10;

    public GUIRobotics(int size, double range, int numOfRange) {
        GUIRobotics.size = size;
        GUIRobotics.range = range;
        GUIRobotics.numOfRange = numOfRange;
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new Frame("GUI for Robot Path Planning");
        mainFrame.setSize(size + 60, size + 60);
        mainFrame.setResizable(false);
        mainFrame.setLayout(new GridLayout(1, 1));
        mainFrame.setLocationRelativeTo(null);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        controlPanel = new Panel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
    }

    public void generateEnvironment() {
        canvas = new MyCanvas();

        controlPanel.add(canvas);

        // draw obstacles
        canvas.drawPoint(Input.start, Color.GREEN);
        canvas.drawPoint(Input.goal, Color.GREEN);

        for (Obstacle obstacle : Input.obstacles) {
            for (int i = 0; i < obstacle.getVertexes().size() - 1; i++) {
                canvas.drawLine(obstacle.getVertexes().get(i), obstacle.getVertexes().get(i + 1));
            }
            canvas.drawLine(obstacle.getVertexes().get(0), obstacle.getVertexes().get(obstacle.getVertexes().size() - 1));
        }

        // draw Oxy
        Graphics2D g2 = (Graphics2D) canvas.getGraphics();
        g2.drawLine(MyCanvas.OX, MyCanvas.OY, MyCanvas.OY, MyCanvas.OY);
        g2.drawLine(MyCanvas.OX, MyCanvas.OY, MyCanvas.OX, MyCanvas.OX);
        g2.drawString("O", MyCanvas.OX - 10, MyCanvas.OY + 10);
        g2.drawString("x", MyCanvas.OY + 5, MyCanvas.OY);
        g2.drawString("y", MyCanvas.OX, MyCanvas.OX);

        // draw gird
        float[] dash1 = { 2f, 0f, 2f };
        BasicStroke bs1 = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
        g2.setStroke(bs1);
        for (int i = 0; i < numOfRange; i++) {
            g2.draw(new Line2D.Double(MyCanvas.OX, MyCanvas.OY - size / range * 10 * (i + 1), MyCanvas.OY,
                    MyCanvas.OY - size / range * 10 * (i + 1)));

            g2.draw(new Line2D.Double(MyCanvas.OX + size / range * 10 * (i + 1), MyCanvas.OY,
                    MyCanvas.OX + size / range * 10 * (i + 1), MyCanvas.OX));

            g2.drawString(String.valueOf(range / numOfRange * (i + 1)),
                    (int) (MyCanvas.OX + size / range * 10 * (i + 1) - 10), MyCanvas.OY + 10);

            g2.drawString(String.valueOf(range / numOfRange * (i + 1)), MyCanvas.OX,
                    (int) (MyCanvas.OY - size / range * 10 * (i + 1)));
        }

        mainFrame.setVisible(true);

    }

    public void drawPoint(Point p) {
        canvas.drawPoint(p);
    }

    public void drawPoint(Point p, Color color) {
        canvas.drawPoint(p, color);
    }


    public static class MyCanvas extends Canvas {

        static int OX = 10;
        static int OY = size - OX;

        private double alpha = size / range;

        public MyCanvas() {
            setSize(size, size);
        }

        public void drawPoint(Point p) {
            Graphics2D g2 = (Graphics2D) getGraphics();
            g2.setColor(Color.BLUE);
            g2.fill(new Ellipse2D.Double(OX + p.getX1() * alpha - 3, OY - p.getX2() * alpha - 3, 6, 6));
        }

        public void drawPoint(Point p, Color color) {
            Graphics2D g2 = (Graphics2D) getGraphics();
            g2.setColor(color);
            g2.fill(new Ellipse2D.Double(OX + p.getX1() * alpha - 3, OY - p.getX2() * alpha - 3, 10, 10));

        }

        public void drawLine(Point p1, Point p2) {
            Graphics2D g2 = (Graphics2D) getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.RED);
            g2.draw(new Line2D.Double(OX + p1.getX1() * alpha, OY - p1.getX2() * alpha, OX + p2.getX1() * alpha, OY - p2.getX2() * alpha));
        }

        public void drawLine(Point p1, Point p2, Color color) {
            Graphics2D g2 = (Graphics2D) getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(2));
            g2.setColor(color);
            g2.draw(new Line2D.Double(OX + p1.getX1() * alpha, OY - p1.getX2() * alpha, OX + p2.getX1() * alpha, OY - p2.getX2() * alpha));
        }

    }

}
