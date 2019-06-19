package funcoes.para_xdb;


import java.io.File;
import java.util.ArrayList;
import java.util.List;



import funcoes.para_arquivos.ManipularConteudo;


public class CRUD
{

	private ManipularXDB baseDeDados;
	private String atributosDasVariaveis;

	public CRUD(ManipularXDB bd)
	{

		File arqConfig = new File(bd.getCaminhoDoXDB() + "/" + bd.getNomeDoXDB() + "/.config");
		System.out.println(arqConfig);
		atributosDasVariaveis = ManipularConteudo.lerNoArquivo(arqConfig, 2);
		this.baseDeDados = bd;

	}

	// --------------------------------------------------------

	public Boolean criarDado(String oDados)
	{
		String[ ] valoresDeDados = oDados.split(",");
		String[ ] atributosDeCadaDado = atributosDasVariaveis.split(",");
		String[ ] tiposEArgumentos;
		List<String> Formatado = new ArrayList<>();

		if (valoresDeDados.length == atributosDeCadaDado.length)
		{
			for (int i = 0; i < valoresDeDados.length; i++)
			{

//variavel responsavel a montar os dados de cada case no switch abaixo; 
				String temp;

				tiposEArgumentos = atributosDeCadaDado[ i ].split("/");

				switch (tiposEArgumentos[ i ])
				{
					case "STRING":
						temp = "";
						try
						{
							if (tiposEArgumentos[ 1 ].split(":")[ 0 ] != "*" || tiposEArgumentos[ 1 ].split(":")[ 0 ] != ">"
								|| tiposEArgumentos[ 1 ].split(":")[ 0 ] != "<")
							{

								if (tiposEArgumentos[ 1 ].contains("*"))
								{
									if (valoresDeDados[ i ].length() <= Integer.parseInt(tiposEArgumentos[ 1 ].split(":")[ 0 ]))
									{
										if (valoresDeDados[ i ].length() != 0)
										{
											temp = valoresDeDados[ i ];
										}
										else
										{
											System.out.println("O " + tiposEArgumentos[ i ] + " nao aceita dado vazio ");
											return false;
										}
									}
									else
									{
										System.out.println("O " + tiposEArgumentos[ i ] + " nao aceita mais do que "
											+ tiposEArgumentos[ 1 ].split(":")[ 0 ] + " caracteres");
										return false;
									}
								}
								if (tiposEArgumentos[ 1 ].contains("<"))
								{

									temp = valoresDeDados[ i ].toLowerCase();

								}
								if (tiposEArgumentos[ 1 ].contains(">"))
								{

									temp = valoresDeDados[ i ].toUpperCase();
								}

							}
							else
							{
								if (tiposEArgumentos[ 1 ].contains("*"))
								{

									temp = valoresDeDados[ i ];

								}
								if (tiposEArgumentos[ 1 ].contains("<"))
								{

									temp = valoresDeDados[ i ].toLowerCase();

								}
								if (tiposEArgumentos[ 1 ].contains(">"))
								{

									temp = valoresDeDados[ i ].toUpperCase();
								}

							}
						}
						catch (Exception e)
						{
							temp = valoresDeDados[ i ];
						}

						Formatado.add(temp);
						System.out.println(Formatado);
						break;

					case "CHAR":

						if (valoresDeDados[ i ].length() <= 1 && DADOS.verificarCARACTERouINTEGER("char", tiposEArgumentos[ i ]) 
							)
						{
							temp = "";

							try
							{
								if (tiposEArgumentos[ 1 ].contains("*"))
								{
									if (valoresDeDados[ i ].length() == 1)
									{

										temp = valoresDeDados[ i ];

									}
									else
									{
										System.out.println("O " + tiposEArgumentos[ i ] + " nao pode ser vazio");
										return false;
									}
								}
								if (tiposEArgumentos[ 1 ].contains("<"))
								{

									temp = valoresDeDados[ i ].toLowerCase();

								}
								if (tiposEArgumentos[ 1 ].contains(">"))
								{

									temp = valoresDeDados[ i ].toUpperCase();
								}

							}
							catch (Exception e)
							{
								temp = valoresDeDados[ i ];
							}

							Formatado.add(temp);
							System.out.println(Formatado);
							break;
						}
						else
						{
							System.out.println("Tipo De Dados Invalido");
						}
						break;
					case "INTEGER":
						if (DADOS.verificarCARACTERouINTEGER("num", tiposEArgumentos[ i ]))
						{
							// gravar integer ou double implementar codigo
						}
						break;
					case "DOUBLE":
						if (DADOS.verificarCARACTERouINTEGER("num", tiposEArgumentos[ i ]))
						{
							// gravar integer ou double implementar codigo
						}
						break;
					case "BOOLEAN":

						break;

				}

			}

		}
		else
		{

		}
		return true;
	}
}
