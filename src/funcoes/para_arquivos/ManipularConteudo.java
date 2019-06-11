package funcoes.para_arquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author carlos-abp classe responsavel pela manipulacao de conteudo intriseco ao arq.
 */
public class ManipularConteudo
{

    /**
     *
     * @param Texto texto a introduzir no arquivo.
     * @param arq arquiva para a inscrisao.
     * @param adicionar se deseja acresentar ao texto original ou nao.
     */
    public static void escreverNoArquivo(String Texto, File arq, Boolean adicionar)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arq, adicionar))) {
            bw.append(Texto + String.format("%n"));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param Arq diretori completo
     */
    public static void limparOArquivo(File Arq)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Arq))) {
            bw.append("");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * metodo que realiza a introducao de texto em uma linha bem expecifica
     *
     * @param Texto texto a escrever na linha. para posicao = 0 *nao feito
     * @param Arq arquiva para a inscrisao.
     * @param Posicao a linha que deseja reeditar.
     */
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

    /**
     *
     * @param arq arquiva para a inscrisao.
     * @param linha linha que deseja ler
     * @return A leitura do arquivo
     */
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

    /**
     *
     * @param arq arquiva para a inscrisao.
     * @return linha todas mapeada devido o retono ser map e provem uma manipulacao mais facil do texto
     */
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
                System.out.println("Erro 12520: " + e.getMessage());
            }
        }
        else {
            // codigo de erro personalizar
        }
        return null;
    }
}
