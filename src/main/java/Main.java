import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.photo.Photo;
import services.OutlierService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public void main() {
		String path = getClass().getResource("/ex2.png").getPath();
		reduceImageNoise(path, 25);

		IRQOutliner();
	}

	public void reduceImageNoise(String imagePath, Integer filterStrength) {
		Mat output = new Mat();
		Mat image = Highgui.imread(imagePath);

		Photo.fastNlMeansDenoisingColored(image, output, filterStrength, filterStrength, 7, 21);
		Highgui.imwrite("output.png", output);
	}

	public void IRQOutliner() {
		OutlierService outlierService = new OutlierService();

		List<Double> data = new ArrayList<Double>();
		data.add((double) 20);
		data.add((double) 65);
		data.add((double) 72);
		data.add((double) 75);
		data.add((double) 77);
		data.add((double) 1000);
		data.add((double) 78);
		data.add((double) 80);
		data.add((double) 81);
		data.add((double) 82);
		data.add((double) 83);
		Collections.sort(data);
		System.out.println(outlierService.getOutliers(data));
	}
}
