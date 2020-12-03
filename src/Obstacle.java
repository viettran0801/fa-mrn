import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Obstacle {
    private List<Point> vertexes;

    public Obstacle() {
        vertexes = new ArrayList<>();
    }

    public Obstacle(Point ...vertexes) {
        this.vertexes = Arrays.asList(vertexes);
    }

    public void add(Point vertex) {
        vertexes.add(vertex);
    }

    public List<Point> getVertexes() {
        return vertexes;
    }

    public void setVertexes(List<Point> vertexes) {
        this.vertexes = vertexes;
    }
}
