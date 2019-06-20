package aplicacao;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import funcoes.para_arquivos.*;
import funcoes.para_xdb.CRUD;
import funcoes.para_xdb.ConfiguracaoDoXDB;
import funcoes.para_xdb.ManipularXDB;


/**
 *
 * @author carlos-abp
 */

public class TestarEXC
{

	/**
	 *
	 * @param args ferramenta de integracao com outras aplicacao afim de gerir dados da propria
	 *             aplicacao hospedeira
	 */
	public static void main(String[ ] args)
	{
		// ManipularArquivo.criarArquivo("TESTANDO", "liu.mdf");

		// ManipularArquivo.criarArquivo("TESTANDO/Libs", "teste.txt");
		File arq = new File("TESTANDO/Libs");

		ManipularXDB bd = new ManipularXDB(arq, "GAME");
		ConfiguracaoDoXDB cbd = new ConfiguracaoDoXDB(arq, "GAME");

		// bd.criarBaseDeDados();

		// bd.renomearBaseDeDados("GAME");

	//	List<String> listaConfig = new ArrayList<>();
	//	listaConfig.add("id,nome");
	//	listaConfig.add("double/3.3:*,String/12:*");
	//	listaConfig.add("code=zus");
	//	listaConfig.add("link=gmail.com");
      //
	//	cbd.escreverConfig(listaConfig);
		
		CRUD crud = new CRUD(bd);
		System.out.println(crud.criarDado("11"));

//  
	}

}