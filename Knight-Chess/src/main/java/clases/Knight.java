package clases;

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
}
