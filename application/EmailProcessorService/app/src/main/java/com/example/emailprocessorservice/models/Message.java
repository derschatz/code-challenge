package com.example.emailprocessorservice.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Message implements Parcelable {
    private int id;
    private String text;

    public Message() {
    }

    public Message(int id, String text) {
        this.id = id;
        this.text = text;
    }

    private Message(Parcel in) {
        id = in.readInt();
        text = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof Message && id == ((Message) obj).getId();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.text);
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };
}
