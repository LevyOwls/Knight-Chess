package clases;

import java.util.ArrayList;
import java.util.Random;

public class NewMov {

	private Tablero t = new Tablero();
	private ArrayList movPosibles = new ArrayList();
	private ArrayList libre = new ArrayList(); //SE INICIALIZA PARA CADA CABALLO
	private ArrayList opciones = new ArrayList(); //TIENE TODAS LAS OPCIONES LIBRES
	public NewMov(Tablero tablero)
	{
		t = tablero;
		ArrayList enemy = tablero.getEnemyKnight();
		ArrayList ally = tablero.getAllyKnight();
		
		ally = desordenar(ally);
		for(int i=0 ; i < ally.size(); i++)
		{

			Knight k_actual = (Knight)ally.get(i);
			ArrayList movPosibles = new ArrayList();
			movPosibles = k_actual.posibleMoves();
			if(ocupaEnemigo(k_actual,ally,enemy,movPosibles) == 0)
			{
				break;
			}
			if(ocupaEnemigo(k_actual,ally,enemy,movPosibles) == -1)
			{
				ArrayList opcionk = new ArrayList();
				opcionk = libre;
				Opcion o = new Opcion(k_actual,opcionk, 0);
				opciones.add((Opcion)o); //YA COMPROBË QUE NO ESTA VACIO EL ARRAY DE OPCIONES
			}
		}
		
		int j=0;
		Opcion aux = (Opcion)opciones.get(j);
		while (aux.getAlternativas().equals(null))
		{
			j++;
			aux = (Opcion)opciones.get(j);
		}
		output((Opcion)opciones.get(j));
		
	}
	
	public ArrayList desordenar(ArrayList a)
	{
		ArrayList nuevo = new ArrayList();
		int n = a.size();
		Random r = new Random();
		
		for (int i=0; i<n; i++) {
            int posAleatoria = r.nextInt(a.size());
            nuevo.add(a.get(posAleatoria));
            a.remove(posAleatoria);
		}
		return nuevo;
	}
	public boolean estaEnTablero(ArrayList pos)
	{
		boolean esta = false;
		
		if((Integer)pos.get(0) >= 0 && (Integer)pos.get(0)<=7)
		{
			if((Integer)pos.get(1) >= 0 && (Integer)pos.get(1)<=7)
			{
				esta = true;
			}
		}
		
		return esta;
	}
	/**
	 * RETORNA 0 si ocupa enemigo
	 * RETORNA 1 si ocupa aliado
	 * RETORNA -1 si no ocupa nadie
	 */
	public int ocupaEnemigo(Knight k, ArrayList aliados, ArrayList enemigos, ArrayList p)
	{
		libre.clear();
		int ocupa = -1;
		ArrayList alternativa = new ArrayList();
		for (int i = 0 ; i < p.size() ; i++)
		{
			alternativa = (ArrayList)p.get(i);
			if(enemigos.contains((ArrayList)alternativa))
			{
				ocupa = 0;
				Opcion o = new Opcion(k, alternativa,0);
				output(o);
				break;
			}
			else
			{
				if(aliados.contains((ArrayList)alternativa))
				{
					ocupa = 1;
					continue;
				}
				else
				{
					if(estaEnTablero(alternativa))
					{
						libre.add(alternativa);
					}
					
				}
			}
			
		}
		return ocupa;
	}
	
	
	public int getKm(Knight k, ArrayList alternativas)
	{
		ArrayList aux = new ArrayList();
		int x = k.getX(),y = k.getY();
		int mov = 9;
		
		alternativas = desordenar(alternativas);
		aux = (ArrayList)alternativas.get(0);
		if((Integer)aux.get(0)+1 == x)
		{
			if((Integer)aux.get(1)+2 == y)
			{
				mov = 3;
			}
			else
			{
				mov = 0;
			}
		}
		if((Integer)aux.get(0)+2 == x)
		{
			if((Integer)aux.get(1)+1 == y)
			{
				mov = 2;
			}
			else
			{
				mov = 1;
			}
		}
		if((Integer)aux.get(0)-1 == x)
		{
			if((Integer)aux.get(1)+2 == y)
			{
				mov = 4;
			}
			else
			{
				mov = 7;
			}
		}
		if((Integer)aux.get(0)-2 == x)
		{
			if((Integer)aux.get(1)+1 == y)
			{
				mov = 5;
			}
			else
			{
				mov = 6;
			}
		}

		//aleatorio.setSeed(System.currentTimeMillis());
		return mov;
	}
	
	public void output(Opcion o)//int id,int km)
	{
		
		int id = o.getK().getId();
		int km = getKm(o.getK(), o.getAlternativas());
		
		
		String op="{\r\n" + 
				"    \"knight_id\": "+id+",\r\n" + 
				"    \"knight_movement\": "+km+"\r\n" + 
				"}\r\n" + 
				"";
		System.out.println(op);
	}
}
