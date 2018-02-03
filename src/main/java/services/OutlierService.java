package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OutlierService {
	public List<Double> getOutliers(List<Double> input) {
		List<Double> output = new ArrayList<>();

		HashMap<String, List<Double>> array = this.splitArray(input);

		double q1 = this.getMedian(array.get("leftHalf"));
		double q3 = this.getMedian(array.get("rightHalf"));

		HashMap<String, Double> turkeyFences = tukeyFences(q1, q3);

		for (Double anInput : input) {
			if (anInput < turkeyFences.get("lowerFence") || anInput > turkeyFences.get("upperFence"))
				output.add(anInput);
		}
		return output;
	}

	private HashMap<String, Double> tukeyFences(double q1, double q3) {
		HashMap<String, Double> result = new HashMap<>();
		double iqr = q3 - q1;

		result.put("lowerFence", (q1 - 1.5 * iqr));
		result.put("upperFence", (q3 + 1.5 * iqr));

		return result;
	}

	private double getMedian(List<Double> array) {
		Integer halfSize = array.size() / 2;
		Double meridian = array.get(halfSize);

		if (isEven(array.size()))
			meridian = (meridian + array.get(halfSize - 1)) / 2;

		return meridian;
	}

	private HashMap<String, List<Double>> splitArray(List<Double> input) {
		Integer halfSizeOfArray = input.size() / 2;
		HashMap<String, List<Double>> result = new HashMap<>();

		result.put("leftHalf", input.subList(0, halfSizeOfArray));

		if (isEven(input.size()))
			halfSizeOfArray += 1;

		result.put("rightHalf", input.subList(halfSizeOfArray, input.size()));

		return result;
	}

	private boolean isEven(int input) {
		return input % 2 == 0;
	}
}
