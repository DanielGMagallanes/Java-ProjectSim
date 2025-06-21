package PracticeSim;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;


public class Window extends Canvas{

	private static final long serialVersionUID = -7606809190565392235L;
	private JFrame frame;
	private Canvas canvas;
	public JTextArea area;
	public JScrollPane sbar;
	public JPanel actionPanel;
	public JPanel ParkActionPanel;
	public JPanel ReactionPanel;
public Window(int width, int height , String title, Game game)
	{
		frame = new JFrame(title);

		actionPanel = new JPanel();
		actionPanel.setBounds(895,572,300,300);
		actionPanel.setBackground(Color.GRAY);
		actionPanel.setVisible(false);
		frame.add(actionPanel);

		ParkActionPanel = new JPanel();
		ParkActionPanel.setBounds(895,572,300,300);
		ParkActionPanel.setBackground(Color.GRAY);
		ParkActionPanel.setVisible(false);
		frame.add(ParkActionPanel);

		ReactionPanel = new JPanel();
		ReactionPanel.setBounds(895,572,300,300);
		ReactionPanel.setBackground(Color.BLACK);
		ReactionPanel.setVisible(false);
		frame.add(ReactionPanel);

		area = new JTextArea("this is going to be displayed\n");
		sbar = new JScrollPane(area);
		area.setBackground(Color.gray);
		area.setEditable(false);
		sbar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sbar.setBounds(50, 600, 800, 250);
		frame.add(sbar);

		sbar.setVisible(false);

		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));
		canvas.setMaximumSize(new Dimension(width,height));
		canvas.setMinimumSize(new Dimension(width,height));


		frame.add(game);
		frame.setVisible(true);
		game.start();

	}

	public void setPanel(JPanel a) {
		this.actionPanel = a;
	}
	public JPanel getPanel() {
		return actionPanel;
	}

	public JPanel getReactionPanel() {
		return ReactionPanel;
	}

	public JPanel getParkActionPanel() {
		return ParkActionPanel;
	}

	public JFrame getFrame() {
		return frame;
	}
	public Canvas getCanvas() {
		return canvas;
	}

}
