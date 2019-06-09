package funcoes.para_conteudo;

import funcoes.para_arquivos.VerificarOuAtualizar;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class ManipularConteudo
{

    public static void escreverNoArquivo(String Texto, File arq, Boolean adicionar)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arq, adicionar))) {
            bw.append(Texto + String.format("%n"));
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

    public static void escreverNoArquivo(String Texto, File Arq, int Posicao)
    {
        if (Posicao != 0) {

            Map<Integer, String> textoKM = new TreeMap<>(lerNoArquivo(Arq));
            textoKM.put(Posicao, Texto);

            if (VerificarOuAtualizar.arquivoExiste(Arq.getPath())) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(Arq, false))) {
                    for (int a = 0; a < textoKM.size(); a++) {
                        bw.append(textoKM.get(a + 1) + String.format("%n"));
                    }
                }
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                //mensagem de erro
            }

        }
        else {
            // para o int posisao = 0
        }
    }

    public static String lerNoArquivo(File arq, int linha)
    {
        if (VerificarOuAtualizar.arquivoExiste(arq.getPath())) {

            try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
                String texto = br.readLine();
                int contador = 1;

                while (texto != null) {
                    if (contador == linha) {
                        return texto;
                    }
                    else {
                        texto = br.readLine();
                        contador++;
                    }
                }
            }
            catch (IOException e) {
                System.out.println("Erro 12507: " + e.getMessage());
            }
        }
        return null;
    }

    public static Map<Integer, String> lerNoArquivo(File arq)
    {
        if (VerificarOuAtualizar.arquivoExiste(arq.getPath())) {

            Map<Integer, String> textoKM = new TreeMap<>();

            try (BufferedReader br = new BufferedReader(new FileReader(arq))) {

                String texto = br.readLine();

                int contador = 1;

                while (texto != null) {
                    textoKM.put(contador, texto);
                    contador++;
                    texto = br.readLine();

                }
                return textoKM;
            }
            catch (IOException e) {
                System.out.println("Erro 12507: " + e.getMessage());
            }
        }
        else {
            // codigo de erro personalizar
        }
        return null;
    }
}
