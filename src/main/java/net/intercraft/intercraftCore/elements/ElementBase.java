package net.intercraft.intercraftCore.elements;

public class ElementBase {
    /*
    * Should register an ore (hard), dust (+ tiny), ingot (+ nugget).
    * Is going to be a extension of this class to make code shorter.
    * */
    private String name,title;
    private int tint;

    public ElementBase(String name, String title, int tint) {
        this.name = name;
        this.title = title;
        this.tint = tint;

    }
}