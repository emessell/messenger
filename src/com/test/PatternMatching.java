package com.test;

import java.util.regex.Pattern;

public class PatternMatching {

   public static void main(String[] args) {
      
      // ÀüÈ­¹øÈ£ ÆĞÅÏ °ËÁõ
      String regExp = "(02|031|032|033|041|042|043|044|"
      		+ "051|052|053|054|055|061|062|063|064|"
      		+ "010|011|016|017|018|019)"
      		+ "\\d{3,4}"
      		+ "\\d{4}";
      String data = "0161234567";
      boolean result = Pattern.matches(regExp, data);
      if(result) {
         System.out.println("ÀüÈ­¹øÈ£ Á¤±ÔÇ¥Çö½Ä°ú ÀÏÄ¡");
         }else {
            System.out.println("ÀüÈ­¹øÈ£ Á¤±ÔÇ¥Çö½Ä°ú ºÒÀÏÄ¡");
         }
      
      // ÀÌ¸ŞÀÏ ÆĞÅÏ °ËÁõ
      String regExp2 = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
      String data2 = "emessell22@gmail.com";
      boolean result2 = Pattern.matches(regExp2, data2);
      if(result2) {
         System.out.println("ÀÌ¸ŞÀÏ Á¤±ÔÇ¥Çö½Ä°ú ÀÏÄ¡");
         }else {
            System.out.println("ÀÌ¸ŞÀÏ Á¤±ÔÇ¥Çö½Ä°ú ºÒÀÏÄ¡");
         }
      
      // ºñ¹Ğ¹øÈ£ ÆĞÅÏ °ËÁõ
      String regExp3 = "^(?=.*?[a-z])(?=.*?[0-9]).{6,16}$";
      String data3 = "aaa333";
      boolean result3 = Pattern.matches(regExp3, data3);
      if(result3) {
         System.out.println("ºñ¹Ğ¹øÈ£ Á¤±ÔÇ¥Çö½Ä°ú ÀÏÄ¡");
         }else {
            System.out.println("ºñ¹Ğ¹øÈ£ Á¤±ÔÇ¥Çö½Ä°ú ºÒÀÏÄ¡");
         }
      
      // ÀÌ¸§ ÆĞÅÏ °ËÁõ
      String regExp4 = "^[°¡-ÆR]{2,4}|[a-zA-Z]{2,10}\\s[a-zA-Z]{2,10}$";
      String data4 = "È«±æµ¿";
      boolean result4 = Pattern.matches(regExp4, data4);
      if(result4) {
         System.out.println("ÀÌ¸§ Á¤±ÔÇ¥Çö½Ä°ú ÀÏÄ¡");
         }else {
            System.out.println("ÀÌ¸§ Á¤±ÔÇ¥Çö½Ä°ú ºÒÀÏÄ¡");
         }
      
      // ³ªÀÌ ÆĞÅÏ °ËÁõ
      String regExp5 = "[1]?[1-9]?[1-9]";
      String data5 = "54";
      boolean result5 = Pattern.matches(regExp5, data5);
      if(result5) {
         System.out.println("³ªÀÌ Á¤±ÔÇ¥Çö½Ä°ú ÀÏÄ¡");
         }else {
            System.out.println("³ªÀÌ Á¤±ÔÇ¥Çö½Ä°ú ºÒÀÏÄ¡");
         }
      
      // »ı³â¿ùÀÏ ÆĞÅÏ °ËÁõ
      String regExp6 = "\\d{6}";
      String data6 = "945647";
      boolean result6 = Pattern.matches(regExp6, data6);
      if(result6) {
         System.out.println("»ı³â¿ùÀÏ Á¤±ÔÇ¥Çö½Ä°ú ÀÏÄ¡");
         }else {
            System.out.println("»ı³â¿ùÀÏ Á¤±ÔÇ¥Çö½Ä°ú ºÒÀÏÄ¡");
         }
      
   }

}