package funcoes.para_xdb;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

public class CRUD {

	private ManipularXDB baseDeDados;
	private String NomeDasvariaveis;
	private String AtributosDasVariaveis;

	public CRUD(ManipularXDB bd) {
		this.baseDeDados = bd;
//+++ implementaar captura de dados para a classse funcionar
	}

	// --------------------------------------------------------

	public Boolean criarDado(String oDados) {
		String[] elementosDosDados = oDados.split(",");
		String[] atributosFracionado = AtributosDasVariaveis.split(";");
		String[] elementoDeCadaNome = NomeDasvariaveis.split(",");
		String[] atributosEArgumentos;
		List<String> Formatado = new ArrayList<>();

		if (elementosDosDados.length == atributosFracionado.length) {
			for (int i = 0; i < elementosDosDados.length; i++) {
				
//variavel responsavel a montar os dados de cada case no switch abaixo; 
				String temp;
				
				atributosEArgumentos = atributosFracionado[i].split("/");

				switch (atributosEArgumentos[0]) {
				case "STRING":
					temp ="";
					try {
						if (atributosEArgumentos[1].split(":")[0] != "*" || atributosEArgumentos[1].split(":")[0] != ">"
								|| atributosEArgumentos[1].split(":")[0] != "<") 
						{
							
							if (atributosEArgumentos[1].contains("*")) 
							{
								if (elementosDosDados[i].length() <= Integer.parseInt(atributosEArgumentos[1])) {
									temp = elementosDosDados[i];
									break;
								} else {
									System.out.println("O " + elementoDeCadaNome[i] + " nao aceita mais do que "
											+ atributosEArgumentos[0] + " caracteres");
									return false;
								}
							}
							if (atributosEArgumentos[1].contains("<")) {

								
									temp = elementosDosDados[i];
									break;
								
									
							}
							if (atributosEArgumentos[1].contains(">")) 
							{

								temp = elementosDosDados[i];
								break;
							}

						} else {
							if (atributosEArgumentos[1].contains("*")) {

							}
							if (atributosEArgumentos[1].contains("<")) {

							}
							if (atributosEArgumentos[1].contains(">")) {

							}
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					Formatado.add(temp);
					break;
				case "CHAR":
					if (DADOS.verificarCARACTERouINTEGER("num", elementosDosDados[i])) {
						// gravar integer ou double implementar codigo
					}
					break;
				case "INTEGER":
					if (DADOS.verificarCARACTERouINTEGER("num", elementosDosDados[i])) {
						// gravar integer ou double implementar codigo
					}
					break;
				case "DOUBLE":
					if (DADOS.verificarCARACTERouINTEGER("num", elementosDosDados[i])) {
						// gravar integer ou double implementar codigo
					}
					break;
				case "BOOLEAN":

					break;
				}

			}
		}
	}
}
