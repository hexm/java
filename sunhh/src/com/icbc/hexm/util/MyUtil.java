package com.icbc.hexm.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MyUtil {

	/**
	 * �����execption��trackת��Ϊstrng���ء�
	 */
	public static String Execption2String(Throwable... execptions) {
		return Execption2Strings(false, execptions);
	}

	public static String Execption2Strings(boolean rep, Throwable... execptions) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		for (Throwable e : execptions) {
			e.printStackTrace(pw);
		}
		if (rep) {
			return sw.toString().replaceAll("\r\n|\n\r|\r|\n", "<br/>\n");
		}
		return sw.toString();
	}
}
