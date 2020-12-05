package immutable;


import immutable.Node;
import immutable.ImmutableList;

public class ImmutableLinkedList implements ImmutableList {

    private Node head;


    public ImmutableLinkedList() {
        this.head = null;
    }

    public ImmutableLinkedList(Object[] inpData) {
        this.addAllCurr(inpData);
    }

    protected Node getHead() {
        return head;
    }

    private void setHead(Node inpHead) {
        this.head = inpHead;
    }

    public ImmutableLinkedList cloneList() {
        ImmutableLinkedList cop = new ImmutableLinkedList(this.toArray());
        return cop;
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        if (head == null) {
            return new ImmutableLinkedList(new Object[]{e});
        } else {
            ImmutableLinkedList cop = this.cloneList();
            Node currNode = cop.getHead();
            while (currNode.getNext() != null) {
                currNode = currNode.getNext();
            }
            currNode.setNext(new Node(e, null));
            return cop;
        }
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        try {
            ImmutableLinkedList cop = this.cloneList();
            if (this.isEmpty() && index == 0) {
                cop.setHead(new Node(e, null));
                return cop;
            }
            if (index == 0) {
                cop.setHead(new Node(e, cop.getHead()));
            } else {
                Node currNode = cop.getHead();
                for (int i = 1; i < index; i++) {
                    currNode = currNode.getNext();
                }
                currNode.setNext(new Node(e, currNode.getNext()));
            }
            return cop;
        } catch (NullPointerException error) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void addToCurr(Object e) {
        Node currNode = this.getHead();
        while (currNode.getNext() != null) {
            currNode = currNode.getNext();
        }
        currNode.setNext(new Node(e, null));
    }

    private void addAllCurr(Object[] c) {
        int start = 0;
        if (this.getHead() == null && c.length > 0) {
            this.setHead(new Node(c[0], null));
            start++;
        }
        for (int i = start; i < c.length; i++) {
            this.addToCurr(c[i]);
        }
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        ImmutableLinkedList cop = this.cloneList();
        int start = 0;
        if (cop.getHead() == null && c.length > 0) {
            cop.setHead(new Node(c[0], null));
            start++;
        }
        for (int i = start; i < c.length; i++) {
            cop.addToCurr(c[i]);
        }
        return cop;
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        try {
            ImmutableLinkedList cop = this.cloneList();
            int start = 0;
            if (index == 0 && cop.getHead() == null && c.length > 0) {
                cop.setHead(new Node(c[0], null));
                Node currNode = cop.getHead();
                start++;
            } else if (c.length == 0) {
                return cop;
            }
            Node currNode = cop.getHead();
            for (int i = 1; i < index; i++) {
                currNode = currNode.getNext();
            }
            for (int i = start; i < c.length; i++) {
                currNode.setNext(new Node(c[i], currNode.getNext()));
                currNode = currNode.getNext();
            }
            return cop;
        } catch (NullPointerException error) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Object get(int index) {
        try {
            Node currNode = this.getHead();
            for (int i = 1; i < index + 1; i++) {
                currNode = currNode.getNext();
            }
            return currNode.getData();
        } catch (NullPointerException error) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        try {
            ImmutableLinkedList cop = this.cloneList();
            Node currNode = cop.getHead();
            if (index == 0) {
                cop.setHead(currNode.getNext());
                return cop;

            }
            for (int i = 1; i < index - 1; i++) {
                currNode = currNode.getNext();
            }
            currNode.setNext(currNode.getNext().getNext());
            return cop;
        } catch (NullPointerException error) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        try {
            ImmutableLinkedList cop = this.cloneList();
            Node currNode = cop.getHead();
            for (int i = 1; i < index + 1; i++) {
                currNode = currNode.getNext();
            }
            currNode.setData(e);
            return cop;
        } catch (NullPointerException error) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int indexOf(Object e) {
        ImmutableLinkedList cop = this.cloneList();
        boolean met = false;
        int rezIndex = 0;
        Node currNode = cop.getHead();
        while (currNode != null) {
            if (currNode.getData() == e) {
                met = true;
                break;
            }

            rezIndex++;
            currNode = currNode.getNext();
        }
        if (met) {
            return rezIndex;
        } else {
            return -1;
        }
    }

    @Override
    public int size() {
        Node currNode = this.getHead();
        int size = 0;
        while (currNode != null) {
            size++;
            currNode = currNode.getNext();
        }
        return size;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return this.getHead() == null;
    }

    @Override
    public Object[] toArray() {
        Object[] rezArr = new Object[this.size()];
        Node currNode = this.getHead();
        int currInd = 0;
        while (currNode != null) {
            rezArr[currInd] = currNode.getData();
            currInd++;
            currNode = currNode.getNext();
        }
        return rezArr;
    }

    @Override
    public String toString() {
        Node currNode = this.getHead();
        String rezString = new String();
        while (currNode != null) {
            rezString += currNode.getData().toString();
            currNode = currNode.getNext();
            if (currNode != null) {
                rezString += ",";
            }
        }
        return rezString;
    }

    public ImmutableLinkedList addFirst(Object e) {
        ImmutableLinkedList cop = this.cloneList();
        if (cop.getHead() == null) {
            cop.setHead(new Node(e, null));
        } else {
            cop.setHead(new Node(e, cop.getHead()));
        }
        return cop;
    }

    public ImmutableLinkedList addLast(Object e) {
        ImmutableLinkedList cop = this.cloneList();
        if (cop.getHead() == null) {
            cop.setHead(new Node(e, null));
            return cop;
        } else {
            Node currNode = cop.getHead();
            while (currNode.getNext() != null) {
                currNode = currNode.getNext();
            }
            currNode.setNext(new Node(e, null));
            return cop;
        }
    }

    public Object getFirst() {
        try {
            return this.getHead().getData();
        } catch (NullPointerException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public Object getLast() {
        try {
            Node currNode = this.getHead();
            while (currNode.getNext() != null) {
                currNode = currNode.getNext();
            }
            return currNode.getData();
        } catch (NullPointerException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public ImmutableLinkedList removeFirst() {
        return this.remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return this.remove(this.size() - 1);
    }

}
