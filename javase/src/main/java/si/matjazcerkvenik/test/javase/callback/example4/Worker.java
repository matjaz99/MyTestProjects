package si.matjazcerkvenik.test.javase.callback.example4;

public class Worker {
	
	// Worker gets a handle to the boss object via the Callable interface.  
    // There's no way this worker class can call any other method other than  
    // the one in Callable.  
    public void doSomeWork(Callable myBoss)  
    {  
        myBoss.callBackMethod();  
        // ERROR!  
        //myBoss.directMethod();  
    } 
	
}
