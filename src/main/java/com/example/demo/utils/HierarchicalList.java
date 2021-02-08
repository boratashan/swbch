package com.example.demo.utils;

public class HierarchicalList<T> {
    private HNode<T> root = new HNode<T>();

    public HNode<T> getRoot() {
        return root;
    }

    public HNode<T> setRootValue(T data) {
        return root.setData(data);
    }
}
