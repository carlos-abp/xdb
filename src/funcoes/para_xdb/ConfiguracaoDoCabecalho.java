package funcoes.para_xdb;

import funcoes.para_arquivos.ManipularConteudo;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Carlos Alexandre Classe responsavel pela manipulacao do cabecalho, alterando funcionalidade deste.
 */
public class ConfiguracaoDoCabecalho
{

    /*
     *campos responsavel pela estruturacao e funcionalidades da classe
     */
    private final Character separarCampo = ',';
    private final Character isolarQuantidade = '/';

    /**
     *
     * @param cabecalhoVerificar Recebe o cabechalho e verifica se esta bem estruturado tanto com pontuacao quanto estrutura ex:(^ = ,) List[nome,id ^ (String/*,Integer) ^ [code=pt]].
     * @return Se o cabechalho cumpre os requesitos
     */
    public static Boolean verificarCabecalho(List<String> cabecalhoVerificar)
    {
        ConfiguracaoDoCabecalho c = new ConfiguracaoDoCabecalho();
        if (cabecalhoVerificar.size() == 2) {

            String[] listaDeNomes = cabecalhoVerificar.get(0).split(",");
            String[] listaDeVariavel = cabecalhoVerificar.get(1).split(",");
            if (listaDeNomes.length == listaDeVariavel.length) {
                return true; //nao verifica os atributos da variaveis
            }
            else {
                System.out.println("Erro 12515 Numeros inconpativel de variavel e tipos");
            }

        }
        else {
            System.out.println("Erro 12514 Numeros de index incompativel");
            return false;
        }
        return false;
    }

    public static Map<Integer, String> verificarAtributosDasVariaveis(File arqDeCabecalho)
    {
        Map<Integer, String> mapaDosAtributosDasVariaveis = new HashMap<>();
        mapaDosAtributosDasVariaveis = ManipularConteudo.lerNoArquivo(arqDeCabecalho);

        System.out.println(mapaDosAtributosDasVariaveis.get(1));

        String[] listaCruaDoMap = mapaDosAtributosDasVariaveis.get(1).split(",");

        return null;
    }

    /**
     *
     * @param Arq instanciado de forma que o diretorio seja a onde foi criado a base de dados,ex: crie na pasta "/home/carlos-abp/Documentos" diretorio completo
     * /home/carlos-abp/Documentos/myDados/myDados.xdb.
     * @param CabecalhoEscrever Este parametro de obdecer ex:(^ = ,) List[nome,id ^ (String/*,Integer) ^ [code=pt]]
     * @param nomeXDB nome dado ao banco de dados criado
     * @return Se foi ou nao escrito.
     */
    public static Boolean escreverCabechalho(File Arq, String nomeXDB, List<String> CabecalhoEscrever)
    {
        String caminhoDoCabecalho = Arq.toString() + "/" + nomeXDB + "/.cabecalho";

        if (new File(caminhoDoCabecalho).exists()) {
            if (verificarCabecalho(CabecalhoEscrever)) {
                ConfiguracaoDoCabecalho c = new ConfiguracaoDoCabecalho();

                ManipularConteudo.escreverNoArquivo(CabecalhoEscrever.get(0), new File(caminhoDoCabecalho), false);
                ManipularConteudo.escreverNoArquivo(CabecalhoEscrever.get(1), new File(caminhoDoCabecalho), true);
                if (CabecalhoEscrever.size() == 3) {
                    ManipularConteudo.escreverNoArquivo(CabecalhoEscrever.get(2), new File(caminhoDoCabecalho), true);
                }

                return true;
            }
            else {
                System.out.println("Erro 12506 : erro lista de cabecalho invalida");
            }

        }
        else {
            System.out.println("Erro 12507 : nao encontrado o arquivo .cabecalho");
        }
        return null;
    }

    /**
     *
     * @param Arq File instanciado de forma que o diretorio completo, com arqivo e estencao seja do XDB criado.
     * @param opcaoParaEdicao Index 1 ("variavel","tipos","atributos") Index 2("a alteracao em si") ex: "nome=carlos" , "nome=String/30"*nao feita "code=us"*nao feita
     */
    public static void editarCabecalho(File Arq, String[] opcaoParaEdicao)
    {
        switch (opcaoParaEdicao[0]) {
            case "variavel":
                String texto = ManipularConteudo.lerNoArquivo(new File(Arq.getParent() + "/.cabecalho.xdb"), 1);

                List<String> nomesDasVariaveis = new ArrayList<>(Arrays.asList(texto.split(",")));
                String[] valoresAAdicionar = opcaoParaEdicao[1].split("->");

                if (nomesDasVariaveis.contains(valoresAAdicionar[0])) {

                    int posicao = nomesDasVariaveis.indexOf(valoresAAdicionar[0]);

                    nomesDasVariaveis.add(posicao, valoresAAdicionar[1]);
                    nomesDasVariaveis.remove(posicao + 1);

                    for (String a : nomesDasVariaveis) {
                        if (a == nomesDasVariaveis.get(0)) {
                            texto = a;
                        }
                        else {
                            texto += "," + a;
                        }
                    }
                    System.out.println(texto);
                    ManipularConteudo.escreverNoArquivo(texto, Arq, 1);
                }

                break;
            case "tipos":
                break;
            case "atributos":
                break;
            default:
                System.out.println("Erro 12504: opcao invalida para a modificacao");
        }
    }
}
