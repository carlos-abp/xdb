package funcoes.para_xdb;

import funcoes.para_arquivos.ManipularArquivo;
import funcoes.para_arquivos.ManipularConteudo;
import funcoes.para_arquivos.VerificarOuAtualizar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

/**
 * @author Carlos Alexandre Classe responsavel pela manipulacao do cabecalho,
 *         alterando funcionalidade deste.
 */
public class ConfiguracaoDoXDB {

	/*
	 * campos responsavel pela estruturacao e funcionalidades da classe
	 */
	private final Character separarCampo = ',';
	private final Character isolarQuantidade = '/';
	private File caminhoCompleto;

	public ConfiguracaoDoXDB(File arqComPastaXDB, String nomeDoXDB) {
		this.caminhoCompleto = new File(arqComPastaXDB + "/" + nomeDoXDB);
	}

//------------------------------------------------------------------------------------------------
	private static boolean verificarCARACTERouINTEGER(String tipo, String caracter) {
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

	/**
	 * Funcao Para Editar o cabecalho
	 *
	 * @param CabecalhoEscrever Este parametro de obdecer ex:(^ = ,) List[nome,id ^
	 *                          (String/*,Integer) ^ [code=pt]]
	 * @return Se foi ou nao escrito.
	 */
	public Boolean escreverConfig(List<String> configAEscrever) {

		File caminhoDoConfig = new File(caminhoCompleto.toString() + "/.config");

		if (VerificarOuAtualizar.arquivoExiste(caminhoDoConfig.toString())) {

			if (verificarLinhas(configAEscrever) != null) {
				for (String a : configAEscrever) {
					if (configAEscrever.get(0).equals(a)) {
						ManipularConteudo.escreverNoArquivo(a, caminhoDoConfig, Boolean.FALSE);
					} else {
						ManipularConteudo.escreverNoArquivo(a, caminhoDoConfig, Boolean.TRUE);
					}
				}

			}
		}
		return null;
	}

	/**
	 *
	 * @param cabecalhoVerificar Recebe o cabechalho e verifica se esta bem
	 *                           estruturado tanto com pontuacao quanto estrutura
	 *                           ex:(^ = ,) List[nome,id ^ (String/*,Integer) ^
	 *                           [code=pt]].
	 * @return Se o cabechalho cumpre os requesitos
	 */
	public List<String> verificarLinhas(List<String> tiposAVerificar) {

		String[] listaDeNomes = tiposAVerificar.get(0).split(",");
		String[] listaDeVariavel = tiposAVerificar.get(1).split(",");

		// verifica se as variaveis e os nomes conferem no quesito de quantidade
		if (listaDeNomes.length == listaDeVariavel.length) {

			List<String> temp1verNaLinhaUm = AtributosDasVariaveis(tiposAVerificar.get(1));

			if (temp1verNaLinhaUm != null) {

				String temp2 = "";
				// intera o temp1verNaLinhaUm para ver se esta em ordem que o sistema possa
				// entender
				// e depois com o retorno da lista com index 1 formatado
				for (String a : temp1verNaLinhaUm) {
					temp2 += a;
				}

				tiposAVerificar.remove(1);
				tiposAVerificar.add(1, temp2);

				// verifica as configuracoes do banco de dados apartir da linha 3 do arquivo
				// .config
				for (int b = 2; b < tiposAVerificar.size(); b++) {
					if (!tiposAVerificar.get(b).contains("=")) {
						System.out.println("Erro 12560 nao foi possivel atribuir o valor de " + tiposAVerificar.get(b));
						return null;
					}
				}
				return tiposAVerificar;
			} else {
				return null;
			}
		} else {
			System.out.println("Erro 12515 Numeros inconpativel de variavel e tipos");

		}
		return null;
	}

	private List<String> AtributosDasVariaveis(String trecho) {

//	        String/?:int:^
//	        Integer/?:int
//	        Double/?:int.int
//	        Boolean/?
//	        Character/?:^

		List<String> cabecalhoCompleto = new ArrayList<>();

		String[] trechoRecortado = trecho.split(",");
		String erro = "";
		for (String a : trechoRecortado) {

			String[] verificar = a.split("/");

			switch (verificar[0].toUpperCase()) {

			case "STRING":
				try {
					boolean argumentoVazio = verificar[1].contains("?");
					boolean letraDeForma = verificar[1].contains("^");
					String numVerificado = "";
					String formatado = "STRING/";

					if (verificar[1].split(":").length <= 3) {

						if (argumentoVazio && letraDeForma) 
						{
							String[] temporario = verificar[1].split(":");

							for (String b : temporario) {

								if (!"?".equals(b.trim()) && !"^".equals(b.trim())) {
									if (verificarCARACTERouINTEGER("num", b)) {
										numVerificado = b;
									} else {
										System.out.println("Erro 12563 Argumentos de STRING.. <?:num:^>");
										return null;
									}
								}

							}
							if (!numVerificado.isEmpty()) 
								formatado = ("STRING/" + numVerificado + ":?:^");
							else
								formatado = ("STRING/?:^");
						}
						

						

						} else if (argumentoVazio && !numVerificado.isEmpty()) {
							formatado = ("STRING/" + numVerificado + ":?");
						} else if (letraDeForma && !numVerificado.isEmpty()) {
							formatado = ("STRING/" + numVerificado + ":^");

						} else if (argumentoVazio) {
							formatado = ("STRING/?");
						} else if (!numVerificado.isEmpty()) {
							formatado = ("STRING/" + numVerificado);
						} else {
							formatado = ("STRING/^");
						}

					

					if (0 != cabecalhoCompleto.size()) {
						if (verificar[1].split(":").length > 3) {
							System.out.println("Erro 12562 Argumentos a mais do indicado");
							return null;
						}
						cabecalhoCompleto.add("," + formatado);
					} else {
						if (verificar[1].split(":").length > 3) {
							System.out.println("Erro 12562 Argumentos a mais do indicado");
							return null;
						}
						cabecalhoCompleto.add(formatado);

					}

				} catch (ArrayIndexOutOfBoundsException e) {
					if (0 != cabecalhoCompleto.size()) {
						cabecalhoCompleto.add(",STRING/");
					} else {
						cabecalhoCompleto.add("STRING/");
					}
				}

				break;

			case "INTEGER":

				try {
					boolean argumentoVazio = verificar[1].contains("?");
					String numVerificado = "";
					String formatado = "INTEGER/";

					if (verificar[1].split(":").length <= 2) {

						String[] temporario = verificar[1].split(":");

						for (String b : temporario) {

							if (!"?".equals(b.trim())) {
								if (verificarCARACTERouINTEGER("num", b)) {
									numVerificado = b;

								} else {
									System.out.println("Erro 12562 Argumentos de INTEGER.. <?:num>");
									return null;
								}
							}

						}

						if (argumentoVazio && !numVerificado.isEmpty()) {
							formatado = ("INTEGER/" + numVerificado + ":?");
						} else if (argumentoVazio && !numVerificado.isEmpty()) {
							formatado = ("INTEGER/?:^");
						} else if (argumentoVazio) {
							formatado = ("INTEGER/?");
						} else if (!numVerificado.isEmpty()) {
							formatado = ("INTEGER/" + numVerificado);
						}

					}

					if (0 != cabecalhoCompleto.size()) {
						cabecalhoCompleto.add("," + formatado);
					} else {
						if (verificar[1].split(":").length > 2) {
							System.out.println("Erro 12562 Argumentos de DOUBLE.. <?:num.num>");
							return null;
						}
						cabecalhoCompleto.add(formatado);

					}

				} catch (ArrayIndexOutOfBoundsException e) {
					if (0 != cabecalhoCompleto.size()) {
						cabecalhoCompleto.add(",INTEGER/");
					} else {
						cabecalhoCompleto.add("INTEGER/");
					}
				}

				break;
			case "DOUBLE":
				try {
					boolean argumentoVazio = verificar[1].contains("?");

					String numVerificado = "";
					String formatado = "DOUBLE/";

					if (verificar[1].split(":").length <= 2) {

						String[] temporario = verificar[1].split(":");

						for (String b : temporario) {

							if (!"?".equals(b.trim())) {
								if (verificarCARACTERouINTEGER("num", b)) {
									numVerificado = b;
								} else {
									System.out.println("Erro 12562 Argumentos de DOUBLE.. <?:num.num>");
									return null;
								}
							}

						}

						if (argumentoVazio && !numVerificado.isEmpty()) {
							formatado = ("DOUBLE/" + numVerificado + ":?");
						} else if (argumentoVazio) {
							formatado = ("DOUBLE/?");
						} else if (!numVerificado.isEmpty()) {
							formatado = ("DOUBLE/" + numVerificado);
						}

					}

					if (0 != cabecalhoCompleto.size()) {
						cabecalhoCompleto.add("," + formatado);
					} else {
						if (verificar[1].split(":").length > 3) {
							System.out.println("Erro 12562 Argumentos de DOUBLE.. <?:num.num>");
							return null;
						}
						cabecalhoCompleto.add(formatado);

					}

				} catch (ArrayIndexOutOfBoundsException e) {
					if (0 != cabecalhoCompleto.size()) {
						cabecalhoCompleto.add(",DOUBLE/");
					} else {
						cabecalhoCompleto.add("DOUBLE/");
					}
				}

				break;
			case "BOOLEAN":
				try {
					boolean argumentoVazio = verificar[1].contains("?");
					boolean separadorDeArgumentos = verificar[1].contains("^");
					String formatado = "BOOLEAN/";

					if (separadorDeArgumentos) {
						String[] temp = verificar[1].split(":");

						if (temp.length == 2 && argumentoVazio) {
							formatado = ("BOOLEAN/?:^");
						} else {
							System.out.println("Erro 12562 Argumentos de BOOLEAN.. <?:^>");
							return null;
						}

					}
					if (argumentoVazio && !separadorDeArgumentos) {
						formatado = ("BOOLEAN/?");
					}

					if (0 != cabecalhoCompleto.size()) {
						cabecalhoCompleto.add("," + formatado);
					} else {
						if (verificar[1].split(":").length > 3) {
							System.out.println("Erro 12562 Argumentos de DOUBLE.. <?:num.num>");
							return null;
						}
						cabecalhoCompleto.add(formatado);

					}

				} catch (ArrayIndexOutOfBoundsException e) {
					if (0 != cabecalhoCompleto.size()) {
						cabecalhoCompleto.add(",BOOLEAN/");
					} else {
						cabecalhoCompleto.add("BOOLEAN/");
					}
				}

				break;
			case "CHAR":
				try {
					boolean argumentoVazio = verificar[1].contains("?");
					boolean letraDeForma = verificar[1].contains("^");
					String formatado = "CHAR/";

					if (verificar[1].split(":").length <= 2) {

						if (argumentoVazio && letraDeForma) {
							formatado = ("CHAR/?:^");
						} else if (argumentoVazio) {
							formatado = ("CHAR/?");
						} else if (letraDeForma) {
							formatado = ("CHAR/^");

						}

					}

					if (0 != cabecalhoCompleto.size()) {
						cabecalhoCompleto.add("," + formatado);
					} else {
						if (verificar[1].split(":").length > 3) {
							System.out.println("Erro 12562 Argumentos de DOUBLE.. <?:num.num>");
							return null;
						}
						cabecalhoCompleto.add(formatado);

					}

				} catch (ArrayIndexOutOfBoundsException e) {
					if (0 != cabecalhoCompleto.size()) {
						cabecalhoCompleto.add(",CHAR/");
					} else {
						cabecalhoCompleto.add("CHAR/");
					}
				}

				break;
			default:
				erro += "'" + verificar[0];

			}

		}
		if (!erro.isEmpty()) {
			System.out.println("Erro 12561 : tipo de dado nao compativel " + erro);
			return null;
		} else
			return cabecalhoCompleto;

	}

}