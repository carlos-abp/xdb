package funcoes.para_conteudo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ManipularConteudo
{

    public static void escreverNoArquivo(String texto, File arq, Boolean adicionar)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arq, adicionar))) {
            bw.append(texto + String.format("%n"));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void limparOArquivo(File Arq)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Arq))) {
            bw.append("");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void editarCabecalho(File Arq, List<String> CabecalhoEmString)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Arq))) {
            bw.append(CabecalhoEmString.get(0));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
