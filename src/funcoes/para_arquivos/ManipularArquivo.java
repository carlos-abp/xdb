package funcoes.para_arquivos;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author carlos-abp
 */
public class ManipularArquivo
{

    private String[] pastasEArq;

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
     * @param nomeDoArquivoOuPasta nome e estencao ex: texto.txt
     */
    public static Boolean excluirArquivos(String caminhoPai, String nomeDoArquivoOuPasta)
    {
        File arq = new File(caminhoPai + "/" + nomeDoArquivoOuPasta);
        String[] filhos = arq.list();
        Map<Integer, String> arvoresDePastas = new TreeMap<>();

        if (!arq.exists()) {
            System.out.println("Erro 12510 : arqivo ou pasta inexistente");
        }
        else {
            if (arq.isFile()) {
                arq.delete();
            }
            else {
                for (int i = 0; i < filhos.length; i++) {
                    File pastaTemporaria = new File(arq + "/" + filhos[i]);
                    if (pastaTemporaria.isFile()) {
                        System.out.println("del: " + pastaTemporaria.getPath());
                        pastaTemporaria.delete();
                    }
                    else {

                        String[] temporario = pastaTemporaria.list();

                        if (temporario.length == 0) {
                            System.out.println("del Pasta " + pastaTemporaria);

                            pastaTemporaria.delete();
                        }
                        else {
                            arvoresDePastas.put(i, pastaTemporaria.getPath());
                            System.out.println(i + " >> " + pastaTemporaria.getPath());
                        }

                    }

                }

                for (Integer a : arvoresDePastas.keySet()) {
                    System.out.println("map " + a + " <>" + arvoresDePastas.get(a));
                    try {
                        System.out.println("---------------------");
                        excluirArquivos(arvoresDePastas.get(a));
                    }
                    catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println(arq.delete());

            }

        }

        return false;
    }

    private static void excluirArquivos(String caminhoPai)
    {
        File arq = new File(new File(caminhoPai).getParent() + "/" + new File(caminhoPai).getName());
        System.out.println(arq);
        String[] filhos = arq.list();
        Map<Integer, String> arvoresDePastas = new TreeMap<>();

        for (int i = 0; i < filhos.length; i++) {
            File pastaTemporaria = new File(arq + "/" + filhos[i]);

            if (pastaTemporaria.isFile()) {
                System.out.println("del: " + pastaTemporaria.getPath());
                pastaTemporaria.delete();
            }
            else {

                String[] temporario = pastaTemporaria.list();

                if (temporario.length == 0) {
                    System.out.println("del: " + pastaTemporaria.getPath());
                    pastaTemporaria.delete();
                }
                else {
                    arvoresDePastas.put(i, pastaTemporaria.getPath());
                }

            }

        }

        for (Integer a : arvoresDePastas.keySet()) {
            System.out.println(arvoresDePastas.get(a));
            try {
                System.out.println(new File(arvoresDePastas.get(a)).getParent() + new File(arvoresDePastas.get(a)).getName());
                excluirArquivos(new File(arvoresDePastas.get(a)).getParent(), new File(arvoresDePastas.get(a)).getName());

            }
            catch (NullPointerException e) {

            }
        }
        System.out.println(arq.delete());

    }

}
