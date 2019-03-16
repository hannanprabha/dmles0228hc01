package gov.dha.jmlfdc.logicole.ivv.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EnvironmentCleanUp {

	public static void envCleanup() {

		clearSession();
		Process p = null;
		try {
			p = Runtime.getRuntime().exec("tasklist");
		} catch (IOException e) {
			e.printStackTrace();
		}
		InputStreamReader ISR = new InputStreamReader(p.getInputStream());
		BufferedReader reader = new BufferedReader(ISR);
		String process = "chrome.exe";
		String DType = "chrome";

		String line;
		// System.out.println("Cleanup environment.\nCurrent running process.");

		try {
			while ((line = reader.readLine()) != null) {

				if (line.contains(process)) {
					// System.out.println(DType + " driver was found running.");
					Runtime.getRuntime().exec("taskkill /F /IM " + process);
					// System.out.println(DType + " process is closed.");

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void clearSession() {
		// System.out.println("Clearing cache");
		String FILE_NAME = "C:\\workspace\\dmles-ivv\\cleanupscripts\\chromeclr.bat";
		String path = FILE_NAME;
		try {
			Process p = Runtime.getRuntime().exec("cmd /c start " + path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
