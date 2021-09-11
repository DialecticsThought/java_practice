package com.atguigu.exer;

public class Test {

    /*
    将一个字符串进行反转。将字符串中指定部分进行反转。比如“abcdefg”反转为”abfedcg”

    方式一：转换为char[]
     */
    public static void main(String[] args) {
        String str="abfedcg";

        String reverseStr = reverse(str, 2, 5);

        System.out.print(reverseStr);
    }

    public static String reverse(String str, int startIndex, int endIndex) {
        if (str != null && str.length() != 0) {
            char[] chars = str.toCharArray();

            for (int x = startIndex, y = endIndex; x < y; x++, y--) {

                char temp = chars[x];

                chars[x] = chars[y];

                chars[y] = temp;

            }
            return new String(chars);

        } else {
            return null;
        }
    }

    public static String reverse1(String str, int startIndex, int endIndex){

        StringBuilder builder=new StringBuilder(str.length());

        builder.append(str.substring(0,startIndex));

        for(int x=endIndex;x>=startIndex;x++){

            builder.append(str.charAt(x));

        }
        builder.append(str.substring(endIndex+1));

        return builder.toString();
    }

     /*
    获取一个字符串在另一个字符串中出现的次数。
      比如：获取“ab”在 “abkkcadkabkebfkaabkskab” 中出现的次数

     */

    public int getCount(String mainStr,String subStr){
        int length = mainStr.length();
        int length1 = subStr.length();
        int index;
        int count=0;
        if(length<length1){
            //index =mainStr.indexOf(subStr)  index!=-1
            while((index = mainStr.indexOf(subStr)) != -1){
                count++;
                mainStr=mainStr.substring(index+subStr.length());
            }
            return count;
        }else {
            return 0;
        }

    }
}
