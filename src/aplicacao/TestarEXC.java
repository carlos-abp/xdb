package aplicacao;

import java.io.File;

import funcoes.para_arquivos.*;
import funcoes.para_xdb.ManipularXDB;

/**
 *
 * @author carlos-abp
 */

public class TestarEXC {

	/**
    *
    * @param args ferramenta de integracao com outras aplicacao afim de gerir dados da propria aplicacao hospedeira
    */
   public static void main(String[] args)
   {  
	   //ManipularArquivo.criarArquivo("TESTANDO", "liu.mdf");
	   
	   //ManipularArquivo.criarArquivo("TESTANDO/Libs", "teste.txt");
	   File arq = new File("TESTANDO/Libs");
	  
	   
	  ManipularXDB bd = new ManipularXDB();
	  
	  bd.setNomeDoXDB("ringo");
	  bd.setCaminhoDoXDB(arq);
	  
	  bd.renomearBaseDeDados("teste");
			  
	  //bd.criarBaseDeDados();
//  
   }

}