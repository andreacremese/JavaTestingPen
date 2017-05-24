package main.java.algos;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class Dag {
    public ArrayList<Vertice> Vertices;
    public ArrayList<DirectedEdge> Edges;
    private LinkedList<Vertice>[] AdjacencyList;

    public Dag(ArrayList<Vertice> _vertices, ArrayList<DirectedEdge> _edges) {
        Edges = _edges;
        Vertices = _vertices;
    }

    public Dag() {
        Edges = new ArrayList<>();
        Vertices = new ArrayList<>();
    }

    // the length of the array is increased by one as the input is not a zero based
    // set, but one base, meaning the node count starts from 1
    private int AuxArrayDimension() {
        return Vertices.size() + 1;
    }

    private void GenerateAdjacencyList() {
        AdjacencyList = new LinkedList[AuxArrayDimension()];
        for (DirectedEdge e : Edges) {
            Vertice from = e.From;
            Vertice to = e.To;
            // initialize lists if not present
            if (AdjacencyList[from.Value] == null) {
                AdjacencyList[from.Value] = new LinkedList<Vertice>();
            }

            // add on from
            AdjacencyList[from.Value].add(to);
        }
    }

    public int[] TopologicalSort() throws Exception {
        GenerateAdjacencyList();

        int[] result = new int[AuxArrayDimension()];
        // the indegree array has for node value n (position) the indegree
        int[] indegree = new int[AuxArrayDimension()];
        Stack<Vertice> stack = new Stack<Vertice>();

        // compile an array of indegree O(n+m)
        for (int i = 0; i < AdjacencyList.length; i++) {
            // the adjacency list is not present, no outgoing edges from this node
            if (AdjacencyList[i] == null) { continue; }
            // update the indegrees
            for (Vertice e : AdjacencyList[i]) {
                indegree[e.Value]++;
            }
        }

        // find the nodes with indegree zero and push them into the stack (order n).
        for (Vertice v : Vertices) {
            if (indegree[v.Value] == 0) {
                stack.push(v);
            }
        }

        if (stack.empty()) {
            throw new Exception("not a dag - there is a loop");
        }

        int k = 0;

        while (!stack.empty()) {
            // pop from stack
            Vertice current = stack.pop();
            // put value in the result i++;
            result[k] = current.Value;
            k++;
            // see from adjacency list where did it go, update the indegree
            // the adjacency list is not present, no outgoing edges from this node
            if (AdjacencyList[current.Value] == null) { continue; }
            for (Vertice v : AdjacencyList[current.Value]) {
                // if indegree is already zero the node is already processed.
                if (indegree[v.Value] == 0) { continue; }
                indegree[v.Value]--;
                // if one of the nodes updated has indegree == 0 after this, push into stack
                if (indegree[v.Value] == 0) { stack.push(v); }
            }

        }

        return result;
    }
}