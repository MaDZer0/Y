package com.njcb;

import org.apache.commons.lang.StringEscapeUtils;

public class json {
    public static void main(String[] args){
        String s1="{\"MsgId\":1,\"TotalCount\":10,\"FilterCount\":8,\"SentCount\":7,\"ErrorCount\":1}";
        System.out.println(StringEscapeUtils.unescapeJava(s1));
        System.out.println("\u9000\u56DE");


    }
}
