import java.util.concurrent.Semaphore;


public class Usuario extends Thread 
{
	private BandejaEntrada miBandeja;
	private Semaphore SemUsuario, SemEscritor, mutex;
	
	public Usuario(String nombre, BandejaEntrada miBandeja, Semaphore SemUsuario, Semaphore mutex)
	{
		super(nombre);
		this.miBandeja=miBandeja;
		this.SemUsuario=SemUsuario;
		//this.SemEscritor=SemEscritor;
		this.mutex=mutex;
	}
	
	public void run()
	{
		try 
		{
			while(miBandeja.CorreoLeido()!=50)
			{
				//espera a que el avisador de permiso al semaforo 
				//para leer el correo
				SemUsuario.acquire();
				//cierra el semaforo del escritor para que no
				//puedan llegar mensajes
				//SemEscritor.acquire();
				//intenta entrar en la seccion critica
				mutex.acquire();
				//lee los mensajes
				miBandeja.leerBandeja(getName());
				//suelta la seccion critica
				mutex.release();
				//permikte volver al escritor a poner mensajes en el 
				//buzon
				//SemEscritor.release();
				
			}
			
			System.out.println("Proceso Terminado.");
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
