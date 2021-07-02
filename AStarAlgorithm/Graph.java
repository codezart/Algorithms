

import java.util.ArrayList;
import java.util.HashMap;

class Graph {
    //-- vertex list stores all the vertices.
    //-- edgeMap stores the key value pairs, when the vertex is the key and the edge is all the adjacent edges.
    public ArrayList<Vertex> vertexList;
    public HashMap<Vertex, ArrayList<Edge>> edgeMap;
    public Graph(){
        this.vertexList = new ArrayList<>();
        this.edgeMap = new HashMap<>();
    }
    //-- add a new vertex and initialize its corresponding edgelist
    public void addVertex(Vertex newVertex){
        //check if vertex already exists
        for (Vertex v: vertexList  ) {
            if(v.label.equals(newVertex.label)) {
                System.out.println("cannot insert duplicate node");
                return;
            }
        }
        this.vertexList.add(newVertex);
        edgeMap.put(newVertex, new ArrayList<Edge>());
    }

    //-- Adds an edge to the graph, connecting two nodes with a weight.
    public void addEdge(Vertex sourceNode, Vertex destinationNode, int edgeWeight){
        //-- check if the vertex exists
        if(!edgeMap.containsKey(sourceNode) || !edgeMap.containsKey(destinationNode))
            return;

        //-- check if the edge already exists
        for (Edge currentEdge : edgeMap.get(sourceNode)) {
            if (currentEdge.destinationNode.equals(destinationNode)
                    && currentEdge.edgeWeight == edgeWeight
                    && currentEdge.sourceNode.equals(sourceNode)) {
                System.out.println("Cannot insert, duplicate edge!");
                return;
            }
        }
        // Add edge to graph
        this.edgeMap.get(sourceNode).add(new Edge(sourceNode, destinationNode, edgeWeight));
    }

    // Check if the node exists or not.
    public boolean hasNode(Vertex node){
        for(Vertex v : vertexList)
            if(node.label.equals(v.label))
                return true;

        return false;
    }
    public boolean hasNode(String node){
        for(Vertex v : vertexList)
            if(node.equals(v.label))
                return true;

        return false;
    }
    public Vertex getNode(String nodeLabel){
        for (Vertex v: vertexList ) {
            if(v.label.equals(nodeLabel)){
                return v;
            }
        }
        return null;
    }
    public ArrayList<Vertex> getNodesWithHeuristic0(){
        ArrayList<Vertex> goalList = new ArrayList<>();
        for (Vertex v: vertexList)
            if(v.heuristic == 0)
                goalList.add(v);

        return goalList;
    }
    @Override
    public String toString() {
        return edgeMap.toString();

    }
}
