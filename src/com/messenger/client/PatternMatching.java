package com.messenger.client;

import java.util.regex.Pattern;

public class PatternMatching {
	
	public void pt1(String string) {
		String regExp4 = "^[��-�R]{2,4}|[a-zA-Z]{2,10}\\s[a-zA-Z]{2,10}$";
	      string = "ȫ�浿";
	      boolean result4 = Pattern.matches(regExp4, string);
	      if(result4) {
	         System.out.println("�̸� ����ǥ���İ� ��ġ");
	         }else {
	            System.out.println("�̸� ����ǥ���İ� ����ġ");
	         }
	}

}
