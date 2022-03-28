package com.dongnaoedu.dnplayer.common.data.entity;

import java.util.ArrayList;

public class ListData<T> {

    private ArrayList<T> records;
    private int total;
    private int size;
    private int current;
    private int pages;

    public ArrayList<T> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<T> records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
