package com.example.demo.model;

public class NavItem {
    private NavItemType itemType;

    private String caption;

    public NavItem(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public NavItem setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public NavItemType getItemType() {
        return itemType;
    }

    public NavItem setItemType(NavItemType itemType) {
        this.itemType = itemType;
        return this;
    }
}
