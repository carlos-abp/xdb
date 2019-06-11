package aplicacao;

import funcoes.para_arquivos.ManipularArquivo;
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

//        File file = new File("teste");
//
//        List<String> cab = new ArrayList<>();
//        cab.add("name,id");
//        cab.add("(String/*,int/*)");
//        cab.add("[chave=id]");
//
//        ManipularXDB db = new ManipularXDB();
//
//        db.criarBaseDeDados(file, "bd");
//        //ConfiguracaoDoCabecalho.escreverCabechalho(file, "bd", cab);
//        //db.excluirBaseDeDados(file, "bd");
        ManipularArquivo.excluirArquivos("t", "teste");
    }

}
