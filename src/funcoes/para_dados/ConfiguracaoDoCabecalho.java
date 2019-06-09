package funcoes.para_dados;

import java.io.File;
import java.util.List;

public class ConfiguracaoDoCabecalho
{

    private Character separarCampo = ',';
    private Character[] isolarVar = new Character[]{'(', ')'};
    private Character isolarQuantidade = '/';
    private Character[] isolarAtributo = new Character[]{'[', ']'};

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

    public static Boolean escreverCabechalho(List<String> CabecalhoEscrever, File Arq)
    {
        if (verificarCabechalho(CabecalhoEscrever)) {
            ConfiguracaoDoCabecalho c = new ConfiguracaoDoCabecalho();

            return true;
        }
        else {
            return null;
        }
    }

}
