package com.atguigu.exer;
/**
 * @author shkstart
 * @create 2019 上午 10:26
 */
public class StringDemo1 {
    /*
    获取一个字符串在另一个字符串中出现的次数。
      比如：获取“ab”在 “abkkcadkabkebfkaabkskab” 中出现的次数

     */

    /**
     * 获取subStr在mainStr中出现的次数
     * @param mainStr
     * @param subStr
     * @return
     */
    public int getCount(String mainStr,String subStr){
        int mainLength = mainStr.length();
        int subLength = subStr.length();
        int count = 0;
        int index = 0;
        //如果字符串的总长度大于字串的长度的话
        if(mainLength >= subLength){
            //方式一： 如果字串所在字符串的索引位置不等于-1的话 也就是不在开头的话
//            while((index = mainStr.indexOf(subStr)) != -1){
//                count++;
                    //字符串的起始位置 是当前的索引 加上 子串的长度 并且重新赋值
//                mainStr = mainStr.substring(index + subStr.length());
//            }
            //方式二：对方式一的改进 返回指定子字符串 再此字符串第一次出现的索引 从指定索引开始
            while((index = mainStr.indexOf(subStr,index)) != -1){//字符串的索引每次循环都会递增
                count++;
                index += subLength;//每次找到以后 索引加上字串的长度 从新索隐开始向后找
            }

            return count;
        }else{
            return 0;
        }
    }

    //@Test
    public void testGetCount(){
        String mainStr = "abkkcadkabkebfkaabkskab";
        String subStr = "ab";
        int count = getCount(mainStr, subStr);
        System.out.println(count);
    }
}
