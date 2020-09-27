package clases;

import java.util.ArrayList;
import java.util.Random;

public class NewMovement {

	private Knight bestK;
	private int bestId;
	private ArrayList bestPos=new ArrayList();
	private Tablero t = new Tablero();
	private ArrayList possible = new ArrayList();
	private ArrayList allyesLess = new ArrayList();
	private ArrayList<Opcion> opciones = new ArrayList();
	private boolean sinSacrificio = true;
	public NewMovement(Tablero tablero)
	{
		t = tablero;
		ArrayList enemy = tablero.getEnemyKnight();
		ArrayList ally = tablero.getAllyKnight();
		
		for(int i = 0 ; i < ally.size() ; i++)
		{
			allyesLess.clear();
			Knight myKnight = (Knight)ally.get(i);
			ArrayList<ArrayList> nextMov = myKnight.posibleMoves();
			
			//VER SI EXISTE UN MOVIMIENTO QUE SE COMA UNA FICHA ENEMIGA, SI NO HAY
			//VER SI EXISTE UN MOVIMIENTO SEGURO, DONDE NO SE PIERDA NINGUNA FICHA Y AGREGAR COMO ALTERNATIVA
			if(haveEnemy(myKnight,enemy,nextMov,0))
			{
				ArrayList aux = bestPos;
				Opcion o = new Opcion(myKnight, bestPos, 2);
				deleteEnemy(myKnight, enemy);
				output(o);
				return;
			}
		/*}
		
		for(int i = 0; i < ally.size() ; i++)
		{
			allyesLess.clear();
			Knight myKnight = (Knight)ally.get(i);
			ArrayList<ArrayList> nextMov = myKnight.posibleMoves();
		*/
			/*ArrayList alternativas = safetyMovement(enemy,ally,nextMov);
			
			Opcion o = new Opcion(myKnight, alternativas, 0);
			opciones.add(o);
			
			if (o.getAlternativas().equals(null))//possible.isEmpty())
			{
				if(sacrifice(ally))
				{
					o = new Opcion(myKnight, bestPos, 1);
					opciones.add(o);

				}
			}*/
			Opcion o = new Opcion(myKnight, null, 0);
			ArrayList alternativas = safetyMovement(enemy,ally,nextMov);
			if(!alternativas.equals(null))
			{
				o = new Opcion(myKnight, alternativas, 0);
				opciones.add(o);
			}
			/*else
			{
				o = new Opcion(null,null,0);
				if(sacrifice(ally))
				{
					for(int k = 0 ; k < ally.size() ; k++)
					{
						Knight k1 = (Knight)ally.get(k);
						ArrayList pos = new ArrayList();
						pos.add(k1.getX());
						pos.add(k1.getY());
						if(!estaEnTablero(pos))
						{
							continue;
						}
					}
					ArrayList aux = bestPos;
					o = new Opcion(myKnight, aux, 1);
					opciones.add(o);
				}
			}*/
			//SI NO ESTA VACIA CREO UN RANDOM PARA ELEGIR ENTRE LOS POSIBLES MOVIMIENTOS
		}
		
		int intAleatorio;
		
		for(int m = 0 ; m < opciones.size() ; m++)
		{
			if(opciones.get(m).getTipo_solucion() == 1)
			{
				sinSacrificio = false;
			}
		}
		
		if(!sinSacrificio)
		{
			Random aleatorio = new Random(System.currentTimeMillis());
			intAleatorio = aleatorio.nextInt(opciones.size());
			//aleatorio.setSeed(System.currentTimeMillis());
			output(opciones.get(intAleatorio));
		}
		else
		{
			do
			{
				Random aleatorio = new Random(System.currentTimeMillis());
				intAleatorio = aleatorio.nextInt(opciones.size());
			}while(opciones.get(intAleatorio).getTipo_solucion() != 0);
			output(opciones.get(intAleatorio));
		}

				
				//output(opciones.get(intAleatorio));
				//aleatorio.setSeed(System.currentTimeMillis());

				//output(opciones.get(intAleatorio));
			//}
			
		

		
		

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
	
	public boolean sacrifice (ArrayList ally)
	{
		
		ArrayList futureEnemy = new ArrayList();
		
		for(int i = 0 ; i < allyesLess.size() ; i++)
		{
			futureEnemy = (ArrayList)allyesLess.get(i);
			for(int j = 0 ; j < ally.size() ; j++)
			{
				Knight k = (Knight)ally.get(j);
				if (k.getX() == (Integer)futureEnemy.get(0) && k.getY() == (Integer)futureEnemy.get(1))
				{
					bestPos.clear();
					bestPos.add(futureEnemy);
					return true;
				}
			}
		}
		return false;
	}
	
	//FUNCION QUE VE SI EL MOVIMIENTO NO GENERA RIESGOS
	//SIMULA EL SIGUIENTE NIVEL Y EVALUA SI HAY O NO HAY ENEMIGO EN ESTE PARA SABER SI EXISTE RIESGO O NO
	public ArrayList safetyMovement(ArrayList enemy,ArrayList ally, ArrayList movements)
	{
		ArrayList possible = new ArrayList();
		ArrayList nextMov = new ArrayList();
		for (int i = 0 ; i < movements.size() ; i++)
		{
			ArrayList pos = (ArrayList)movements.get(i);
			Knight auxK = auxKnight(pos);
			nextMov=auxK.posibleMoves();
			if(!haveEnemy(null,enemy,movements,1) || !haveEnemy(null,ally,movements,1))
			{

						possible.add(pos);
			}
			else
			{
				continue;
			}
		}
		return possible;
	}
	
	public Knight auxKnight(ArrayList pos)
	{
		Knight k = new Knight(0, (Integer)pos.get(0), (Integer)pos.get(1), true);
		
		return k;
	}
	//LA FUNCION EVALUA SI TIENE O NO TIENE UN ENEMIGO
	//SI LA FUNCION ES LLAMADA DESDE EL CONSTRUCTOR ELIMINA AL ENEMIGO
	//SINO, SOLO RETORNA QUE HAY UN ENEMIGO Y POR ENDE, EL CABALLO ALIADO ESTARÁ AMENAZADO
	public boolean haveEnemy (Knight myKnight,ArrayList enemy, ArrayList movements, int delete)
	{
		
		boolean have = false;
		allyesLess.clear();
		
		for(int i = 0 ; i < movements.size() ; i++)
		{
			ArrayList pos = (ArrayList)movements.get(i);
			
			for(int j = 0 ; j < enemy.size() ; j++)
			{
				Knight k = (Knight)enemy.get(j);
				if(k.getX() == (Integer)pos.get(0) && k.getY() == (Integer)pos.get(1))
				{
					if(delete == 0)
					{
						bestPos.clear();
						bestPos.add(pos);
						//Opcion o = new Opcion(myKnight,bestPos, 0);
						//output(o);
						deleteEnemy(k, enemy);
						//return true;
						have = true;
					}
					if(delete == 1)
					{
						//SI EL MOVIMIENTO ES DESVENTAJOSO SE GUARDA LA POSICION
						//LUEGO SE EVALUARA SI SE PODRA COMER EL CABALLO QUE SE COMERÁ NUESTRO CABALLO
						allyesLess.add(pos);
						have = true;
					}
					//if(delete == 2)
					//{
						//POSICION DONDE SE COMERAN UN CABALLO NUESTRO PERO NOS PODREMOS COMER EL SUYO
						
					//}
					
				}
			}
		}
		return have;
	}
	
	public void deleteEnemy(Knight myKnight , ArrayList enemy)
	{
		for(int i = 0 ; i < enemy.size() ; i++)
		{
			Knight e = (Knight)enemy.get(i);
			if (e.equals(myKnight))
			{
				enemy.remove(e);
				t.setEnemyKnight(enemy);
			}
		}
	}

	public int getKm(Knight k, ArrayList alternativas)
	{
		ArrayList aux = new ArrayList();
		int x = k.getX(),y = k.getY();
		int elegida;
		int mov = 9;
		Random aleatorio = new Random(System.currentTimeMillis());
		do {
		

		if(alternativas.size() > 1)
		{
				int intAleatorio = aleatorio.nextInt(alternativas.size());
				aux = (ArrayList)alternativas.get(intAleatorio);
		}
		else
		{
			aux = (ArrayList)alternativas.get(0);
		}
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
		}while(!estaEnTablero(aux));
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
