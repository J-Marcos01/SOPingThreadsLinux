package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ThreadPing extends Thread
{
	private int n;

	public ThreadPing(int n) {
		this.n = n;

	}

	public void run() {
		testeping();
	}

	private void testeping() {

		String os = System.getProperty("os.name");
		if (os.contains("Linux")) {
			String proc = "";
			String h = "";
			switch (n) {
			case 1: {
				proc = "ping -4 -c 10 www.uol.com.br";
				h = "UOL";
				break;
			}
			case 2: {
				proc = "ping -4 -c 10 www.terra.com.br";
				h = "TERRA";
				break;
			}
			case 3: {
				proc = "ping -4 -c 10 www.google.com.br";
				h = "GOOGLE";
				break;
			}
			}
			String[] procArr = proc.split(" ");
			try {
				Process p = Runtime.getRuntime().exec(procArr);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha != null) {
					if (linha.contains("seq")) {
						String[] arrLinha = linha.split(" ");
						System.out.println(h + "\n" + arrLinha[7] + " " + arrLinha[8]);
					}
					else if(linha.contains("rtt"))
					{
						String[]arrLinha=linha.split("/");
						System.out.println(h+"\n tempo médio :"+arrLinha[6]);
					}
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (Exception e) {
				String msg = e.getMessage();
				System.err.println(msg);

			}

		} else {
			System.err.println("Sistema não suportado");
		}

	}
}
