package immutable;

import immutable.ImmutableLinkedList;

import java.util.Iterator;

public class Queue<Value> implements Iterable<Value> {
    private ImmutableLinkedList data;

    public Queue() {
        this.data = new ImmutableLinkedList();
    }


    public Node peekNode() {
        return this.data.getHead();
    }

    public Value dequeue() {
        Value peeked = (Value) data.getFirst();
        data = data.removeFirst();
        return peeked;
    }

    public void enqueue(Value e) {
        data = data.addLast(e);
    }

    @Override
    public Iterator<Value> iterator() {
        return new QueueIterator(this);
    }
}
