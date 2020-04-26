package com.example.emailprocessorservice.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.HashSet;

public class LinkedList implements Parcelable {
    private Node head;
    public LinkedList(){}
    public LinkedList(Node head){
        this.head = head;
    }

    private LinkedList(Parcel in) {
        head = in.readParcelable(Node.class.getClassLoader());
    }

    /**
     * Add new data to the list.
     * @param message data to add to the list.
     */
    public void append(Message message){
        Node newNode = new Node(message);

        if(this.head == null){
            this.head = newNode;
            return;
        }

        Node aux = this.head;
        while(aux.getNextNode()!=null){
            aux = aux.getNextNode();
        }

        aux.setNextNode(newNode);
    }

    /**
     * Print all values in a linked list.
     */
    public void printList() {
        printList(this.head);
    }

    /**
     * Method to print the values of a linked list starting from a specific node.
     * @param head reference to the first data to print.
     */
    public void printList(Node head) {
        Node aux = head;
        while(aux != null) {
            System.out.println(aux.getMessage());
            aux = aux.getNextNode();
        }
    }

    /**
     * Implementation of a method to reverse the order of the data in the linked list.
     */
    public void reverse(){
        if (this.head == null) {
            return;
        }

        Node aux = this.head;
        Node newHead = null;
        while(aux!=null) {
            Node nextAux = aux.getNextNode();
            aux.setNextNode(newHead);
            newHead = aux;
            aux = nextAux;
        }

        this.head = newHead;
    }

    /**
     * Implementation of a method to find the intersection of two linked lists
     * @param list
     * @return node (reference to the lists intersection)
     */
    public Node findIntersection(LinkedList list) {
        // reverse list to start list traversal from the end to the begining
        this.reverse();
        list.reverse();

        Node ax1 = this.head;
        Node ax2 = list.head;

        // define intersection node reference, if theres is no intersection
        // a null object is returned
        Node intersectionNode = null;

        // while nodes are equal keep looking for the interception
        // if the nodes are different the previous node is the intersection
        while(ax1 != null && ax2!=null && ax1.getMessage() == ax2.getMessage()) {
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

    /**
     * Implementation of a method to remove duplicated data from the linked list.
     */
    public void removeDuplicates() {
        final AbstractSet<Message> hashSet = new HashSet<>();
        Node auxNode = this.head;

        while (auxNode != null) {
            hashSet.add(auxNode.getMessage());
            Node tempNode = auxNode;
            while(tempNode!=null && hashSet.contains(tempNode.getMessage())){
                tempNode = tempNode.getNextNode();
            }
            auxNode.setNextNode(tempNode);
            auxNode = auxNode.getNextNode();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(head, flags);
    }

    public static final Creator<LinkedList> CREATOR = new Creator<LinkedList>() {
        @Override
        public LinkedList createFromParcel(Parcel in) {
            return new LinkedList(in);
        }

        @Override
        public LinkedList[] newArray(int size) {
            return new LinkedList[size];
        }
    };
}
