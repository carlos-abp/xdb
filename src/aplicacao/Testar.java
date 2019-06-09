package aplicacao;

import funcoes.para_conteudo.ConfiguracaoDoCabecalho;
import funcoes.para_conteudo.ManipularConteudo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Testar
{

    public static void main(String[] args)
    {

        File file = new File("config/teste.txt");
//
        List<String> cab = new ArrayList<>();
        cab.add("name,id(String/*,int/*)[chave=id]");
//        //ConfiguracaoDoCabecalho.verificarCabechalho(cab);
//        ConfiguracaoDoCabecalho.escreverCabechalho(cab, file);

        //ConfiguracaoDoCabecalho.escreverCabechalho(file, cab);
        ConfiguracaoDoCabecalho.editarCabecalho(file, new String[]{"variavel", "id->001"});

    }

}
