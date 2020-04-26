
/**
 * 7. Linked List Intersection: If two requests on the queue have linked lists
 * that intersect (like the example below), previous service could be improved
 * to process only the difference between them. Write a method that receives two
 * singly linked lists and return the intersecting node of the two lists (if
 * exists). Note that the intersection is defined by reference, not value. (No
 * need to change previous answer).
 * 
 * Solution time complexity O(n+m-i) (or simply O(n)) where: 1) n is the size of
 * the first linked list; 2) m is the size of the second linked list; and 3) i
 * is the size of the intersection between the lists
 */

import java.util.AbstractSet;
import java.util.HashSet;

public final class Algo7 {

    public static void main(String... args) {
        LinkedList<String> list1 = new LinkedList<>(); // c a e h j b a 
        LinkedList<String> list2 = new LinkedList<>(); // d f j b a


        list1.append("c");
        list1.append("a");
        list1.append("e");
        list1.append("h");
        list1.append("j");
        list1.append("b");
        list1.append("a");

        

        list2.append("d");
        list2.append("f");
        list2.append("j");
        list2.append("b");
        list2.append("a");


        System.out.println("List 1: ");
        list1.printList();
        System.out.println("List 2: ");
        list2.printList();
        
        
        Algo7 algo = new Algo7();
        Node<String> ref = algo.findIntersection(list1, list2);

        System.out.println("Intersection: ");
        list1.printList(ref);
    }
    public Node<String> findIntersection(LinkedList<String> l1, LinkedList<String> l2){
        l1.reverse();
        l2.reverse();

        Node<String> ax1 = l1.getHead(); 
        Node<String> ax2 = l2.getHead(); 

        Node ref = null;
        while(ax1 != null && ax2!=null && ax1.getData() == ax2.getData()) {
            ref = ax1;
            ax1 = ax1.getNextNode();
            ax2 = ax2.getNextNode();
        }
        l1.reverse();
        l2.reverse();
        return ref;
    }
}

class Node<T> {
    private T data;
    private Node<T> nextNode;

    public Node(){}

    public Node(T data) {
        this.data = data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setNextNode(Node<T> nextNode){
        this.nextNode = nextNode;
    }

    public Node<T> getNextNode() {
        return this.nextNode;
    }
}

class LinkedList<T> {
    private Node<T> head;

    public LinkedList(){}
    public LinkedList(Node<T> head){
        this.head = head;
    }

    public Node<T> getHead(){
        return this.head;
    }

    public void append(T data){
        Node<T> newNode = new Node<T>(data);
        
        if(this.head == null){
            this.head = newNode;
            return;
        }

        Node<T> aux = this.head;
        while(aux.getNextNode()!=null){
            aux = aux.getNextNode();
        }

        aux.setNextNode(newNode);
    }

    public void delete(T data) {
        if (this.head == null) {
            return;
        } else if (this.head.getData() == data) {
            this.head = this.head.getNextNode();
            return;
        }
    }

    public void printList() {
        printList(this.head);
    }

    public void printList(Node<T> head) {
        Node<T> aux = head;
        while(aux != null) {
            System.out.println(aux.getData());
            aux = aux.getNextNode();
        }
    }

    public void reverse(){
        if (this.head == null) {
            return;
        }

        Node<T> aux = this.head;
        Node<T> newHead = null;
        while(aux!=null) {
            Node<T> nextAux = aux.getNextNode();
            aux.setNextNode(newHead);
            newHead = aux;
            aux = nextAux;
        }

        this.head = newHead;
    }

    public Node<T> findIntersection(LinkedList<T> list) {
        // reverse list to start list traversal from the end to the begining
        this.reverse();
        list.reverse();

        Node<T> ax1 = this.head; 
        Node<T> ax2 = list.getHead(); 

        // define intersection node reference, if theres is no intersection
        // a null object is returned 
        Node<T> intersectionNode = null;

        // while nodes are equal keep looking for the interception
        // if the nodes are different the previous node is the intersection 
        while(ax1 != null && ax2!=null && ax1.getData() == ax2.getData()) {
            // stores the current node as possible intersection
            intersectionNode = ax1;
            ax1 = ax1.getNextNode();
            ax2 = ax2.getNextNode();
        }

        // reverse lists back
        this.reverse();
        list.reverse();

        // return the intersection
        return intersectionNode;
    }

    public void removeDuplicates() {
        final AbstractSet<T> hashSet = new HashSet<>();
        Node<T> auxNode = this.head;

        while (auxNode != null) { 
            hashSet.add(auxNode.getData());
            Node<T> tempNode = auxNode; 
            while(tempNode!=null && hashSet.contains(tempNode.getData())){ 
                tempNode = tempNode.getNextNode(); 
            } 
            auxNode.setNextNode(tempNode); 
            auxNode = auxNode.getNextNode(); 
        } 
    }
}