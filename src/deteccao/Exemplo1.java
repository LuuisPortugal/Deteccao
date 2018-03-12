package deteccao;

import static org.opencv.imgcodecs.Imgcodecs.imread;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 * @author luis.portugal
 *
 */
public class Exemplo1 {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat imagemColorida = imread("src\\pessoas\\pessoas3.jpg");
		MatOfRect facesDetectadas = new MatOfRect();
		CascadeClassifier classificador = new CascadeClassifier("src\\cascades\\haarcascade_frontalface_default.xml");
		classificador.detectMultiScale(imagemColorida, 
				facesDetectadas,
				1.19,
				3,
				0,
				new Size(25, 25),
				new Size(250, 250));
		
		for(Rect rect : facesDetectadas.toArray()) {
			Imgproc.rectangle(imagemColorida, 
					new Point(rect.x, rect.y), 
					new Point(rect.x + rect.width, rect.y + rect.height), 
					new Scalar(0, 255, 255), 2);
		}

		Util util = new Util();
		util.mostraImagem(imagemColorida);
	}
}
