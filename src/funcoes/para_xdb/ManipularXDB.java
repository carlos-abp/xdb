package funcoes.para_xdb;

import funcoes.para_arquivos.ManipularArquivo;
import java.io.File;

/**
 *
 * @author carlos-abp
 */
public class ManipularXDB
{

    /**
     *
     * @param caminho a pasta que deseja criar ex:/home/carlos-abp/Documentos
     * @param nomeDoXDB nome escolhido XDB
     */
    public void criarBaseDeDados(File caminho, String nomeDoXDB)
    {
        String x = caminho.toString() + "/" + nomeDoXDB + "/";

        if (!new File(x).exists()) {
            ManipularArquivo.criarPasta(x);
            ManipularArquivo.criarArquivo(x, nomeDoXDB + ".xdb");
            ManipularArquivo.criarArquivo(x, ".cabecalho");
        }
        else {
            System.out.println("Base de dados XDB ja existente");
        }
    }

    public void excluirBaseDeDados(File caminho, String nomeDoXDB)
    {

        if (new File(caminho.getPath() + "/" + nomeDoXDB).exists()) {
            //ManipularArquivo.excluirArquivo(caminho.getPath(), nomeDoXDB);
        }
        else {
            System.out.println("Base de dados XDB nao existe");
        }
    }

}
