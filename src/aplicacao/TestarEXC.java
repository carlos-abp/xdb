package aplicacao;

import funcoes.para_arquivos.*;

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
	   ManipularArquivo.criarArquivo("TESTANDO", "liu.mdf");
	   ManipularArquivo.excluirArquivos("TESTANDO", "*");
       
   }

}