package com.hyf.bli;

import java.util.HashMap;
import java.util.Map;

/**
 * 从标准输入读取字符串，按照指定的两层分隔符切分成多对key-value,依次输出到标准输出中。
 * 注意:仅输出key和value都为非空串的pair。
 * <p>
 * 输入描述:每一行有效输入为三列,列之间以' '分隔,第一列表示key_value_pairs_delimiter,
 * 第二列表示key_value_delimiter,第三列表示待切分的字符串。分隔符' '不会出现在列内容中。
 * 输出描述:先输出有效key-value对的个数(单独一行);再依次输出key-value对，每一对单独成行，以' '分隔key和value。
 *
 * @author baB_hyf
 * @date 2022/03/27
 */
public class StringSplit {

    public static void main(String[] args) {


        // pairDelimiter kvDelimiter string
        String s = "# : a:3:#b:8#c:9";

        String[] ops = s.split(" ");
        String pairDelimiter = ops[0];
        String kvDelimiter = ops[1];
        s = ops[2];


        Map<String, String> result = new HashMap<>();
        String[] pairs = s.split(pairDelimiter);
        for (String pair : pairs) {
            String[] kvs = pair.split(kvDelimiter);
            if ("".equals(kvs[0].trim()) || "".equals(kvs[1].trim())) {
                continue;
            }
            result.put(kvs[0], kvs[1]);
        }


        System.out.println(result.size());
        result.forEach((k, v) -> System.out.println(k + " " + v));

    }
}
