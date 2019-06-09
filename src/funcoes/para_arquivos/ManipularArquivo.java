package funcoes.para_arquivos;

import java.io.File;
import java.io.IOException;

/**
 * @author carlos-abp
 */
public class ManipularArquivo
{

    public static void criarPasta(String caminhoPai)
    {
        File arq = new File(caminhoPai);
        arq.mkdirs();
    }

    /**
     *
     * @param caminhoPai caminho ate a pasta do arq ex: /home/carlos-abp/NetBeansProjects/XDB/config
     * @param nomeDoArquivo nome e estencao ex: texto.txt
     */
    public static void criarArquivo(String caminhoPai, String nomeDoArquivo)
    {
        File arq = new File(caminhoPai + nomeDoArquivo);
        try {
            if (!arq.exists()) {
                new File(caminhoPai).mkdirs();
            }
            arq.createNewFile();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param caminhoPai caminho ate a pasta do arq ex: /home/carlos-abp/NetBeansProjects/XDB/config
     * @param nomeDoArquivo nome e estencao ex: texto.txt
     */
    public static void apagarArquivo(String caminhoPai, String nomeDoArquivo)
    {
        File arq = new File(caminhoPai + nomeDoArquivo);
        arq.deleteOnExit();

    }
}
