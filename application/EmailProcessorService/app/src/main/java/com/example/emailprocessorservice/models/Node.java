package com.example.emailprocessorservice.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Node implements Parcelable {
    private Message message;
    private Node nextNode;

    public Node(){}

    public Node(Message message) {
        this.message = message;
    }

    public Node(Message message, Node nextNode) {
        this.message = message;
        this.nextNode = nextNode;
    }

    private Node(Parcel in) {
        message = in.readParcelable(Message.class.getClassLoader());
        nextNode = in.readParcelable(Node.class.getClassLoader());
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return this.message;
    }

    public void setNextNode(Node nextNode){
        this.nextNode = nextNode;
    }

    public Node getNextNode() {
        return this.nextNode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(message, flags);
        dest.writeParcelable(nextNode, flags);
    }

    public static final Creator<Node> CREATOR = new Creator<Node>() {
        @Override
        public Node createFromParcel(Parcel in) {
            return new Node(in);
        }

        @Override
        public Node[] newArray(int size) {
            return new Node[size];
        }
    };
}
