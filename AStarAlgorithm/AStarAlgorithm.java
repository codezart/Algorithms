package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class AStarAlgorithm {

    static Graph graph;
    static Vertex startNode;
    static Stack<Vertex> shortestPath;
    static HashMap<String,Integer> visitedList = new HashMap<>();
    static PriorityQueue<Vertex> openList = new PriorityQueue<>();


    public static void main(String[] args) throws FileNotFoundException {
        graph = new Graph();
        //-- read the dat file to get graph details.
        Scanner file = new Scanner(new FileInputStream("graph.dat"));
        Scanner scan;
        if(file.hasNextLine()){
            scan = new Scanner(file.nextLine());

            //-- adds all new vertices with their corresponding heuristic values.
            while(scan.hasNext())
                graph.addVertex(new Vertex(scan.next(), scan.nextInt()));
        }

        // entering edges
        int sourceVertexIterator = 0;
        int destinationVertexIterator = 0;
        int edgeWeight;
        //looping through the dat file and adding edges wherever the edgeweight is not 0
        while(file.hasNextLine()){
            scan = new Scanner(file.nextLine());
            while(scan.hasNext()) {
                edgeWeight = scan.nextInt();
                if (edgeWeight != 0) {
                    graph.addEdge(graph.vertexList.get(sourceVertexIterator), graph.vertexList.get(destinationVertexIterator), edgeWeight);
                }
                destinationVertexIterator++;
            }
            destinationVertexIterator = 0;
            sourceVertexIterator++;
        }

        System.out.println(graph.toString());


        //Taking input from the user {startNode, GoalNodes}
        System.out.print("Enter the starting node:");
        Scanner kb = new Scanner(System.in);
        String sn = kb.next();

        if(!graph.hasNode(sn)){
            System.out.println("incorrect start node entered");
            System.exit(0);
        }else{
            startNode = graph.getNode(sn);
        }

        // Main A* algorithm
        shortestPath = aStarSearch();
        //print shortest path
        System.out.print("Shortest path: ");
        while(!shortestPath.empty()){
            System.out.printf(shortestPath.pop().label+", ");
        }

    }

    private static Stack<Vertex> aStarSearch() {

        // adding the starting node to open list.
        startNode.fvalue = startNode.heuristic;
        openList.add(startNode);

        while(!openList.isEmpty()){

            Vertex v = openList.poll();
            if(goalTest(v)){
                System.out.printf("Node: %s, heuritic: %d, fvalue:%d GOAL NODE; %n",v.label,v.heuristic,v.fvalue);
                return findShortestPath(v);
            }

            System.out.printf("Node: %s, heuritic: %d, fvalue:%d NOT A GOAL NODE; %n",v.label,v.heuristic,v.fvalue);

            visitedList.put(v.label,v.fvalue);
            for (Edge edge : graph.edgeMap.get(v)) {

                Vertex childNode = edge.destinationNode;
                if(!visitedList.containsKey(childNode.label) || !openList.contains(childNode)) {
                    childNode.parent = edge.sourceNode;
                    childNode.fvalue = calculateFValue(edge, childNode);
                    if(!openList.contains(childNode) && !visitedList.containsKey(childNode.label))
                        openList.add(childNode);
                }
                else if (checkIfChildNodePresentWithHigherCost(childNode)){
                    openList.add(childNode);
                }
            }


        }

        return null;
    }

    public static boolean goalTest(Vertex v){
        for(Vertex g : graph.getNodesWithHeuristic0())
            if(v.label.equals(g.label)) {
                return true;
            }
        return false;
    }

    public static boolean checkIfChildNodePresentWithHigherCost(Vertex childNode){
        Iterator<Vertex> i = openList.iterator();
        while(i.hasNext()){
            Vertex qnode = i.next();
            if(childNode.label.equals(qnode.label))
                if(qnode.fvalue < childNode.fvalue)
                    return true;
        }
        return false;
    }
    public static int calculateFValue(Edge edge, Vertex childNode){
        int hvalue = childNode.heuristic;
        int gvalue = edge.edgeWeight + (edge.sourceNode.fvalue - edge.sourceNode.heuristic);
        return (hvalue + gvalue);
    }
    public static Stack<Vertex> findShortestPath(Vertex v){
        Stack<Vertex> shortestPath= new Stack<>();
        Vertex p = v;
        while(!p.label.equals(startNode.label)){
            shortestPath.push(p);
            p = p.parent;
        }
        shortestPath.push(p);
        return shortestPath;
    }
    public static boolean checkVisited(String vertexLabel){
        return visitedList.containsKey(vertexLabel);
    }

}

