
class Edge {
    public int edgeWeight;
    public Vertex destinationNode, sourceNode;

    public Edge(Vertex sourceNode, Vertex destinationNode, int edgeWeight){
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.edgeWeight = edgeWeight;
    }

}
