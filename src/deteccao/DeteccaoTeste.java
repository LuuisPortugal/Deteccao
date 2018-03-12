package deteccao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;

import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;

public class DeteccaoTeste {

	public static void main(String[] args) {
		Util util = new Util();
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat imageColorida = imread("src\\deteccao\\opencv-java.jpg", CV_LOAD_IMAGE_COLOR);
		util.mostraImagem(imageColorida);
		
		
		Mat ImagemCinza = new Mat();
		Imgproc.cvtColor(imageColorida, ImagemCinza, COLOR_BGR2GRAY);
		util.mostraImagem(ImagemCinza);
		
		
	}

}
