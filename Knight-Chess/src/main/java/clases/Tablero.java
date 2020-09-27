package clases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Tablero 
{

	private Celda[][] tablero=new Celda[8][8];
	private ArrayList myk=new ArrayList();
	
	public Tablero()
	{
		int i,j;
		
		//SE INICIALIZAN LAS CELDAS DEL TABLERO
		Celda temp;
		for (i=0;i<tablero.length;i++)
		{
			for (j=0;j<tablero.length;j++)
			{
				tablero[i][j]=new Celda(i,j);
			}
		}
	}
	

	public Celda getCelda(int x,int y)
	{
		return tablero[x][y];
	}
	public void setCelda(Celda c,int x,int y)
	{
		tablero[x][y]=c;
	}
	
	
	public void showTime()
	{
		int i,j;
		Celda temp;
		Knight k;
		for (i=0;i<8;i++)
		{
			for (j=0;j<8;j++)
			{
				temp=getCelda(i, j);
				k=temp.getk();
				if (k!=null)
				{
					if (k.isEnemy())
					{
						System.out.print(" | E");
					}
					else
					{
						System.out.print(" | A");
					}
				}
				else
				{
					System.out.print(" |  ");
				}
			}
			
			System.out.println();
		}
	}
	/**
	 * Marca todos los movimientos de un knigth
	 * @param k
	 */
	public void move()
	{
		myk.clear();
		int i,j;
		//CELDA QUE RECORRE
		Celda temp;
		//CELDA AUXILIAR DE LOS MOVS
		Celda aux;
		//KNIGTH
		Knight k;
		//LISTA DE MOVIMIENTOS POSIBLES DE K
		ArrayList movs;
		
		for (i=0;i<8;i++)
		{
			for (j=0;j<8;j++)
			{
				temp=getCelda(i, j);
				k=temp.getk();
				
				if (k!=null)
				{
					if (k.isEnemy())
					{
						//EN CASO QUE SEA ENEMIGO MARCAR TODOS LOS LUGARES POSIBLES DONDE PUEDE ATACAR
						movs=k.posibleMoves();
						updateEnemy(movs);
					}
					else
					{
						movs=k.posibleMoves();
						updateAlly(movs);
						myk.add(k);
					}
				}
			}
		}
	}
	
	public void updateEnemy(ArrayList movs)
	{
		ArrayList aux;
		Celda temp;
		//int x,y;
		int x,y;
		for (int i=0;i<movs.size();i++)
		{
			aux=(ArrayList)movs.get(i);
			aux=recover(aux);
			x=(Integer) aux.get(0);
			y=(Integer) aux.get(1);
			temp=getCelda(x,y);
			temp.setEnemyRange(true);
		}
	}
	public void updateAlly(ArrayList movs)
	{
		ArrayList aux;
		Celda temp;
		int x,y;
		for (int i=0;i<movs.size();i++)
		{
			aux=(ArrayList)movs.get(i);
			aux=recover(aux);
			x=(Integer) aux.get(0);
			y=(Integer) aux.get(1);
			temp=getCelda(x,y);
			temp.setAllyRange(true);
		}
	}
	
	public ArrayList recover(ArrayList l)
	{
		return l;
	}
	
	public Tablero copyTablero()
	{
		Tablero nuevo=new Tablero();
		int i,j;
		Celda temp;
		for (i=0;i<8;i++)
		{
			for (j=0;j<8;j++)
			{
				temp=tablero[i][j];
				nuevo.setCelda(temp.copyCelda(), i, j);
			}
		}
		nuevo.move();
		return nuevo;
	}
	
	public void simulador()
	{
		ArrayList simulaciones=new ArrayList();
		ArrayList score=new ArrayList();
		int i,j;
		Knight k;
		ArrayList posible,aux;
		
		int x,y;
		for (i=0;i<myk.size();i++)
		{
			k=(Knight)myk.get(i);
			posible=k.posibleMoves();
			
			for (j=0;j<posible.size();j++)
			{
				aux=(ArrayList)posible.get(j); 
				//j=0 x
				//j=1 y
				Tablero copia=copyTablero();
				//SE SIMULA
				x=(Integer)aux.get(0);
				y=(Integer)aux.get(1);
				Eval nuevo=new Eval(copia,k.getId(),x,y,k.getX(),k.getY());
				if (nuevo.turn())
				{
					simulaciones.add(nuevo);
					score.add(nuevo.getScore());
				}
				
			}
		}
		
		simulaciones=bubbleSort(score, simulaciones);
	
		//BORA LAS CON PUNTAJE ÑEH
		while (simulaciones.size()>=15)
		{
			simulaciones.remove(simulaciones.size()-1);
		}
		
		int random=random(simulaciones.size());
		
		//SE OBTIENE AL GANADOR
		Collections.shuffle(simulaciones);
		
		Eval select=(Eval)simulaciones.get(random);
		k=search(select.getK());
		
		output(k.getId(), select.getFX(),select.getFY());
		
	}
	
	public Knight search(int id)
	{
		Knight k=null;
		for (int i=0;i<myk.size();i++)
		{
			k=(Knight)myk.get(i);
			if (k.getId()==id)
			{
				return k;
			}
		}
		return k;
	}
	
	/**
	 * 
	 * @param cotaSuperior					VARIABLE EXCLUYENNTE, CONSIDERANDO EL DOMINIO DE LOS POSIBLES NUMEROS RANDOM EN [0 ,(N-1)]
	 * @return
	 */
	public  int random(int cotaSuperior)
	{
		int h= (int) Math.floor(Math.random()*cotaSuperior);
		return h;
	}
	
	/**
	 * MEDIANTE BUUBLESORT ORDENA COLUMNAS POR TAMAnO DE MENOR A MAYOR
	 * 
	 * @param size			ARRAYLIST CON TAMAnO DE CADA COLUMNA
	 * @param columna		ARRAYLIST CON EL NUMERO DE COLUMNA
	 * @return				ARRAYLIST CON NUMERO DE COLUMNAS ORDENADA DE MENOR A MAYOR
	 */
	public  ArrayList bubbleSort(ArrayList score,ArrayList simulaciones)
	{
		 boolean sorted=false;
		 int i;
		 Object aux;
		 while (!sorted)
		 {
			 sorted = true;
			 for (i=0;i<score.size()-1;i++)
			 {
				 //SI I>I+1 CAMBIAN DE POSICION
				 if ((Double)score.get(i)>(Double)score.get(i+1))
				 {
					 //SE ORDENA POR TAMAnO
					 aux=(Double)score.get(i);
					 score.set(i,score.get(i + 1));
					 score.set(i + 1,aux);
					 //SE ORDENA POR COLUMNA
					 aux=(Object)simulaciones.get(i);
					 simulaciones.set(i,simulaciones.get(i + 1));
					 simulaciones.set(i + 1,aux);
					 
					 sorted=false;
				 }
			 }
		 }
		 
		 return simulaciones;
	}
	
	
	public void output(int idk,int x,int y)
	{
		int mov=-1;
		if (x==1)
		{
			if (y==2)
			{
				mov=4;
			}
			else
			{
				mov=7;
			}
		}
		if(x==-1)
		{
			if (y==2)
			{
				mov=2;
				
				
				
				
			}
			else
			{
				mov=1;
				
				
			}
		}
		if (x==2)
		{
			if (y==1)
			{
				mov=3;
				
				
				
			}
			else
			{
				mov=6;
				
				
				
			}
		}
		if(x==-1)
		{
			if (y==1)
			{
				
				mov=5;
				
				
				
				
			}
			else
			{	
				mov=0;
				
				
				
			}
		}
		/******************************/
		if (mov==-1)
		{
			mov=random(8);
		}
		String s="{\"knight_id\": "+idk+",\"knight_movement\": "+mov+"}";
		System.out.println(s);
	}
	
	
	
}
