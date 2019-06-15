package funcoes.para_arquivos;

import java.io.File;

/**
 *
 * @author carlos-abp
 */
public class VerificarOuAtualizar
{

    /**
     *
     * @param caminhoCompleto caminho ate a pasta do arq ex: /home/carlos-abp/NetBeansProjects/XDB/config
     * @return a existencia do arq
     */
    public static Boolean arquivoExiste(String caminhoCompleto)
    {
        File arq = new File(caminhoCompleto);
        return arq.exists();

    }

    /**
     *
     * @param caminhoCompleto caminho completo ex: /home/carlos-abp/NetBeansProjects/XDB/config/texto.txt
     * @param ativarOUdesativar altera a permicao do arquivo se ele pode ser escrito ou nao
     * @return se ele e te permicao para escrever ou nao
     */
    public static Boolean arquivoEscreviviel(String caminhoCompleto, char ativarOUdesativar)
    {
        File arq = new File(caminhoCompleto);
        switch (ativarOUdesativar) {
            case 'a':
                arq.setWritable(true);
                break;
            case 'd':
                arq.setWritable(false);
                break;
        }
        return arq.canWrite();
    }

    /**
     *
     * @param caminhoCompleto caminho completo ex: /home/carlos-abp/NetBeansProjects/XDB/config/texto.txt
     * @param ativarOUdesativar altera a permicao do arquivo se ele pode ser lido ou nao
     * @return permicao do arquivo se ele pode se lido ou nao
     */
    public static Boolean arquivoLido(String caminhoCompleto, char ativarOUdesativar)
    {
        File arq = new File(caminhoCompleto);
        switch (ativarOUdesativar) {
            case 'a':
                arq.setReadable(true);
                break;
            case 'd':
                arq.setReadable(false);
                break;
        }
        return arq.canRead();
    }

}