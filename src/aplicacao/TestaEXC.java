package aplicacao;

import funcoes.para_xdb.ConfiguracaoDoXDB;
import funcoes.para_xdb.ManipularXDB;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlos-abp
 */

public class TestaEXC {

	/**
    *
    * @param args ferramenta de integracao com outras aplicacao afim de gerir dados da propria aplicacao hospedeira
    */
   public static void main(String[] args)
   {
       File arquivo = new File("teste");

       ManipularXDB baseNova = new ManipularXDB(arquivo, "clientes");
       ConfiguracaoDoXDB cnf = new ConfiguracaoDoXDB(baseNova.getcaminhoDoXDB(), baseNova.getNomeDoXDB());

       //baseNova.criarBaseDeDados(arquivo);
       List<String> cab = new ArrayList<>();
       cab.add("id,nome,sexo");
       cab.add("String/^:12:?,Double/?:3.3,Integer/3");
       cab.add("code=us");

       cnf.escreverCabechalho(cab);
   }

}