package com.liu.test;

public class Mon1 implements Monkey {
    @Override
    public String colorShow(String colorName) {
        return colorName+"猴儿";
    }

    @Override
    public void show() {
        System.out.println("介猴儿卖吗?");
    }
}
