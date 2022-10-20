package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import hello.Server_Client;

public class Client {

    private Client() {}

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try {
            Registry registry = LocateRegistry.getRegistry(80);
            Server_Client stub = (Server_Client) registry.lookup("Server_Client");
            System.out.println("Digite o nome do arquivo que quer abrir: ");
            String fileName = sc.next();
            String line = "";
            boolean response = stub.openFile(fileName);
            if(response) {
            	System.out.println("Arquivo aberto com sucesso");
            	do{
                	
                	line = stub.nextLine();
                	if(line != null) {
                    	System.out.println(line);

                	}
                }while(line != null);
                
                boolean closed = stub.closeFile();
                if(closed) {
                	System.out.println("Arquivo fechado com sucesso");
                }else {
                	System.out.println("Erro ao fechar o arquivo");
                }
            }else {
            	System.out.println("Arquivo não encontrado ou erro ao abrir o arquivo");
            }
         
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        
        sc.close();
    }
}
