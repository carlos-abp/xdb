package funcoes.para_xdb;

import funcoes.para_arquivos.ManipularArquivo;
import java.io.File;

/**
 *
 * @author carlos-abp
 */
public class ManipularXDB
{

    public void criarBaseDeDados(File caminho, String nomeDoXDB)
    {
        String x = caminho.toString() + "/" + nomeDoXDB + "/";
        if (!new File(x).exists()) {
            ManipularArquivo.criarPasta(x);
            ManipularArquivo.criarArquivo(x, nomeDoXDB + ".xdb");
            ManipularArquivo.criarArquivo(x, ".cabechalho");
        }
        else {
            System.out.println("Base de dados XDB ja existente");
        }
    }

}
