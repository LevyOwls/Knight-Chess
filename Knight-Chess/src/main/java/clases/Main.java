package clases;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Main 
{

	public static void main(String args[]) throws ParseException
	{
		JsonManagement test=new JsonManagement();
		
		String s="{\"ids\": [[100, 101, 102, 103, 104, 105, 106, 107], [108, 109, 110, 111, 112, 113, 114, 115], [null, null, null, null, null, null, null, null], [null, null, null, null, null, null, null, null], [null, null, null, null, null, null, null, null], [null, null, null, null, null, null, null, null], [200, 201, 202, 203, 204, 205, 206, 207], [208, 209, 210, 211, 212, 213, 214, 215]], \"enemy_knights\": [[100, 101, 102, 103, 104, 105, 106, 107], [108, 109, 110, 111, 112, 113, 114, 115], [null, null, null, null, null, null, null, null], [null, null, null, null, null, null, null, null], [null, null, null, null, null, null, null,null], [null, null, null, null, null, null, null, null], [null, null, null, null, null, null, null, null], [null, null, null, null, null, null, null, null]], \"enemy_knights_dict\": {\"100\": [0, 0], \"101\": [1, 0], \"102\": [2, 0], \"103\": [3, 0], \"104\": [4, 0], \"105\": [5, 0], \"106\": [6, 0], \"107\": [7, 0], \"108\": [0, 1], \"109\": [1, 1], \"110\": [2, 1], \"111\": [3, 1], \"112\": [4, 1], \"113\": [5, 1], \"114\": [6, 1], \"115\": [7, 1]},\"my_knights\": [[null, null, null, null, null, null, null, null], [null, null, null, null, null, null, null, null], [null, null, null, null, null, null, null, null], [null, null, null, null, null, null, null, null], [null, null, null, null, null, null, null, null], [null, null, null, null, null, null, null, null], [200, 201, 202, 203, 204, 205, 206, 207], [208, 209, 210, 211, 212, 213, 214, 215]], \"my_knights_dict\": {\"200\": [0, 6], \"201\": [1, 6], \"202\": [2, 6], \"203\": [3, 6], \"204\": [4, 6], \"205\": [5, 6], \"206\": [6, 6], \"207\": [7, 6], \"208\": [0, 7], \"209\": [1, 7], \"210\": [2, 7], \"211\": [3, 7], \"212\": [4, 7], \"213\": [5, 7], \"214\": [6, 7], \"215\": [7, 7]}}";
		
		Tablero tablero=new Tablero();
		test.toObjects(s,tablero);
		
		
		
		//tablero.move();
		
		Tablero t2=tablero.copyTablero();
		FrameIn showTime=new FrameIn(tablero);

		//Object name = (Object) jo.get("my_knights_dict");
		//Object eo=((HashMap) name).get("200");
		//System.out.println(name);
		//System.out.println(eo);
		//Celda c=tablero.getCelda(0,0);
		//Knight k=c.getk();
		//ArrayList nuevo=k.posibleMoves();
		int i=0;
		i=i+1-1;
	}
	
}
