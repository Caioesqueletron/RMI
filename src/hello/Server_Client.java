package hello;

import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server_Client extends Remote {
	    boolean openFile(String fileName) throws RemoteException, FileNotFoundException;
	    boolean closeFile() throws RemoteException;
	    String nextLine() throws RemoteException;
	    
}

