package com.PRYtheSheep.launchermod.ModBlock;

import net.minecraft.util.StringRepresentable;

public enum LauncherPartIndex implements StringRepresentable {
    P1("p1"),
    P2("p2"),
    P3("p3"),
    P4("p4"),
    P5("p5"),
    P6("p6"),
    P7("p7"),
    P8("p8"),
    P9("p9"),
    P10("p10"),
    P11("p11"),
    P12("p12"),
    P13("p13"),
    P14("p14"),
    P15("p15"),
    ROTATIONMATRIX("");

    public final String name;

    LauncherPartIndex(String s) {
        this.name = s;
    }

    public String toString() {
        return this.getSerializedName();
    }

    public String getSerializedName() {

        //Yes this is stupid
        switch(this){
            case P1 -> {
                return "p1";
            }
            case P2 -> {
                return "p2";
            }
            case P3 -> {
                return "p3";
            }
            case P4 -> {
                return "p4";
            }
            case P5 -> {
                return "p5";
            }
            case P6 -> {
                return "p6";
            }
            case P7 -> {
                return "p7";
            }
            case P8 -> {
                return "p8";
            }
            case P9 -> {
                return "p9";
            }
            case P10 -> {
                return "p10";
            }
            case P11 -> {
                return "p11";
            }
            case P12 -> {
                return "p12";
            }
            case P13 -> {
                return "p13";
            }
            case P14 -> {
                return "p14";
            }
            case P15 -> {
                return "p15";
            }
        }
        return null;
    }
}
