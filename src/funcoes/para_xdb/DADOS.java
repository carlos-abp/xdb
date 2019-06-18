package funcoes.para_xdb;

public class DADOS {
//++ implementar para double
	public static boolean verificarCARACTERouINTEGER(String tipo, String caracter) {
		String numeros = "0123456789.";
		String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

		if ("num".equals(tipo)) {
			boolean temp = true;
			for (int j = 0; j < caracter.length(); j++) {
				if (!numeros.contains("" + caracter.charAt(j))) {
					temp = false;
				}
			}
//+++
			return temp;
		} else if ("car".equals(tipo)) {
			return caracteres.contains(caracter);
		}
		return false;
	}
}
