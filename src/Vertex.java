import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Vertex {
    String name;
    List<Edge> adjacentList;
    // add distance and predeesoor variables here
    int distance;
    Vertex predecessor;
    boolean visited;

    // initialize distance and predecessor here.
    public Vertex(String name) {
        this.name = name;
        adjacentList = new LinkedList<Edge>();
        distance = 0;
        predecessor = null;
        visited = false;
    }

    public Vertex predecessor() {
        return predecessor;
    }

    public int distance() {
        return distance;
    }

    public List<Edge> adjacents() {
        return adjacentList;
    }

    public String toString() {
        return name;
    }

    public void reset() {
        distance = Graph.Infinity;
        predecessor = null;
    }
    
    public String getPath() {

        Stack<Vertex> s = new Stack<Vertex>();
        String path = "";
        Vertex p = this;
        s.push(p);
        while (p.predecessor != null) {
            p = p.predecessor;
            s.push(p);
        }
        while (!s.isEmpty()) {
            String pop = s.pop().name;
            path += pop;
            if (!s.isEmpty())
                path += " => ";

        }
        return path;

    }
}
