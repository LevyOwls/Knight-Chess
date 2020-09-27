package clases;

public class Eval 
{
	private Tablero t;
	private int k;
	private int oX;
	private int oY;
	private int toX;
	private int toY;
	private double score=1;
	private int fX;
	private int fY;
	
	public Eval(Tablero t, int k,int x, int y,int oX,int oY)
	{
		this.setT(t);
		this.setK(k);
		this.setToX(x);
		this.setToY(y);
		this.setoX(oX);
		this.setoY(oY);
		fX=oX-x;
		fY=oY-y;
	}
	
	public int getFX()
	{
		return fX;
	}
	public int getFY()
	{
		return fY;
	}
	public double getScore()
	{
		return score;
	}
	 void setK(int k2) {
		this.k=k2;
		
	}
	 public int getK()
	 {
		 return k;
	 }

	public Tablero getT() {
		return t;
	}


	public void setT(Tablero t) {
		this.t = t;
	}


	public int getToX() {
		return toX;
	}


	public void setToX(int toX) {
		this.toX = toX;
	}


	public int getToY() {
		return toY;
	}


	public void setToY(int toY) {
		this.toY = toY;
	}
	
	public boolean turn()
	{
		
		Knight k=t.search(this.k);
		//SE VACIA LA CELDA DEL KNIGHT
		Celda temp=t.getCelda(k.getX(), k.getY());
		//SE VISUALIZA SU DESTINO
		Celda dest=t.getCelda(toX, toY);
		
		//SE VERIFICA SI CONTIENE UN K
		if (dest.containsKnight())
		{
			//SI ES ENEMIGO, DO IT
			if (dest.getk().isEnemy())
			{
				dest.setk(k);
				k.setX(toX);
				k.setY(toY);
				//SE ACTUALIZA EL TABLERO
				t.move();
				score*=0.002;
			}
			else
			{
				//SI NO ES ENEMIGO ES ALIADO
				//Y ESO ESTA PROHIBIDO
				return false;
			}
		}
		dest.setk(k);
		k.setX(toX);
		k.setY(toY);
		//SE ACTUALIZA EL TABLERO
		if (dest.isEnemyRange())
		{
			score*=0.04;
			
		}
		else
		{
			score*=0.03;
		}
		t.move();
		score=score*score;
		return true;
		
	}


	public int getoX() {
		return oX;
	}


	public void setoX(int oX) {
		this.oX = oX;
	}


	public int getoY() {
		return oY;
	}


	public void setoY(int oY) {
		this.oY = oY;
	}
	
	public int fX()
	{
		return oX-toX;
	}
	
	public int fY()
	{
		return oY-toY;
	}
}
