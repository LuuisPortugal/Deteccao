package deteccao;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Mat;

public class Util {
	public BufferedImage convertMatToImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }

        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] bytes = new byte[bufferSize];
        mat.get(0, 0, bytes);
        BufferedImage imagem = new BufferedImage(mat.cols(), mat.rows(), type);
        byte[] targetPixels = ((DataBufferByte) imagem.getRaster().getDataBuffer()).getData();
        System.arraycopy(bytes, 0, targetPixels, 0, bytes.length);
        return imagem;
    }
    
	public void mostraImagem(Mat imagem) {
		mostraImagem(convertMatToImage(imagem));
	}
	
    public void mostraImagem(BufferedImage imagem) {
        ImageIcon icon = new ImageIcon(imagem);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(imagem.getWidth() + 30, imagem.getHeight() + 30);
        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
