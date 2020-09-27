package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class FrameIn extends JFrame {
	private Font font=new Font("Calibri", Font.BOLD, 20);
	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public FrameIn(Tablero tablero) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		JPanel panel = new JPanel(new GridLayout(8,8));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBackground(Color.white);

		int i,j;
		Celda aux;
		for (i=0;i<8;i++)
		{
			for (j=0;j<8;j++)
			{
				aux=tablero.getCelda(j, i);
				JLabel lbl=new JLabel("",SwingConstants.CENTER);
				lbl.setFont(font);
				lbl.setBackground(Color.white);
				
				if (aux.isEnemyRange() && aux.isAllyRange())
				{
					lbl.setBackground(Color.magenta);
					lbl.setOpaque(true);
					lbl.setBorder(new LineBorder(Color.black,3,true));
				}
				if (aux.isAllyRange() && !aux.isEnemyRange())
				{
					lbl.setBackground(Color.cyan);
					lbl.setOpaque(true);
					lbl.setBorder(new LineBorder(Color.black,3,true));
				}
				if (!aux.isAllyRange() && aux.isEnemyRange())
				{
					lbl.setBackground(Color.pink);
					lbl.setOpaque(true);
					lbl.setBorder(new LineBorder(Color.black,3,true));
				}
				Knight k=aux.getk();
				if (k!=null)
				{
					if (k.isEnemy())
					{
						lbl.setText("E");
						
					}
					else
					{
						lbl.setText("A");
					}
				}
				
				panel.add(lbl);	
			}
		}
		repaint();
		
		this.setVisible(true);
	}

}
