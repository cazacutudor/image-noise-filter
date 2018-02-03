package services;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;


public class OutlierServiceTest {
	OutlierService subject = new OutlierService();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getOutliersWhenGetsAListOfSortedValuesReturnsOutliners() throws Exception {
		List<Double> input = Arrays.asList(11.2, 12.1, 22.1, 43.1, 997.1);

		List<Double> result = subject.getOutliers(input);

		List<Double> expect = Arrays.asList(997.1);
		Assert.assertThat(result, is(expect));
	}

}