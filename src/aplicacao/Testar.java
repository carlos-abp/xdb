package aplicacao;

import funcoes.para_arquivos.ManipularArquivo;
import funcoes.para_arquivos.VerificarOuAtualizar;
import funcoes.para_dados.ConfiguracaoDoCabecalho;
import java.util.ArrayList;
import java.util.List;

public class Testar
{

    public static void main(String[] args)
    {

        //ManipularArquivo.criarArquivo("/home/carlos-abp/Área de Trabalho/", "carlinhos.gay");
        //ManipularArquivo.apagarArquivo("/home/carlos-abp/Área de Trabalho/", "carlinhos.gay");
        VerificarOuAtualizar.arquivoEscreviviel("/home/carlos-abp/Área de Trabalho/carlinhos.gay", 'a');
//        List<String> cab = new ArrayList<>();
//        cab.add("name,id(String/*,int/*)[chave=id]");
//        ConfiguracaoDoCabecalho.verificarCabechalho(cab);
    }

}
