package com.hqq.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class changetype {
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // ����script��������ʽ
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // ����style��������ʽ
	private static final String regEx_html = "<[^>]+>"; // ����HTML��ǩ��������ʽ
	private static final String regEx_img = "<img\\s*([^>]*)\\s*src=\\\"(.*?)\\\"\\s*([^>]*)>";// ����image��ǩ��������ʽ
	private static final String regEx_emoji = "[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\ud83e\\udd00-\\ud83e\\udfff]|[\\u2600-\\u27ff]";// ��������ǩ��������ʽ
	private static final String regEx_space = "\\s*|\t|\r|\n";//����ո�س����з�
	private static final String regEx_special = "\\&[a-zA-Z]{1,10};";//���������ַ�

	public static String delHTMLTag(String htmlStr) {
	 
	 // ����script��ǩ
	Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
	Matcher m_script = p_script.matcher(htmlStr);
	htmlStr = m_script.replaceAll("");
	// ����style��ǩ
	Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
	Matcher m_style = p_style.matcher(htmlStr);
	htmlStr = m_style.replaceAll("");
	 
	// ����image��ǩ
	Pattern p_img = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
	Matcher m_img = p_img.matcher(htmlStr);
	htmlStr = m_img.replaceAll("");
	 
	// ����emoji��ǩ
	Pattern p_emoji = Pattern.compile(regEx_emoji, Pattern.CASE_INSENSITIVE);
	Matcher m_emoji = p_emoji.matcher(htmlStr);
	htmlStr = m_emoji.replaceAll("");
	
	// ����html��ǩ
	Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
	Matcher m_html = p_html.matcher(htmlStr);
	htmlStr = m_html.replaceAll("");
	
	// ���˿ո�س���ǩ
	Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
	Matcher m_space = p_space.matcher(htmlStr);
	htmlStr = m_space.replaceAll("");
	
	// ���������ַ�
	Pattern p_special = Pattern.compile(regEx_special, Pattern.CASE_INSENSITIVE);
	Matcher m_special = p_special.matcher(htmlStr);
	htmlStr = m_special.replaceAll("");
	
	return htmlStr.trim(); // �����ı��ַ���
	}
}
