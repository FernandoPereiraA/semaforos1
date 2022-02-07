import java.util.concurrent.Semaphore;


public class Escritor extends Thread 
{
	private BandejaEntrada miBandeja;
	private Semaphore SemEscritor;
	private Semaphore mutex;
	
	public Escritor(String nombre, BandejaEntrada miBandeja, Semaphore mutex)
	{
		super(nombre);
		this.miBandeja=miBandeja;
		this.SemEscritor=SemEscritor;
		this.mutex=mutex;
	}
	
	public void run()
	{
		try 
		{
			
				//hasta 500 msg de espera
				sleep((int)(Math.random()*300));
				//busca un permiso de su semaforo
				//SemEscritor.acquire();
				//intentqa entrar en la seccion critica
				mutex.acquire();
				//añade el mensaje
				miBandeja.anadirCorreo();
				//libera la seccion crituca
				mutex.release();
				//abre su semaforo para poder leer en la proxima
				//iteraccion del bucle
				//SemEscritor.release();
			
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
