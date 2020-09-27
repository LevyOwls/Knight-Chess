package clases;

import java.util.ArrayList;

public class Opcion {
	
	private Knight k;
	private ArrayList<ArrayList> alternativas = new ArrayList();
	
	/**
	 * TIPO DE SOLUCION
	 * 1 = SE SACRIFICARA UNA PIEZA
	 * 0 = NO SE SACRIFICARÄ UNA PIEZA
	 */
	private int tipo_solucion;
	
	public Opcion(Knight k , ArrayList alternativas, int i)
	{
		this.setK(k);
		this.setAlternativas(alternativas);
		this.setTipo_solucion(i);
	}

	public Knight getK() {
		return k;
	}

	public void setK(Knight k) {
		this.k = k;
	}

	public ArrayList getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(ArrayList alternativas) {
		this.alternativas = alternativas;
	}

	public int getTipo_solucion() {
		return tipo_solucion;
	}

	public void setTipo_solucion(int tipo_solucion) {
		this.tipo_solucion = tipo_solucion;
	}

}
