package funcoes.para_arquivos;

import java.io.File;

public class VerificarOuAtualizar
{
	public static Boolean arquivoExiste(String caminhoCompleto)
	{
		File arq = new File(caminhoCompleto);
		return arq.exists();

	}

	public static Boolean arquivoEscreviviel(String caminhoCompleto, char ativarOUdesativar)
	{
		File arq = new File(caminhoCompleto);
		switch (ativarOUdesativar)
		{
			case 'a':
				arq.setWritable(true);
				break;
			case 'd':
				arq.setWritable(false);
				break;
		}
		return arq.canWrite();
	}

	public static Boolean arquivoLido(String caminhoCompleto, char ativarOUdesativar)
	{
		File arq = new File(caminhoCompleto);
		switch (ativarOUdesativar)
		{
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
