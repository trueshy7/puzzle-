package com.trueshy7.ui;

import java.util.Random;

public class CodeUtil {
    public static String getCodeText() {
        StringBuilder res = new StringBuilder();
        char[] array = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        //1.随机获得四个英文字母
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(array.length);
            res.append(array[index]);
        }
        //2.随机获得1个数字
        int index = random.nextInt(numbers.length);
        res.append(numbers[index]);
        //5个char随机打乱顺序
        for (int i = 0; i < res.length(); i++) {
            index = random.nextInt(res.length());
            char temp = res.charAt(index);
            res.setCharAt(index, res.charAt(i));
            res.setCharAt(i, temp);
        }
        return res.toString();
    }
}
