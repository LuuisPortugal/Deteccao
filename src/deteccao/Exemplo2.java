package deteccao;

import static org.opencv.imgcodecs.Imgcodecs.imread;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class Exemplo2 {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat imagemColorida = imread("src\\pessoas\\faceolho.jpg");
		
		/* ------ ------ Faces ----- ----- */
		MatOfRect facesDetectadas = new MatOfRect();
		CascadeClassifier classificadorFace = new CascadeClassifier("src\\cascades\\haarcascade_frontalface_default.xml");
		classificadorFace.detectMultiScale(imagemColorida, facesDetectadas);
		
		for(Rect rect : facesDetectadas.toArray()) {
			Imgproc.circle(imagemColorida, 
					new Point(rect.x + (rect.width /2), rect.y + (rect.height /2)), 
					rect.width /2, 
					new Scalar(0, 255, 255), 2);
		}
		
		/* ------ ------ Olhos ----- ----- */
		MatOfRect olhosDetectadas = new MatOfRect();
		CascadeClassifier classificadorOlho = new CascadeClassifier("src\\cascades\\haarcascade_eye.xml");
		classificadorOlho.detectMultiScale(imagemColorida, olhosDetectadas);
		
		for(Rect rect : olhosDetectadas.toArray()) {			
			Imgproc.circle(imagemColorida, 
					new Point(rect.x + (rect.width /2), rect.y + (rect.height /2)), 
					rect.width /2, 
					new Scalar(0, 0, 255), 2);
		}

		Util util = new Util();
		util.mostraImagem(imagemColorida);
	}

}
