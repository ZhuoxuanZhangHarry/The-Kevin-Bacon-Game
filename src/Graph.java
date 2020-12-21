import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

public class Graph {
    public static int Infinity = Integer.MAX_VALUE;

    private HashMap<String, Vertex> vertexMap;
    private LinkedList<Vertex> actors;
    private LinkedList<Vertex> movies;

    public Graph() {
        vertexMap = new HashMap<String, Vertex>();
        movies = new LinkedList<Vertex>();
        actors = new LinkedList<Vertex>();
    }

    public Vertex getVertex(String name) {
        return vertexMap.get(name);
    }

    HashMap<String, Vertex> vertexMap() {
        return vertexMap;
    }

    public int vertexCount() {
        return vertexMap.size();
    }

    public void addEdge(String source, String dest) {
        Vertex v = getVertex(source);
        Vertex w = getVertex(dest);
        List<Edge> L = v.adjacents();
        L.add(new Edge(w));
    }

    public void printAvailableActors() {
        for (Vertex e : actors)
            System.out.printf("%d: %s\n", e.distance() / 2, e);
    }
    
    public void printAvailableMovies() {
        for (Vertex e : movies)
            System.out.printf("%s\n", e);
    }
    
    public void loadFile(String fName) {
        Scanner scan = null;

        if (fName.substring(0, 4).equals("http")) {

            try {
                scan = new Scanner(new URL(fName).openStream());
            } catch (IOException e) {
                System.out.println("Invalid url");
                System.exit(1);
            }
            ;
        } else {
            try {
                scan = new Scanner(new File(fName));
            } catch (FileNotFoundException e) {
                System.out.println("Invalid file");
                System.exit(1);
            }
            ;
        }
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            int b = line.indexOf('|');
            String name = line.substring(0, b);
            String title = line.substring(b + 1);
            Vertex v = vertexMap.get(name);
            if (v == null) {
                v = new Vertex(name);
                vertexMap.put(name, v);
            }
           /** if (!actors.contains(v)) {
                actors.add(v);
            }*/
            Vertex x = vertexMap.get(title);
            if (x == null) {
                x = new Vertex(title);
                vertexMap.put(title, x);
            }
           /** if (!movies.contains(x)) {
                movies.add(x);
            }*/
            addEdge(name, title);
            addEdge(title, name);
        }

    }

    // This implements the All Paths Single Source algorithm. As it works if it
    // finds that a node has a less-than-infinite distance from the source node
    // it
    // should add the node toeither the abailable movie list or the avaialble
    // actors
    // list.
    // Note that a node of the graph reporesents an actor if its distance is
    // even,
    // and it represents a movie if its distance is odd.
    public void findAllPaths(String s) {
        int countActor = 0;
        int countMovie = 0;
        Vertex start = vertexMap.get(s);
        if (start == null)
            throw new NoSuchElementException("Start vertex not found");
        Queue<Vertex> q = new LinkedList<Vertex>();
        q.add(start);
        start.distance = 0;
        start.visited = true;
        while (!q.isEmpty()) {
            Vertex v = q.poll();
            for (Edge e : v.adjacentList) {
                Vertex w = e.destination;
                if (w.visited == false) {
                    w.distance = v.distance + 1;
                    w.predecessor = v;
                    q.add(w);
                    w.visited = true;
                    if (w.distance % 2 == 0 && w != start) {
                        countActor += 1;
                        actors.add(w);

                    } else if (w.distance % 2 != 0 && w != start) {
                        countMovie += 1;
                        movies.add(w);
                    }
                }
            }
        }
        System.out.println("Finished finding all connections");
        System.out.printf("%d actors and %d movies connected to %s %n",countActor,countMovie,s);
    }

}
