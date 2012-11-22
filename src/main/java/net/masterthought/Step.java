package net.masterthought;

public class Step implements Comparable{

    public int getId() {
        return id;
    }

    private int id;
    private String text;
    private String filename;

    public Step(int id, String text, String filename){
        this.id = id;
        this.text = text;
        this.filename = filename;
    }

    public String getText() {
        return text;
    }

    @Override
    public int compareTo(Object o) {
         Step step = (Step) o;
         return this.getId() - step.getId();
    }
}
