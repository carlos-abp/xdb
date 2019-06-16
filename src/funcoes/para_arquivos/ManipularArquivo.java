package funcoes.para_arquivos;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author carlos-abp
 */
public class ManipularArquivo {

	private String[] pastasEArq;

	/**
	 * Cria pasta e subpasta mesmo se nao existir.
	 * @param caminhoCompleto ex:/home/carlos-abp/libs/pasta1/pasta2 ...
	 */
	public static void criarPasta(String caminhoCompleto) {
		File arq = new File(caminhoCompleto);
		arq.mkdirs();
	}

	/**
	 *
	 * @param caminhoPai    caminho ate a pasta que contem o arquivo ex:"/home/carlos-abp/libs".
	 * @param nomeDoArquivo nome e estencao ex: "teste.txt".
	 */
	public static void criarArquivo(String caminhoPai, String nomeDoArquivo) {
		File arq = new File(caminhoPai + "/" +nomeDoArquivo);
		try {
			if (!arq.exists()) {
				new File(caminhoPai).mkdirs();
			}
			arq.createNewFile();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Excluir arquivos e ate pasta passado no segundo ARGUMENTO
	 *
	 * @param caminhoPai           diretorio em que esta o objeto a ser apagado ex:"/home/carlos-abp/libs".
	 * @param nomeDoArquivoOuPasta nome da pasta ou arquivo que deseja excluir ex:"teste.txt".
	 * @return o resultado da exclusao. 
	 */
	public static Boolean excluirArquivos(String caminhoPai, String nomeDoArquivo) {
		/*
		 * Melhorar o codigo abaixo muitas instancia do File sendo iniciada if("*")
		 *    solucao Usar a classe privada excluirArq(String)
		 */
		if(nomeDoArquivo.equals("*")) {
			ManipularArquivo.excluirArquivos(new File(new File(caminhoPai).getAbsolutePath()).getParent()+"/",caminhoPai);
			ManipularArquivo.criarPasta(caminhoPai);
			
			return true;
		}
		if(!nomeDoArquivo.isEmpty()) 
		{
			File arq = new File(caminhoPai + "/" + nomeDoArquivo);
			String[] filhos = arq.list();
			Map<Integer, String> arvoresDePastas = new TreeMap<>();
	
			if (!arq.exists()) {
				System.out.println("Erro 12510 : arqivo ou pasta inexistente");
			} else {
				if (arq.isFile()) {
					arq.delete();
				} else {
					for (int i = 0; i < filhos.length; i++) {
						File pastaTemporaria = new File(arq + "/" + filhos[i]);
						if (pastaTemporaria.isFile()) {
							pastaTemporaria.delete();
						} else {
	
							String[] temporario = pastaTemporaria.list();
	
							if (temporario.length == 0) {
								pastaTemporaria.delete();
							} else {
								arvoresDePastas.put(i, pastaTemporaria.getPath());
							}
	
						}
	
					}
	
					for (Integer a : arvoresDePastas.keySet()) {
						try {
							ExcluirArquivos(arvoresDePastas.get(a));
						} catch (NullPointerException e) {
						}
					}
	
				}
	
			}
			return arq.delete();
		}
		else {
			System.out.println("Erro 12539 : O nome do arquivo nao pode ser nulo ");
		}

		return false;
	}

	/**
	 * Metodo que excluir as pastas dentro da pasta que deseja excluir
	 *
	 * @param caminhoPai Passa-se o caminho completo ex:"/home/eclipse/program" ||
	 *                   "/home/eclipse/program.exe"
	 */
	private static void ExcluirArquivos(String caminhoCompleto) {
		File arq = new File(new File(caminhoCompleto).getParent() + "/" + new File(caminhoCompleto).getName());
		System.out.println(arq);
		String[] filhos = arq.list();
		Map<Integer, String> arvoresDePastas = new TreeMap<>();

		for (int i = 0; i < filhos.length; i++) {
			File pastaTemporaria = new File(arq + "/" + filhos[i]);

			if (pastaTemporaria.isFile()) {
				System.out.println("del: " + pastaTemporaria.getPath());
				pastaTemporaria.delete();
			} else {

				String[] temporario = pastaTemporaria.list();

				if (temporario.length == 0) {
					System.out.println("del: " + pastaTemporaria.getPath());
					pastaTemporaria.delete();
				} else {
					arvoresDePastas.put(i, pastaTemporaria.getPath());
				}

			}

		}

		for (Integer a : arvoresDePastas.keySet()) {
			System.out.println(arvoresDePastas.get(a));
			try {
				System.out.println(
						new File(arvoresDePastas.get(a)).getParent() + new File(arvoresDePastas.get(a)).getName());
				excluirArquivos(new File(arvoresDePastas.get(a)).getParent(),
						new File(arvoresDePastas.get(a)).getName());

			} catch (NullPointerException e) {

			}
		}
		System.out.println(arq.delete());

	}

}