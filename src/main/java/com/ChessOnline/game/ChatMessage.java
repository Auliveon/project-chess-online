package com.ChessOnline.game;

public class ChatMessage{

    private String author;

    private String text;

    public ChatMessage() {
    }
    public String getText() {
        return this.text;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }
}
