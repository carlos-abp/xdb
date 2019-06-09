package funcoes.para_conteudo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfiguracaoDoCabecalho
{

    private final Character separarCampo = ',';
    private final Character[] isolarVar = new Character[]{'(', ')'};
    private final Character isolarQuantidade = '/';
    private final Character[] isolarAtributo = new Character[]{'[', ']'};

    public static Boolean verificarCabechalho(List<String> cabecalhoVerificar)
    {
        ConfiguracaoDoCabecalho c = new ConfiguracaoDoCabecalho();

        if (cabecalhoVerificar.get(0).contains(String.valueOf(c.isolarVar[0]))
                && cabecalhoVerificar.get(0).contains(String.valueOf(c.isolarVar[1]))) {
            if (cabecalhoVerificar.get(0).contains(String.valueOf(c.isolarAtributo[0]))
                    && cabecalhoVerificar.get(0).contains(String.valueOf(c.isolarAtributo[1]))) {
                if (cabecalhoVerificar.get(0).contains(String.valueOf(c.isolarQuantidade))
                        && cabecalhoVerificar.get(0).contains(String.valueOf(c.isolarQuantidade))) {
                    return true;
                }
                else {
                    System.out.println("Erro 12503: separadores nao indentificado");
                }

            }
            else {
                System.out.println("Erro 12502: nao foi possivel localizar atributos");
            }
        }
        else {
            System.out.println("Erro 12501: nao foi possivel separar variaveis com tipos das variaveis");
        }

        return false;
    }

    public static Boolean escreverCabechalho(File Arq, List<String> CabecalhoEscrever)
    {
        if (verificarCabechalho(CabecalhoEscrever)) {
            ConfiguracaoDoCabecalho c = new ConfiguracaoDoCabecalho();

            ManipularConteudo.escreverNoArquivo(CabecalhoEscrever.get(0), new File(Arq.getParent() + "/.cabecalho.xdb"), false);

            return true;
        }
        else {
            return null;
        }
    }

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
