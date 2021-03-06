package com.test;

import java.util.regex.Pattern;

public class PatternMatching {

   public static void main(String[] args) {
      
      // 전화번호 패턴 검증
      String regExp = "(02|031|032|033|041|042|043|044|"
      		+ "051|052|053|054|055|061|062|063|064|"
      		+ "010|011|016|017|018|019)"
      		+ "\\d{3,4}"
      		+ "\\d{4}";
      String data = "0161234567";
      boolean result = Pattern.matches(regExp, data);
      if(result) {
         System.out.println("전화번호 정규표현식과 일치");
         }else {
            System.out.println("전화번호 정규표현식과 불일치");
         }
      
      // 이메일 패턴 검증
      String regExp2 = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
      String data2 = "emessell22@gmail.com";
      boolean result2 = Pattern.matches(regExp2, data2);
      if(result2) {
         System.out.println("이메일 정규표현식과 일치");
         }else {
            System.out.println("이메일 정규표현식과 불일치");
         }
      
      // 비밀번호 패턴 검증
      String regExp3 = "^(?=.*?[a-z])(?=.*?[0-9]).{6,16}$";
      String data3 = "aaa333";
      boolean result3 = Pattern.matches(regExp3, data3);
      if(result3) {
         System.out.println("비밀번호 정규표현식과 일치");
         }else {
            System.out.println("비밀번호 정규표현식과 불일치");
         }
      
      // 이름 패턴 검증
      String regExp4 = "^[가-힣]{2,4}|[a-zA-Z]{2,10}\\s[a-zA-Z]{2,10}$";
      String data4 = "홍길동";
      boolean result4 = Pattern.matches(regExp4, data4);
      if(result4) {
         System.out.println("이름 정규표현식과 일치");
         }else {
            System.out.println("이름 정규표현식과 불일치");
         }
      
      // 나이 패턴 검증
      String regExp5 = "[1]?[1-9]?[1-9]";
      String data5 = "54";
      boolean result5 = Pattern.matches(regExp5, data5);
      if(result5) {
         System.out.println("나이 정규표현식과 일치");
         }else {
            System.out.println("나이 정규표현식과 불일치");
         }
      
      // 생년월일 패턴 검증
      String regExp6 = "\\d{6}";
      String data6 = "945647";
      boolean result6 = Pattern.matches(regExp6, data6);
      if(result6) {
         System.out.println("생년월일 정규표현식과 일치");
         }else {
            System.out.println("생년월일 정규표현식과 불일치");
         }
      
   }

}