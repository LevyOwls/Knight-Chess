package clases;

import java.util.ArrayList;

public class Tablero 
{

	private Celda[][] tablero=new Celda[8][8];
	
	
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
}
