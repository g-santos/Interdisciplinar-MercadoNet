package mercadonet.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MyReader extends Reader {

	private static Scanner scanner = new Scanner(System.in);
	
	public static Date converterStringToDate(String source) throws Exception {

		// Variables declaration

		Date data = null;

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		// Data processing

		try {

			data = dateFormat.parse(source);

		} catch (Exception e) {
			// TODO: handle exception

			throw e;
		}

		// Information output

		return data;
	}

	public static Date readDate() throws Exception {

		// Variables declaration

		Date value = null;

		String source = null;

		// Data input

		try {

			scanner = new Scanner(System.in);

			source = scanner.nextLine();

			value = converterStringToDate(source);

		} catch (Exception e) {
			// TODO: handle exception

			throw e;
		}

		// Information output

		return value;

	}
}
