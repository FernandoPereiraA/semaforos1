import java.util.concurrent.Semaphore;

//avisador de correo actua cada segundo y avisa al lector 
//si hay correos
public class Avisador extends Thread 
{
	private BandejaEntrada miBandeja;
	private Semaphore SemUsuario, SemEscritor, mutex;
	
	public Avisador(String nombre, BandejaEntrada miBandeja, Semaphore SemUsuario, Semaphore mutex)
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
            //para 50 mensajes leidos
			while(miBandeja.CorreoLeido()!=50)
			{
				//duerme un segundo
				sleep(1000);
				//intenta entrar en la seccion critica
				mutex.acquire();
				//si hay correo despierta al usuario para que lea
				if(miBandeja.comprobarCorreo()>0) SemUsuario.release();
				//libera la seccion crituca
				mutex.release();
			}
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
