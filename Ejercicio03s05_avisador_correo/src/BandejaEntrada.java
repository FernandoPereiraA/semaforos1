
public class BandejaEntrada implements Bandeja 
{
	
	private int correoNoLeido=0;
	private int correoLeido=0;
	
	
	@Override
	//metodo que lee los mensajes. Suma los que haya a correoleido
	//ye inicializa a 0 los no leidos
	public void leerBandeja(String lector) 
	{
		System.out.println(lector+" leyó "+correoNoLeido+" correos.");
		correoLeido+=correoNoLeido;
		correoNoLeido=0;
	}

	@Override
	//metodo usado por el escritor para poner un mensaje en el buzon
	public void anadirCorreo() 
	{
		correoNoLeido++;
	}

	@Override
	//el avisador comprueba con este metodo si hay correo
	public int comprobarCorreo() 
	{
		
			if(correoNoLeido>0)
			{
				System.out.println("Tienes "+correoNoLeido+" correos sin leer!");
			}

		
		return correoNoLeido;
	}
	
	@Override
	//getter que devuelve el total de mensajes leidos para poder 
	//terminar los bucles de los hilos.
	public int CorreoLeido() 
	{
		return correoLeido;
	}
}
