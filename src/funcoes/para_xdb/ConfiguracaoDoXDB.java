package funcoes.para_xdb;

import funcoes.para_arquivos.ManipularConteudo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Carlos Alexandre Classe responsavel pela manipulacao do cabecalho, alterando funcionalidade deste.
 */
public class ConfiguracaoDoXDB
{

    /*
     *campos responsavel pela estruturacao e funcionalidades da classe
     */
    private final Character separarCampo = ',';
    private final Character isolarQuantidade = '/';
    private File arqComPastaXDB;

    public ConfiguracaoDoXDB(File arqComPastaXDB, String nomeDoXDB)
    {
        this.arqComPastaXDB = new File(arqComPastaXDB + "/" + nomeDoXDB);
    }

//_________________________________________________________________________________________________________________________________________________________________
    /**
     * @param arqAPesquisar caminho ate a pasta do arq ex: /home/XDB/teste
     * @return a existencia do arquivo .cabecalho ou do .config
     */
    public Boolean arquivoExiste(String arqAPesquisar)
    {
        File arq = new File(arqComPastaXDB + "/" + arqAPesquisar);
        return arq.exists();
    }

    /**
     *
     * @param cabecalhoVerificar Recebe o cabechalho e verifica se esta bem estruturado tanto com pontuacao quanto estrutura ex:(^ = ,) List[nome,id ^ (String/*,Integer) ^ [code=pt]].
     * @return Se o cabechalho cumpre os requesitos
     */
    public List<String> verificarOCabecalho(List<String> cabecalhoVerificar)
    {

        if (cabecalhoVerificar.size() == 2 || cabecalhoVerificar.size() == 3) {

            String[] listaDeNomes = cabecalhoVerificar.get(0).split(",");
            String[] listaDeVariavel = cabecalhoVerificar.get(1).split(",");

            if (listaDeNomes.length == listaDeVariavel.length) {
                if (AtributosDasVariaveis(cabecalhoVerificar.get(1)) != null) {
                    String temp = cabecalhoVerificar.get(1);
                    String temp2 = "";
                    for (String a : AtributosDasVariaveis(cabecalhoVerificar.get(1))) {
                        temp2 += a;
                    }

                    cabecalhoVerificar.remove(1);
                    cabecalhoVerificar.add(1, temp2);

                    return cabecalhoVerificar;
                }
                else {
                    return null;
                }
            }
            else {
                System.out.println("Erro 12515 Numeros inconpativel de variavel e tipos");
                return null;
            }
        }
        else {
            System.out.println("Erro 12514 Numeros de index incompativel");
            return null;
        }

    }

