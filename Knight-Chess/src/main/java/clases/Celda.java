package clases;

public class Celda 
{
	private int x;
	private int y;
	private Knight k=null;
	private boolean enemyRange=false;
	private boolean allyRange=false;
	
	public Celda(int x,int y)
	{
		this.x=x;
		this.y=y;
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

	public Knight getk() {
		return k;
	}

	public void setk(Knight k) {
		this.k = k;
	}
	
	public boolean isEnemyRange() {
		return enemyRange;
	}

	public void setEnemyRange(boolean enemyRange) {
		this.enemyRange = enemyRange;
	}

	public boolean isAllyRange() {
		return allyRange;
	}

	public void setAllyRange(boolean allyRange) {
		this.allyRange = allyRange;
	}

	public boolean containsKnight()
	{
		if (k!=null)
		{
			return true;
		}
		return false;
	}
}
