package funcoes.para_xdb;

import funcoes.para_arquivos.ManipularArquivo;
import funcoes.para_arquivos.ManipularConteudo;
import funcoes.para_arquivos.VerificarOuAtualizar;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
		List<String> Formatado = new ArrayList<String>();

		for (String a : trechoRecortado) {

			String[] argsVerificar = a.split("/");

			switch (argsVerificar[0].toUpperCase()) {

			case "STRING":

				String[] argsValidos = new String[] { "*", "<", ">", "num" };
				String numInserido = "";
				Boolean charDiferente = true;
				List<Integer> posicao = new ArrayList<Integer>();
				String formatado = "";

				try {

					String[] listaDeArgs = argsVerificar[1].split(":");

					if (listaDeArgs.length <= 3) {
						for (String st : listaDeArgs) {
							for (int i = 0; i < 3; i++) {

								if (st.equals(argsValidos[i])) {

									posicao.add(i);
									charDiferente = false;
									break;

								} else if (verificarCARACTERouINTEGER("num", st)) {
									numInserido = st;
									charDiferente = false;
									break;
								}
								charDiferente = true;
							}
							if (charDiferente) {
								System.out.println("Erro   simbolo nao reconhecido " + st);
								return null;
							}
						}

						Collections.sort(posicao);
						if (!numInserido.isEmpty()) {
							formatado += "STRING/" + numInserido;
						}

						for (int st = 0; st < posicao.size(); st++) {

							if (!numInserido.isEmpty())
								formatado += ":" + argsValidos[posicao.get(st)];
							else {
								if (st == 0)
									formatado += "STRING/" + argsValidos[posicao.get(st)];
								else
									formatado += ":" + argsValidos[posicao.get(st)];
							}
						}

					} else {
						System.out.println("Erro muitos argumentos em STRING");
						return null;
					}

				} catch (Exception e) {
					
						formatado += "STRING/";
				}
				
				if (Formatado.isEmpty())
					Formatado.add(formatado);
				else
					Formatado.add(","+formatado);
				break;
				
				
			case "INTEGER":
				
				
				String[] argsValidosI = new String[] { "*", "num" };
				String numInseridoI = "";
				Boolean charDiferenteI = true;
				List<Integer> posicaoI = new ArrayList<Integer>();
				String formatadoI = "";

				try {

					String[] listaDeArgsI = argsVerificar[1].split(":");

					if (listaDeArgsI.length <= 2) {
						for (String it : listaDeArgsI) {
							for (int i = 0; i < 2; i++) {

								if (it.equals(argsValidosI[i])) {

									posicaoI.add(i);
									charDiferenteI = false;
									break;

								} else if (verificarCARACTERouINTEGER("num", it)) {
									numInseridoI = it;
									charDiferenteI = false;
									break;
								}
								charDiferenteI = true;
							}
							if (charDiferenteI) {
								System.out.println("Erro   simbolo nao reconhecido " + it);
								return null;
							}
						}

						Collections.sort(posicaoI);
						if (!numInseridoI.isEmpty()) {
							formatadoI += "INTEGER/" + numInseridoI;
						}

						for (int st = 0; st < posicaoI.size(); st++) {

							if (!numInseridoI.isEmpty())
								formatadoI += ":" + argsValidosI[posicaoI.get(st)];
							else {
								if (st == 0)
									formatadoI += "INTEGER/" + argsValidosI[posicaoI.get(st)];
								else
									formatadoI += ":" + argsValidosI[posicaoI.get(st)];
							}
						}

					} else {
						System.out.println("Erro muitos argumentos em INTEGER");
						return null;
					}

				} catch (Exception e) {
					
						formatadoI += "INTEGER/";
					
				}
				if (Formatado.isEmpty())
					Formatado.add(formatadoI);
				else
					Formatado.add(","+formatadoI);
				break;
			case "DOUBLE":
				
				
				String[] argsValidosD = new String[] { "*", "num.num" };
				String numInseridoD = "";
				Boolean charDiferenteD = true;
				List<Integer> posicaoD = new ArrayList<Integer>();
				String formatadoD = "";

				try {

					String[] listaDeArgsD = argsVerificar[1].split(":");

					if (listaDeArgsD.length <= 2) {
						for (String dou : listaDeArgsD) {
							for (int i = 0; i < 2; i++) {

								if (dou.equals(argsValidosD[i])) {

									posicaoD.add(i);
									charDiferenteD = false;
									break;

								} else if (verificarCARACTERouINTEGER("num", dou)) {
									numInseridoD = dou;
									charDiferenteD = false;
									break;
								}
								charDiferenteD = true;
							}
							if (charDiferenteD) {
								System.out.println("Erro   simbolo nao reconhecido " + dou);
								return null;
							}
						}

						Collections.sort(posicaoD);
						if (!numInseridoD.isEmpty()) {
							formatadoD += "DOUBLE/" + numInseridoD;
						}

						for (int st = 0; st < posicaoD.size(); st++) {

							if (!numInseridoD.isEmpty())
								formatadoD += ":" + argsValidosD[posicaoD.get(st)];
							else {
								if (st == 0)
									formatadoD += "DOUBLE/" + argsValidosD[posicaoD.get(st)];
								else
									formatadoD += ":" + argsValidosD[posicaoD.get(st)];
							}
						}

					} else {
						System.out.println("Erro muitos argumentos em DOUBLE");
						return null;
					}

				} catch (Exception e) {
					
						formatadoD += "DOUBLE/";
		
				}
				if (Formatado.isEmpty())
					Formatado.add(formatadoD);
				else
					Formatado.add(","+formatadoD);
				break;

			case "BOOLEAN":
				String[] argsValidosB = new String[] { "*", "num.num" };
				String numInseridoB = "";
				Boolean charDiferenteB = true;
				List<Integer> posicaoB = new ArrayList<Integer>();
				String formatadoB = "";

				try {

					String[] listaDeArgsD = argsVerificar[1].split(":");

					if (listaDeArgsD.length <= 2) {
						for (String it : listaDeArgsD) {
							for (int i = 0; i < 2; i++) {

								if (it.equals(argsValidosB[i])) {

									posicaoB.add(i);
									charDiferenteI = false;
									break;

								} else if (verificarCARACTERouINTEGER("num", it)) {
									numInseridoI = it;
									charDiferenteI = false;
									break;
								}
								charDiferenteI = true;
							}
							if (charDiferenteB) {
								System.out.println("Erro   simbolo nao reconhecido " + it);
								return null;
							}
						}

						Collections.sort(posicaoB);
						if (!numInseridoB.isEmpty()) {
							formatadoB += "BOOLEAN/" + numInseridoB;
						}

						for (int st = 0; st < posicaoB.size(); st++) {

							if (!numInseridoB.isEmpty())
								formatadoB += ":" + argsValidosB[posicaoB.get(st)];
							else {
								if (st == 0)
									formatadoB += "BOOLEAN/" + argsValidosB[posicaoB.get(st)];
								else
									formatadoB += ":" + argsValidosB[posicaoB.get(st)];
							}
						}

					} else {
						System.out.println("Erro muitos argumentos em BOOLEAN");
					}

				} catch (Exception e) {
				
						formatadoB += "BOOLEAN/";
					
				}
				if (Formatado.isEmpty())
					Formatado.add(formatadoB);
				else
					Formatado.add(","+formatadoB);
				break;

			}

		}
		return Formatado;
		
	}
}
