package clases;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonManagement 
{

	public JsonManagement()
	{
		
	}
	
	
	public void toObjects(String input, Tablero t) throws ParseException
	{
		JSONParser parser = new JSONParser();
		JSONObject jo =(JSONObject) parser.parse(input);
		//Se extraen los datos principales
		Object ids 					= jo.get("ids");
		Object enemy_knights 		= jo.get("enemy_knights");
		Object my_knights 			= jo.get("my_knights");
		Object enemy_knights_dict	= jo.get("enemy_knights_dict");
		Object my_knights_dict		= jo.get("my_knights_dict");
		
		ArrayList KnightsEnemigos	=inGame(enemy_knights);
		ArrayList Knightsaliados	=inGame(my_knights);
		
		//System.out.println(ids);
		generarKnights(t, Knightsaliados, my_knights_dict,false);
		generarKnights(t, KnightsEnemigos, enemy_knights_dict, true);
		//System.out.println(ids);
		//System.out.println(enemy_knights);
		//Para obtener cada uno hacer un Object temp=(objeto).get(id);
		t.move();
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList inGame(Object matriz)
	{
		ArrayList nuevo=new ArrayList();
		int i,j;
		JSONArray temp=(JSONArray) matriz;
		ArrayList aux;
		//System.out.println(matriz);
		for (i=0;i<8;i++)
		{
			aux=(ArrayList) temp.get(i);
			for (j=0;j<8;j++)
			{
				if (aux.get(j)!=null)
				{
					nuevo.add(aux.get(j));
				}
			}
		}
		return nuevo;
	}
	
	public void generarKnights(Tablero tablero,ArrayList lista,Object coordenadas,boolean enemy)
	{
		int i;
		long id;
		int x,y;
		//RECORRE LA LISTA DE LOS IDS QUE HAY EN EL CAMPO
		//Lo que recorre el id
		Object temp;
		Celda celda;
		for (i=0;i<lista.size();i++)
		{
			id=(Long) lista.get(i);
			temp=((HashMap) coordenadas).get(""+id);
			JSONArray info=(JSONArray) temp;
			//SE CREA EL KNIGHT Y SE INSERTA EN EL TABLERO
			x=Integer.parseInt(""+info.get(0)) ;
			y=Integer.parseInt(""+info.get(1));
			Knight nuevo=new Knight(Integer.parseInt(""+id),x, y, enemy);
			
			celda=tablero.getCelda(x, y);
			celda.setk(nuevo);
		}
		
	}
}










