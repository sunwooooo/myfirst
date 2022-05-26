package kr.co.EZHOME.controller;

public class test {

	public static void main(String[] args) {
		String str="(12259) 경기 남양주시 가운로 1, sdfsdf";
		String a="";
		String b="";
		String c="";
		int count2=0;
		int count1=0;
		int count=0;
		String[] arr=new String[str.length()];
		
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i) == ')') { count=i;}
			if(str.charAt(i) == ',') { count1=i;}}
		
		for(int i=0;i<=count;i++) {
			a=a+str.charAt(i);
		}
		for(int i=count+2;i<count1;i++) {
			b=b+str.charAt(i);
		}
		for(int i=count1+2;i<str.length();i++) {
			c=c+str.charAt(i);
		}
				
	
		
	System.out.println(a);
	System.out.println(b);
	System.out.println(c);
	}

	
}
