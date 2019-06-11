package aplicacao;

import funcoes.para_arquivos.ManipularArquivo;
import funcoes.para_arquivos.ManipularConteudo;
import funcoes.para_xdb.ConfiguracaoDoCabecalho;
import funcoes.para_xdb.ManipularXDB;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlos-abp
 */
public class Testar
{

    /**
     *
     * @param args ferramenta de integracao com outras aplicacao afim de gerir dados da propria aplicacao hospedeira
     */
    public static void main(String[] args)
    {

        File file = new File("teste");

        List<String> cab = new ArrayList<>();
        cab.add("id,nome,sobrenome");
        cab.add("Int,String/*,String/6");

        ManipularXDB db = new ManipularXDB(file, "testeXDB");
        //db.criarBaseDeDados(file);
        ConfiguracaoDoCabecalho.escreverCabechalho(file, db.getNomeDoXDB(), cab);
        // db.excluirBaseDeDados();
//        ConfiguracaoDoCabecalho.editarCabecalho(new File(file.getPath() + "/" + "clientes"), cab);
        //db.renomearBaseDeDados("client21");

    }

}
