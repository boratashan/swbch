package com.example.demo.utils;

import java.util.*;
import java.util.function.Consumer;

public class HNode<T> implements Iterable<HNode<T>>{
    private List<HNode<T>> items;
    private T data;

    public HNode() {
        this(null);
    }

    public HNode(T data) {
        this.data = data;
        items = new ArrayList<>();
    }

    public Optional<T> getData() {
        return Optional.ofNullable(this.data);
    }

    public HNode<T> setData(T data) {
        this.data = data;
        return this;
    }

    public HNode<T> addChildren(T data) {
        items.add(new HNode<>(data));
        return this;
    }

    public HNode<T> getChildren(int index) {
        return items.get(index);
    }


    public void removeChildren(int index) {
        items.remove(index);
    }

    public int getChildrenCount() {
        return this.items.size();
    }

    public boolean hasChildren() {
        return items.size() > 0;
    }

    @Override
    public Iterator<HNode<T>> iterator() {
        return items.iterator();
    }
}
