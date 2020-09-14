package clases;

import java.util.ArrayList;

public class Knight 
{
	private int id;
	private int x;
	private int y;
	private boolean alive;
	private boolean enemy;
	
	public Knight(int id, int x, int y, boolean enemy)
	{
		this.id=id;
		this.x=x;
		this.y=y;
		//AL GENERARSE TODOS COMIENZAN VIVOS, POR LO QUE NO TIENE SENTIDO COLOCARLOS DE OTRA FORMA
		this.alive=true;
		this.enemy=enemy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isEnemy() {
		return enemy;
	}

	public void setEnemy(boolean enemy) {
		this.enemy = enemy;
	}
	
	public ArrayList posibleMoves()
	{
		//PRIMERO SE GENERAN TODOS LOS POSIBLES MOVIMIENTOS QUE SON 8
		ArrayList posibles=new ArrayList();
		int i;
		for (i=0;i<8;i++)
		{
			//SE CREA UN ARRAYLIST TEMPORAL
			ArrayList temp=new ArrayList();
			//DEPENDIENDO EL CASO GENERAMOS LOS MOVS
			int auxX=x;
			int auxY=y;
			switch (i) 
			{
				case 0:
					//(X+2)(Y+1)
					auxX+=2;
					auxY+=1;
					//SI ESTA DENTRO DE [0,7] 
					if (-1<auxX && auxX<8 && -1<auxY && auxY<8)
					{
						temp.add(auxX);
						temp.add(auxY);
					}
					break;
						
				case 1:
					//(X+2)(Y-1)
					auxX+=2;
					auxY-=1;
					//SI ESTA DENTRO DE [0,7] 
					if (-1<auxX && auxX<8 && -1<auxY && auxY<8)
					{
						temp.add(auxX);
						temp.add(auxY);
					}
					break;
					
				case 2:
					//(X-2)(Y+1)
					auxX-=2;
					auxY+=1;
					//SI ESTA DENTRO DE [0,7] 
					if (-1<auxX && auxX<8 && -1<auxY && auxY<8)
					{
						temp.add(auxX);
						temp.add(auxY);
					}
					break;
					
				case 3:
					//(X-2)(Y-1)
					auxX-=2;
					auxY-=1;
					//SI ESTA DENTRO DE [0,7] 
					if (-1<auxX && auxX<8 && -1<auxY && auxY<8)
					{
						temp.add(auxX);
						temp.add(auxY);
					}
					break;
					
				case 4:
					//(X+1)(Y+2)
					auxX+=1;
					auxY+=2;
					//SI ESTA DENTRO DE [0,7] 
					if (-1<auxX && auxX<8 && -1<auxY && auxY<8)
					{
						temp.add(auxX);
						temp.add(auxY);
					}
					break;
					
				case 5:
					//(X+1)(Y-2)
					auxX+=1;
					auxY-=2;
					//SI ESTA DENTRO DE [0,7] 
					if (-1<auxX && auxX<8 && -1<auxY && auxY<8)
					{
						temp.add(auxX);
						temp.add(auxY);
					}
					break;
					
				case 6:
					//(X-1)(Y+2)
					auxX-=1;
					auxY+=2;
					//SI ESTA DENTRO DE [0,7] 
					if (-1<auxX && auxX<8 && -1<auxY && auxY<8)
					{
						temp.add(auxX);
						temp.add(auxY);
					}
					break;
					
				case 7:
					//(X-1)(Y-2)
					auxX-=1;
					auxY-=2;
					//SI ESTA DENTRO DE [0,7] 
					if (-1<auxX && auxX<8 && -1<auxY && auxY<8)
					{
						temp.add(auxX);
						temp.add(auxY);
					}
					break;
		
			}
			if (temp.size()!=0)
			{
				posibles.add(temp);
			}
			
		}	
		return posibles;
	}
}
