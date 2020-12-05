package immutable;


import java.util.Iterator;

public class QueueIterator implements Iterator {

    private Node currNode;

    public <Value> QueueIterator(Queue<Value> inpQ) {
        this.currNode = inpQ.peekNode();
    }

    @Override
    public boolean hasNext() {
        return currNode != null;
    }

    @Override
    public Object next() {
        Object currVal = currNode.getData();
        currNode = currNode.getNext();
        return currVal;
    }
}
