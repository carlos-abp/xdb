package aplicacao;

import funcoes.para_arquivos.ManipularArquivo;
import funcoes.para_arquivos.VerificarOuAtualizar;
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

        File file = new File("config");
//
        List<String> cab = new ArrayList<>();
        cab.add("name,id(String/*,int/*)[chave=id]");
//

        ManipularXDB db = new ManipularXDB();
        db.criarBaseDeDados(file, "bbb");

    }

}
