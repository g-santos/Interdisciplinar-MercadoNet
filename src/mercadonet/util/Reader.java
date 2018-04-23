package mercadonet.util;

import java.util.Scanner;

public class Reader {

	private static Scanner scanner = new Scanner(System.in);

	public static final String readString() {
		String text = scanner.nextLine();
		return text;
	}

	public static final int readInt() {
		
		int ii = 0;
		boolean validou = false;

		do {
			String i = scanner.nextLine();

			try {
				ii = Integer.parseInt(i);
				validou = true;
			} catch (NumberFormatException e) {
				System.out.println("Não foi possível transformar: " + i + " em Inteiro, digite um numero valido");
				ii = Reader.readInt();
			}

		} while (!validou);

		return ii;
	}

	public static final double readDouble() throws Exception {
		String db = scanner.nextLine();
		double dd = 0;
		try {
			dd = Double.parseDouble(db);
		} catch (Exception e) {
			System.out.println("Não foi possível transformar: " + db + " em Double");
			throw e;
		}
		return dd;
	}

	public static final boolean readBoolean() throws Exception {
		String b = scanner.nextLine();
		boolean bb = false;
		try {
			bb = Boolean.parseBoolean(b);
		} catch (Exception e) {
			System.out.println("Não foi possível transformar: " + b + " em Boolean");
			throw e;
		}
		return bb;
	}
}