    private List<String> AtributosDasVariaveis(String trecho)
    {
//        String/@?:int:^
//        Integer/@?:int
//        Double/@?:int.int
//        Boolean/@?
//        Character/@?:^

        List<String> cabecalhoCompleto = new ArrayList<>();

        String[] trechoRecortado = trecho.split(",");

        for (String a : trechoRecortado) {

            String[] verificar = a.split("/");

            switch (verificar[0]) {

                case "String":
                    try {
                        boolean argumentoVazio = verificar[1].contains("?");
                        boolean letraDeForma = verificar[1].contains("^");
                        String numVerificado = "";
                        String formatado = "String/";

                        if (verificar[1].split(":").length <= 3) {

                            String[] temporario = verificar[1].split(":");

                            for (String b : temporario) {

                                if (!"?".equals(b.trim()) && !"^".equals(b.trim())) {
                                    if (verificarCHARouINTEGER("num", b)) {
                                        numVerificado = b;
                                    }
                                    else {
                                        System.out.println("Erro 12520 dados incompativel");
                                        return null;
                                    }
                                }

                            }

                            if (argumentoVazio && letraDeForma && !numVerificado.isEmpty()) {
                                formatado = ("String/" + numVerificado + ":?:^");
                            }
                            else if (argumentoVazio && letraDeForma) {
                                formatado = ("String/?:^");
                            }
                            else if (argumentoVazio && !numVerificado.isEmpty()) {
                                formatado = ("String/?:^");
                            }
                            else if (letraDeForma && !numVerificado.isEmpty()) {
                                formatado = ("String/" + numVerificado + ":^");

                            }
                            else if (argumentoVazio) {
                                formatado = ("String/?");
                            }
                            else if (!numVerificado.isEmpty()) {
                                formatado = ("String/" + numVerificado);
                            }
                            else {
                                formatado = ("String/^");
                            }

                        }

                        if (0 != cabecalhoCompleto.size()) {
                            cabecalhoCompleto.add("," + formatado);
                        }
                        else {
                            cabecalhoCompleto.add(formatado);
                        }

                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        if (0 != cabecalhoCompleto.size()) {
                            cabecalhoCompleto.add(",String/");
                        }
                        else {
                            cabecalhoCompleto.add("String/");
                        }
                    }

                    break;

                case "Integer":

                    try {
                        boolean argumentoVazio = verificar[1].contains("?");
                        String numVerificado = "";
                        String formatado = "";

                        if (verificar[1].split(":").length <= 2) {

                            String[] temporario = verificar[1].split(":");

                            for (String b : temporario) {

                                if (!"?".equals(b.trim())) {
                                    if (verificarCHARouINTEGER("num", b)) {
                                        numVerificado = b;

                                    }
                                    else {
                                        System.out.println("Erro 12524 dados incompativel");
                                        return null;
                                    }
                                }

                            }

                            if (argumentoVazio && !numVerificado.isEmpty()) {
                                formatado = ("Integer/" + numVerificado + ":?");
                            }
                            else if (argumentoVazio && !numVerificado.isEmpty()) {
                                formatado = ("Integer/?:^");
                            }
                            else if (argumentoVazio) {
                                formatado = ("Integer/?");
                            }
                            else if (!numVerificado.isEmpty()) {
                                formatado = ("Integer/" + numVerificado);
                            }

                        }

                        if (0 != cabecalhoCompleto.size()) {
                            cabecalhoCompleto.add("," + formatado);
                        }
                        else {
                            cabecalhoCompleto.add(formatado);
                        }

                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        if (0 != cabecalhoCompleto.size()) {
                            cabecalhoCompleto.add(",Integer/");
                        }
                        else {
                            cabecalhoCompleto.add("Integer/");
                        }
                    }

                    break;
                case "Double":
                    try {
                        boolean argumentoVazio = verificar[1].contains("?");

                        String numVerificado = "";
                        String formatado = "Double/";

                        if (verificar[1].split(":").length <= 2) {

                            String[] temporario = verificar[1].split(":");

                            for (String b : temporario) {

                                if (!"?".equals(b.trim())) {
                                    if (verificarCHARouINTEGER("num", b)) {
                                        numVerificado = b;
                                    }
                                    else {
                                        System.out.println("Erro 12520 dados incompativel");
                                        return null;
                                    }
                                }

                            }

                            if (argumentoVazio && !numVerificado.isEmpty()) {
                                formatado = ("Double/" + numVerificado + ":?");
                            }
                            else if (argumentoVazio) {
                                formatado = ("Double/?");
                            }
                            else if (!numVerificado.isEmpty()) {
                                formatado = ("Double/" + numVerificado);
                            }

                        }

                        if (0 != cabecalhoCompleto.size()) {
                            cabecalhoCompleto.add("," + formatado);
                        }
                        else {
                            cabecalhoCompleto.add(formatado);
                        }

                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        if (0 != cabecalhoCompleto.size()) {
                            cabecalhoCompleto.add(",Double/");
                        }
                        else {
                            cabecalhoCompleto.add("Double/");
                        }
                    }

                    break;
                case "Boolean":
                    try {
                        boolean argumentoVazio = verificar[1].contains("?");
                        String formatado = "Boolean/";

                        if (verificar[1].split(":").length <= 1) {
                            if (argumentoVazio) {
                                formatado = ("Boolean/?");
                            }
                        }

                        if (0 != cabecalhoCompleto.size()) {
                            cabecalhoCompleto.add("," + formatado);
                        }
                        else {
                            cabecalhoCompleto.add(formatado);
                        }

                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        if (0 != cabecalhoCompleto.size()) {
                            cabecalhoCompleto.add(",Boolean/");
                        }
                        else {
                            cabecalhoCompleto.add("Boolean/");
                        }
                    }

                    break;
                case "Char":
                    try {
                        boolean argumentoVazio = verificar[1].contains("?");
                        boolean letraDeForma = verificar[1].contains("^");
                        String formatado = "Char/";

                        if (verificar[1].split(":").length <= 2) {

                            if (argumentoVazio && letraDeForma) {
                                formatado = ("Char/?:^");
                            }
                            else if (argumentoVazio) {
                                formatado = ("Char/?");
                            }
                            else if (letraDeForma) {
                                formatado = ("Char/^");

                            }

                        }

                        if (0 != cabecalhoCompleto.size()) {
                            cabecalhoCompleto.add("," + formatado);
                        }
                        else {
                            cabecalhoCompleto.add(formatado);
                        }

                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        if (0 != cabecalhoCompleto.size()) {
                            cabecalhoCompleto.add(",Char/");
                        }
                        else {
                            cabecalhoCompleto.add("Char/");
                        }
                    }

                    break;
            }

        }

        return cabecalhoCompleto;

    }

    private static boolean verificarCHARouINTEGER(String tipo, String caracter)
    {
        String numeros = "0123456789.";
        String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        if ("num".equals(tipo)) {
            boolean temp = true;
            for (int j = 0; j < caracter.length(); j++) {
                if (!numeros.contains("" + caracter.charAt(j))) {
                    temp = false;
                }
            }

            return temp;
        }
        else if ("int".equals(tipo)) {
            return caracteres.contains(caracter);
        }
        return false;
    }

    /**
     * Funcao Para Editar o cabecalho
     *
     * @param CabecalhoEscrever Este parametro de obdecer ex:(^ = ,) List[nome,id ^ (String/*,Integer) ^ [code=pt]]
     * @return Se foi ou nao escrito.
     */
    public Boolean escreverCabechalho(List<String> CabecalhoEscrever)
    {
        String caminhoDoCabecalho = arqComPastaXDB.toString() + "/.cabecalho";
        String caminhoDoConfig = arqComPastaXDB.toString() + "/.config";

        System.out.println(caminhoDoCabecalho);

        if (new File(caminhoDoCabecalho).exists() && new File(caminhoDoConfig).exists()) {

            if (verificarOCabecalho(CabecalhoEscrever) != null) {

                for (String a : CabecalhoEscrever) {
                    if (CabecalhoEscrever.get(0).equals(a)) {
                        ManipularConteudo.escreverNoArquivo(a, new File(arqComPastaXDB + "/.cabecalho"), Boolean.FALSE);
                    }
                    else {
                        ManipularConteudo.escreverNoArquivo(a, new File(arqComPastaXDB + "/.cabecalho"), Boolean.TRUE);
                    }
                }
            }

            else {
                System.out.println("Erro 12506 : erro na lista de cabecalho");
            }

        }
        else {
            System.out.println("Erro 12507 : nao encontrado o arquivo .cabecalho ou .config");
        }
        return null;
    }

}
