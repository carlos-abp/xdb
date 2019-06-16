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
        if(arq.exists())
        	return true;
        else {
    		System.out.println("Erro 12542 : nao foi possivel encontrar o arquivo");
    		return false;
    	}
    }

    /**
     *Metodo para altera a permicao de escrita do arquivo
     * @param caminhoCompleto caminho completo ex: /home/carlos-abp/PROJETOS/Libs/teste.txt
     * @param ativarOUdesativar 
     * @return se ele e te permicao para escrever ou nao
     */
    public static Boolean arquivoEscreviviel(String caminhoCompleto,Boolean ativarOUdesativar)
    {
        File arq = new File(caminhoCompleto);
        
        if(ativarOUdesativar) 
                arq.setWritable(true);
                
        else
                arq.setWritable(false);
                
        return arq.canWrite();
    }

    /**
     *Metodo para altera a permicao de leitura do arquivo
     * @param caminhoCompleto caminho completo ex: /home/carlos-abp/PROJETOS/Libs/teste.txt
     * @param ativarOUdesativar altera a permicao do arquivo se ele pode ser lido ou nao
     * @return permicao do arquivo se ele pode se lido ou nao
     */
    public static Boolean arquivoLido(String caminhoCompleto, Boolean ativarOUdesativar)
    {
        File arq = new File(caminhoCompleto);
        
        if(ativarOUdesativar) 
            arq.setReadable(true);
            
        else
            arq.setReadable(false);
            
    
        return arq.canRead();
    }

}