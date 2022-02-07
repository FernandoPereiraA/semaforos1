import java.util.concurrent.Semaphore;


public class GestorCorreo 
{
	public static void main(String[] args) 
	{
		//siempre pueden llegar mensajes. semaforo escritor a 1
		//Semaphore SemEscritor=new Semaphore(1);
		//lector con semaforo cerrado. Espera que lo avisen
		Semaphore SemUsuario=new Semaphore(0);
		//semaforo exclusion mutua
		Semaphore mutex=new Semaphore(1);
		//seccion critica
		BandejaEntrada miBandeja=new BandejaEntrada();
		//creacion hilos
		Usuario miUsuario=new Usuario("Usuario", miBandeja, SemUsuario,  mutex);
		Avisador miAvisador=new Avisador("Avisador", miBandeja, SemUsuario,  mutex);
		miAvisador.start();
		miUsuario.start();
		for(int i=0;i<50;i++) {
			//se duerme 50 como mucho
			try {
				Thread.sleep((int)(Math.random()*300));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}	
		Escritor miEscritor=new Escritor("Escritor"+i, miBandeja,mutex);
		miEscritor.start();
		}
		//arrancamos hilos
		//miEscritor.start();
		
	}
}
