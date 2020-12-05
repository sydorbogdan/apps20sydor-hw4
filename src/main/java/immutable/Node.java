package immutable;


public class Node implements Cloneable {
    private Object data;
    private Node next;

    public Node(Object inpData, Node inpNext) {
        this.data = inpData;
        this.next = inpNext;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object inpData) {
        this.data = inpData;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node inpNext) {
        this.next = inpNext;
    }
}
