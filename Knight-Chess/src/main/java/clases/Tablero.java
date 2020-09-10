package clases;

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
}
