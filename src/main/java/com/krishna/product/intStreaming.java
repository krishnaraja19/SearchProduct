package com.krishna.product;

import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class intStreaming {
	public static void main(String args[]) {
		IntStream stream;
		stream = IntStream.range(1,100);
		stream = stream.filter(number->{
			if(number%4==0)
				return true;
			return false;
		});
		OptionalDouble optionalAverage = stream.average();
		double average;
		average = optionalAverage.orElse(0);
		System.out.println(average);
	}
}
