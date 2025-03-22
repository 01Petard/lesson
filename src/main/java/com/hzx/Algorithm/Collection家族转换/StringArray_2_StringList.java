package com.hzx.Algorithm.Collection家族转换;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringArray_2_StringList {
    public static void main(String[] args) {
        String[] strings = {"A", "B", "C", "D"};

        // 用 Stream API 将数组转换为List
        List<String> list = Arrays.stream(strings).collect(Collectors.toList());
    }
}
