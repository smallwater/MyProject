package com.william.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class RandomNumberGeneratorTest {

	@Test
	public void testGenerate(){
		String quantity = "10";
		String bound = "20";
		List<Integer> resultList = RandomNumberGenerator.generate(quantity, bound);
		assertEquals(resultList.size(), Integer.parseInt(quantity));

		quantity = "3";
		resultList = RandomNumberGenerator.generate(quantity, bound);
		assertEquals(resultList.size(), Integer.parseInt(quantity));

		quantity = "abc";
		resultList = RandomNumberGenerator.generate(quantity, bound);
		assertEquals(resultList.size(), 1);
		
		quantity = null;
		resultList = RandomNumberGenerator.generate(quantity, bound);
		assertEquals(resultList.size(), 1);
	}
	
	@Test
	public void testDerivePrettyString(){
		List<Integer> candidate = new ArrayList<>();
		candidate.add(33);
		candidate.add(69);
		candidate.add(2);
		candidate.add(115);
		
		assertEquals("{\"0\" : \"33\", \"1\" : \"69\", \"2\" : \"2\", \"3\" : \"115\"}", RandomNumberGenerator.derivePrettyString(candidate));
		
	}
	
	@Test
	public void testConvertToIntegerOrReturnNull(){
		String candidate = "57";		
		Integer result = RandomNumberGenerator.convertToIntegerOrReturnNull(candidate);
		assertEquals(57, result.intValue());
		
		candidate = "abc";
		result = RandomNumberGenerator.convertToIntegerOrReturnNull(candidate);
		assertEquals(null, result);
		
		candidate = null;
		result = RandomNumberGenerator.convertToIntegerOrReturnNull(candidate);
		assertEquals(null, result);
		
	}
}
