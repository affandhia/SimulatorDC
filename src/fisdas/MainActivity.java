package fisdas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;

public class MainActivity
{

	private JFrame frmSimulasiDcMotor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try {
					MainActivity window = new MainActivity();
					window.frmSimulasiDcMotor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainActivity()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		final int windowWidth = 640, windowHeight = 480;
		frmSimulasiDcMotor = new JFrame();
		frmSimulasiDcMotor.setResizable(false);
		frmSimulasiDcMotor.setTitle("Simulasi DC Motor");
		frmSimulasiDcMotor.setBounds(100, 100, windowWidth, windowHeight);
		frmSimulasiDcMotor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSimulasiDcMotor.setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {windowWidth};
		int cPanelHeight = 180;
		gridBagLayout.rowHeights = new int[] {windowHeight - cPanelHeight, cPanelHeight};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0};
		frmSimulasiDcMotor.getContentPane().setLayout(gridBagLayout);
		
		JPanel vPanel = new JPanel();
		vPanel.setBackground(SystemColor.controlShadow);
		GridBagConstraints gbc_vPanel = new GridBagConstraints();
		gbc_vPanel.insets = new Insets(0, 0, 5, 0);
		gbc_vPanel.fill = GridBagConstraints.BOTH;
		gbc_vPanel.gridx = 0;
		gbc_vPanel.gridy = 0;
		frmSimulasiDcMotor.getContentPane().add(vPanel, gbc_vPanel);
		vPanel.setLayout(new GridLayout(2, 2, 3, 3));
		
		// each panel point of viewer has 315 width and 139 height
		final int povHeight = 139, povWidth = 315;
		
		class TopPanel extends JPanel {
			private static final long serialVersionUID = 1L;
			/* (non-Javadoc)
			 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
			 */
			double scaleX = 1, scaleY = scaleX;
			int magnetWidth = 80, magnetHeight = 30, motorHeadWidth = 25, motorHeadHeight = 40;
			int cableWidth = 40, cableHeight = 40, maxCableWidth = 80, maxCableHeight = 40;
			private int motorHeadX = 3 * povWidth / 4 - motorHeadWidth /2;
			Rectangle pin = new Rectangle(motorHeadX,
					povHeight / 2 - motorHeadHeight / 5 / 2,
					motorHeadWidth,
					motorHeadHeight / 5);
			Rectangle cable = new Rectangle(povWidth / 2 - cableWidth / 2,
					povHeight / 2 - cableHeight / 2,
					cableWidth,
					cableHeight);
			@Override
			protected void paintComponent(Graphics arg0)
			{
				// TODO Auto-generated method stub
				Graphics2D g2 = (Graphics2D) arg0;
				AffineTransform at = g2.getTransform();
				at.scale(scaleX, scaleY);
				g2.setTransform(at);
				super.paintComponent(g2);

				// draw north magnet
				g2.setColor(Color.RED);
				g2.fillRect(povWidth / 2 - magnetWidth /2,
						0,
						magnetWidth,
						magnetHeight);
				
				//draw south magnet
				g2.setColor(Color.BLUE);
				g2.fillRect(povWidth / 2 - magnetWidth /2,
						povHeight - magnetHeight,
						magnetWidth,
						magnetHeight);
				
				//draw cable
				g2.setColor(Color.ORANGE);
				Stroke tempStroke = g2.getStroke();
				g2.setStroke(new BasicStroke(4));
				g2.draw(cable);
				g2.setStroke(tempStroke);

				//draw motor head
				g2.setColor(Color.YELLOW);
				g2.fillRect(motorHeadX,
						povHeight / 2 - motorHeadHeight / 2,
						motorHeadWidth,
						motorHeadHeight);
				
				//draw motor head pin
				if(pin.y < motorHeadHeight) {
					pin.y = povHeight / 2 + motorHeadHeight / 2;
				}
				g2.setColor(Color.BLACK);
				g2.fill(pin);
				
				//draw mask
				g2.setColor(new Color(240,240,240));
				g2.fillRect(motorHeadX,
						povHeight / 2 - 2 * motorHeadHeight - motorHeadHeight / 2,
						motorHeadWidth,
						2 * motorHeadHeight);
				
				g2.fillRect(motorHeadX,
						povHeight / 2 + motorHeadHeight / 2,
						motorHeadWidth,
						2 * motorHeadHeight);
				
				
				
			}
		}
		TopPanel topPanel = new TopPanel();
		vPanel.add(topPanel);
		topPanel.setLayout(null);
		
		JPanel frontPanel = new JPanel();
		vPanel.add(frontPanel);
		frontPanel.setLayout(null);
		
		JPanel sidePanel = new JPanel();
		vPanel.add(sidePanel);
		
		JPanel statusPanel = new JPanel();
		vPanel.add(statusPanel);
		statusPanel.setLayout(null);
		
		JPanel cPanel = new JPanel();
		GridBagConstraints gbc_cPanel = new GridBagConstraints();
		gbc_cPanel.fill = GridBagConstraints.BOTH;
		gbc_cPanel.gridx = 0;
		gbc_cPanel.gridy = 1;
		frmSimulasiDcMotor.getContentPane().add(cPanel, gbc_cPanel);
		cPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel arusCPanel = new JPanel();
		cPanel.add(arusCPanel);
		arusCPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblArusListrik = new JLabel("Arus Listrik");
		arusCPanel.add(lblArusListrik);
		
		JSlider sliderArusListrik = new JSlider();
		sliderArusListrik.setMajorTickSpacing(10);
		sliderArusListrik.setPaintTicks(true);
		arusCPanel.add(sliderArusListrik);
		
		JPanel medmagCPanel = new JPanel();
		cPanel.add(medmagCPanel);
		medmagCPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblKuatMedanMagnet = new JLabel("Kuat Medan Magnet");
		medmagCPanel.add(lblKuatMedanMagnet);
		
		JSlider sliderKuatMedanMagnet = new JSlider();
		sliderKuatMedanMagnet.setPaintTicks(true);
		sliderKuatMedanMagnet.setMajorTickSpacing(10);
		medmagCPanel.add(sliderKuatMedanMagnet);
		
		JPanel luasCPanel = new JPanel();
		cPanel.add(luasCPanel);
		luasCPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblPanjangKawat = new JLabel("Panjang Kawat");
		luasCPanel.add(lblPanjangKawat);
		
		JSlider sliderPanjangKawat = new JSlider();
		sliderPanjangKawat.setMajorTickSpacing(10);
		sliderPanjangKawat.setPaintTicks(true);
		luasCPanel.add(sliderPanjangKawat);
		
		JLabel lblLebarKawat = new JLabel("Lebar Kawat");
		luasCPanel.add(lblLebarKawat);
		
		JSlider sliderLebarKawat = new JSlider();
		sliderLebarKawat.setMajorTickSpacing(10);
		sliderLebarKawat.setPaintTicks(true);
		luasCPanel.add(sliderLebarKawat);
		double[] increment = new double[]{1};
		// custom action -0-------0-
		Timer timer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				topPanel.pin.y -= 1;
				if (topPanel.cable.height < 1) {
					increment[0] = 1;
				} else if(topPanel.cable.height > topPanel.cableHeight) {
					increment[0] = -1;
				}
				topPanel.cable.height += increment[0];
				topPanel.cable.y = povHeight / 2 - topPanel.cable.height / 2;
				topPanel.repaint();
			}
			
		}); timer.start();
	}

}
