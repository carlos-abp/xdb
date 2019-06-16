package funcoes.para_xdb;

import funcoes.para_arquivos.ManipularArquivo;
import java.io.File;

/**
 *
 * @author carlos-abp
 */
public class ManipularXDB
{

    private String nomeDoXDB;
    private File caminhoDoXDB;
    public final File caminhoCompleto = new File(caminhoDoXDB + "/" + nomeDoXDB);

    public ManipularXDB(String nomeDoXDB)
    {
        this.nomeDoXDB = nomeDoXDB;
    }

    public ManipularXDB(File caminho, String nomeDoXDB)
    {
        this.nomeDoXDB = nomeDoXDB;
        this.caminhoDoXDB = caminho;
    }
//----------------------------------------------------------------------------------------------------------

    /**
     *
     * @param caminho a pasta que deseja criar ex:/home/carlos-abp/Documentos
     *
     */
    public void criarBaseDeDados(File caminho)
    {
        String caminhoCompleto= caminho.toString() + "/" + nomeDoXDB + "/";

        if (!new File(caminhoCompleto).exists()) {
            ManipularArquivo.criarPasta(caminhoCompleto);
            ManipularArquivo.criarArquivo(caminhoCompleto, nomeDoXDB + ".xdb");
            ManipularArquivo.criarArquivo(caminhoCompleto, ".cabecalho");
        }
        else {
            System.out.println("Base de dados XDB ja existente");
        }
    }

    public void excluirBaseDeDados()
    {

        if (new File(caminhoDoXDB.getPath() + "/" + nomeDoXDB).exists()) {
            ManipularArquivo.excluirArquivos(caminhoDoXDB.getPath(), nomeDoXDB);
        }
        else {
            System.out.println("Base de dados XDB nao existe");
        }
    }

    public void renomearBaseDeDados(String novoNomeDoXDB)
    {

        File arq = new File(caminhoDoXDB + "/" + nomeDoXDB);

        if (arq.renameTo(new File(caminhoDoXDB + "/" + novoNomeDoXDB))) {
            File arq2 = new File(caminhoDoXDB + "/" + novoNomeDoXDB + "/" + nomeDoXDB + ".xdb");
            arq2.renameTo(new File(caminhoDoXDB + "/" + novoNomeDoXDB + "/" + novoNomeDoXDB + ".xdb"));
        }
        else {
            System.out.println("Erro 12511");
        }
    }

//_________________________________________________________________________________________________________
    public void setNomeDoXDB(String novoNomeDoXDB)
    {
        this.nomeDoXDB = nomeDoXDB;
    }

    public String getNomeDoXDB()
    {
        return nomeDoXDB;
    }

    public void setcaminhoDoXDB(File caminhoDoXDB)
    {
        this.caminhoDoXDB = caminhoDoXDB;
    }

    public File getcaminhoDoXDB()
    {
        return caminhoDoXDB;
    }
}