package com.rialvik.autiyoda;

public class ListViewElements {

    private int item_image;
    private String item_name;
    private String item_total;

    public ListViewElements(int item_image, String item_name, String item_total) {
        this.item_image = item_image;
        this.item_name = item_name;
        this.item_total = item_total;
    }

    public int getItem_image() {
        return item_image;
    }

    public void setItem_image(int item_image) {
        this.item_image = item_image;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_total() {
        return item_total;
    }

    public void setItem_total(String item_total) {
        this.item_total = item_total;
    }
}
