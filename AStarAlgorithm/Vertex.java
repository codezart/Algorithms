package com.company;

class Vertex implements Comparable<Vertex>{
    public String label;
    public int heuristic;
    public int fvalue;
    public Vertex parent;

    public Vertex(String label){
        this.label = label;
    }
    public Vertex(String label, int heuristic){
        this.label = label;
        this.heuristic = heuristic;
    }

    @Override
    public int compareTo(Vertex o) {
        if(this.fvalue < o.fvalue)
            return -1;
        else if(this.fvalue > o.fvalue)
            return 1;
        else
            return this.label.compareTo(o.label);
    }


}