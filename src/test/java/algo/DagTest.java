package test.java.algo;

import main.java.algos.Dag;
import main.java.algos.DirectedEdge;
import main.java.algos.Vertice;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by acremese on 5/24/17.
 */
public class DagTest {

    @Test
    public void CanDoTopologicalSearchOnADag() {
        // arrange
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);
        Vertice v4 = new Vertice(4);
        Vertice v5 = new Vertice(5);

        DirectedEdge e1 = new DirectedEdge(v1, v2);
        DirectedEdge e2 = new DirectedEdge(v1, v3);
        DirectedEdge e3 = new DirectedEdge(v2, v3);
        DirectedEdge e4 = new DirectedEdge(v2, v5);
        DirectedEdge e5 = new DirectedEdge(v3, v4);

        // c#6 version
        //var dag = new Dag(
        //    new List<Vertice> { v1, v2, v3, v4, v5 },
        //    new List<DirectedEdge> { e1, e2, e3, e4, e5 });
//        Dag dag = new Dag(
//                new LinkedList<Vertice>() { {
//                    add(v1);
//                    add(v2);
//                    add(v3);
//                    add(v4);
//                    add(v5);
//
//                }},
//                new LinkedList<DirectedEdge> (){{
//                    add(e1);
//                    add(e2);
//                    add(e3);
//                    add(e4);
//                    add(e5);
//                }});

        Dag dag = new Dag(new ArrayList<>(Arrays.asList(v1,v2,v3,v4,v5)), new ArrayList<>(Arrays.asList(e1,e2,e3,e4,e5)));
        // is there really not a better way in java for in line creation of an object and pass into a method??



        // act
        int[] topologicalSort;
        try {
            topologicalSort = dag.TopologicalSort();
            // assert
            // finding the position of elements
            int[] position = new int[5];
            for (int i = 0; i < topologicalSort.length; i++) {
                position[topologicalSort[i]] = i;

            }

            int p1 = Arrays.asList(topologicalSort).indexOf(1);
            int p2 = Arrays.asList(topologicalSort).indexOf(2);
            int p3 = Arrays.asList(topologicalSort).indexOf(3);
            int p4 = Arrays.asList(topologicalSort).indexOf(4);
            int p5 = Arrays.asList(topologicalSort).indexOf(5);
            // 2 is before 3
            Assert.assertTrue("wrong order!", position[2] < position[3]);
            // 1 is before 2
            Assert.assertTrue("wrong order!",position[1] < position[2]);
            // 1 is before 3
            Assert.assertTrue("wrong order!",position[1] < position[3]);
            // 2 is before 5
            Assert.assertTrue("wrong order!",position[2] < position[5]);
            // 2 is before 4
            Assert.assertTrue("wrong order!",position[2] < position[4]);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
