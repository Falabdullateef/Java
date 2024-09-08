package com.faisal.hackerrank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner fetch = new Scanner(System.in);
        int i = 1;
        while(fetch.hasNextLine()){
            String st = fetch.nextLine();
            System.out.println(i+" "+st);
            i++;
        }
    }
}