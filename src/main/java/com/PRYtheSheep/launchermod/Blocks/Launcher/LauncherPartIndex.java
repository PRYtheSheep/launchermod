package com.PRYtheSheep.launchermod.Blocks.Launcher;

import net.minecraft.util.StringRepresentable;

import java.util.ArrayList;
import java.util.Arrays;

public enum LauncherPartIndex implements StringRepresentable {
    P1("p1", new ArrayList<int[]>(
            Arrays.asList(
                    //{x, z}
                    new int[] {1,0},
                    new int[] {0,1}
            )
    )),
    P2("p2", new ArrayList<int[]>(
            Arrays.asList(
                    //{x, z}
                    new int[] {1,0},
                    new int[] {-1,0},
                    new int[] {0,1}
            )
    )),
    P3("p3", new ArrayList<int[]>(
            Arrays.asList(
                    //{x, z}
                    new int[] {-1,0},
                    new int[] {0,1}
            )
    )),
    P4("p4", new ArrayList<int[]>(
            Arrays.asList(
                    //{x, z}
                    new int[] {1,0},
                    new int[] {0,1},
                    new int[] {0,-1}
            )
    )),
    P5("p5", new ArrayList<int[]>(
            Arrays.asList(
                    //{x, z}
                    new int[] {1,0},
                    new int[] {-1,0},
                    new int[] {0,1},
                    new int[] {0,-1}
            )
    )),
    P6("p6", new ArrayList<int[]>(
            Arrays.asList(
                    //{x, z}
                    new int[] {-1,0},
                    new int[] {0,1},
                    new int[] {0,-1}
            )
    )),
    P7("p7", new ArrayList<int[]>(
            Arrays.asList(
                    //{x, z}
                    new int[] {1,0},
                    new int[] {0,1},
                    new int[] {0,-1}
            )
    )),
    P8("p8", new ArrayList<int[]>(
            Arrays.asList(
                    //{x, z}
                    new int[] {1,0},
                    new int[] {-1,0},
                    new int[] {0,1},
                    new int[] {0,-1}
            )
    )),
    P9("p9", new ArrayList<int[]>(
            Arrays.asList(
                    //{x, z}
                    new int[] {-1,0},
                    new int[] {0,1},
                    new int[] {0,-1}
            )
    )),
    P10("p10", new ArrayList<int[]>(
            Arrays.asList(
                    //{x, z}
                    new int[] {1,0},
                    new int[] {0,1},
                    new int[] {0,-1}
            )
    )),
    P11("p11", new ArrayList<int[]>(
            Arrays.asList(
                    //{x, z}
                    new int[] {1,0},
                    new int[] {-1,0},
                    new int[] {0,1},
                    new int[] {0,-1}
            )
    )),
    P12("p12", new ArrayList<int[]>(
            Arrays.asList(
                    //{x, z}
                    new int[] {-1,0},
                    new int[] {0,1},
                    new int[] {0,-1}
            )
    )),
    P13("p13", new ArrayList<int[]>(
            Arrays.asList(
                    //{x, z}
                    new int[] {1,0},
                    new int[] {0,-1}
            )
    )),
    P14("p14", new ArrayList<int[]>(
            Arrays.asList(
                    //{x, z}
                    new int[] {1,0},
                    new int[] {-1,0},
                    new int[] {0,-1}
            )
    )),
    P15("p15", new ArrayList<int[]>(
            Arrays.asList(
                    //{x, z}
                    new int[] {-1,0},
                    new int[] {0,-1}
            )
    ));

    public final String name;
    public final ArrayList<int[]> number;
    public static final int[] ROTATIONMATRIX = new int[] {0,1,-1,0};

    LauncherPartIndex(String s, ArrayList<int[]> a) {
        this.name = s;
        this.number = a;
    }

    public String toString() {
        return this.getSerializedName();
    }

    public String getSerializedName() {
        return this.name;
    }

    public static int[] multiply(int[] a, int[][] b){
        //Multiplies a 1x2 matrix with a 2x2 matrix to produce a 1x2 matrix
        int[] returnMatrix = new int[2];
        returnMatrix[0] = a[0] * b[0][0] + a[1] * b[1][0];
        returnMatrix[1] = a[0] * b[0][1] + a[1] * b[1][1];
        return returnMatrix;
    }
}
