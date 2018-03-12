package deteccao;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class Webcam extends javax.swing.JFrame {

	private javax.swing.JPanel jPanel1;
	
	public Webcam() {
		initComponents();
	}
	
	private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jPanel1 = new JPanel();
        jPanel1.setSize(700, 480);
        add(jPanel1);
        
        pack();
    }  
	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Webcam ja = new Webcam();
		ja.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ja.setSize(700, 480);
		ja.setVisible(true);
		ja.mostraVideo();
	}
	
	public void mostraVideo() {
		Mat video = new Mat();
		VideoCapture captura = new VideoCapture(0);
		
		if(captura.isOpened()) {
			while(captura.grab()) {
				captura.read(video);

				if(!video.empty()) {					
					/* ------ ------ Faces ----- ----- */
					MatOfRect facesDetectadas = new MatOfRect();
					CascadeClassifier classificadorFace = new CascadeClassifier("src\\cascades\\haarcascade_frontalface_default.xml");
					classificadorFace.detectMultiScale(video, facesDetectadas);
					
					for(Rect rect : facesDetectadas.toArray()) {
						Imgproc.circle(video, 
								new Point(rect.x + (rect.width /2), rect.y + (rect.height /2)), 
								rect.width /2, 
								new Scalar(0, 255, 255), 2);
					}
				
					setSize(video.width(), video.height());
					BufferedImage imagem = new Util().convertMatToImage(video);
					Graphics g = jPanel1.getGraphics();
					g.drawImage(imagem, 0, 0, imagem.getWidth(), imagem.getHeight(), null);
				}
			}
		}
		
	}
}
