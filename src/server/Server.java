package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import hello.Server_Client;

public class Server implements Server_Client {

	String fileName;
	
	BufferedReader mrf;
	
	File file;

	public Server() {
	}

	public boolean openFile(String fileName) throws FileNotFoundException {

		this.file = new File("./file/" + fileName);

		if (this.file.exists() && file.canRead()) {
			this.fileName = fileName;
			mrf = new BufferedReader(new FileReader(this.file));
			return true;
		}

		return false;

	}

	public boolean closeFile(){
	
		try {
			mrf.close();

		}catch(IOException e) {
			System.out.println("Arquivo não fechado com sucesso");
			return false;
		}
		
		return true;
	}

	public String nextLine() {
		try {
			String linha = "";
			if (file.canRead() && file.isFile()) {
				linha = mrf.readLine();
					return linha;
		

			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		return null;
	}

	public static void main(String args[]) {

		try {
			Server obj = new Server();
			Server_Client stub = (Server_Client) UnicastRemoteObject.exportObject(obj, 0);

			// Bind the remote object's stub in the registry
			LocateRegistry.createRegistry(80);
			Registry registry = LocateRegistry.getRegistry(80);
			registry.rebind("Server_Client", stub);

			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
