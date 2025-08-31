package edu.dnu.fpm.pz;

public class LinkedList<T> implements IList<T> {
    private Node<T> head;
    private int size;
    private static class Node<T>{
        T value;
        Node<T> next;
        Node(T value){
            this.value = value;
        }
        Node(T value, Node<T> next){
            this.value = value;
            this.next = next;
        }
    }
    @Override
    public T get(int index) throws MyException {
        Validator.checkListIndex(index, size());
        Node<T> node = getNodeByIndex(index);
        return node.value;
    }
    private Node<T> getNodeByIndex(int index){
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
    @Override
    public void add(int index, T element) throws MyException {
        if(index == 0){
            addFirst(element);
            return;
        }
        Validator.checkListIndex(index, size());
        Node<T> node = new Node<>(element);
        Node<T> prevNode = getNodeByIndex(index - 1);
        node.next = prevNode.next;
        prevNode.next = node;
        size++;
    }
    @Override
    public void addFirst(T element) {
        Node<T> node = new Node<>(element);
        node.next = head;
        head = node;
        size++;
    }
    @Override
    public void addLast(T element) {
        if(isEmpty()){
            addFirst(element);
            return;
        }
        Node<T> tail = getNodeByIndex(size() - 1);
        tail.next = new Node<>(element);
        size++;
    }
    @Override
    public T remove(int index) throws MyException {
        Validator.checkListIndex(index, size());

        T element;
        if(index == 0){
            element = head.value;
            head = head.next;
        }else {
            Node<T> prevNode = getNodeByIndex(index - 1);
            element = prevNode.next.value;
            prevNode.next = prevNode.next.next;
        }
        size--;

        return element;
    }
    @Override
    public void replace(int index, T element) throws MyException {
        Validator.checkListIndex(index, size());
        Node<T> node = new Node<>(element);
        if(index == 0){
            node.next = head.next;
            head = node;
            return;
        }
        Node<T> prevNode = getNodeByIndex(index - 1);
        node.next = prevNode.next.next;
        prevNode.next = node;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return head == null;
    }
}
