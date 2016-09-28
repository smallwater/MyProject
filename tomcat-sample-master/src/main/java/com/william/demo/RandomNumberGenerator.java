package com.william.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator{

	/** Used to determine how many random numbers shall be generated if
	 * no quantity was specified by the caller. 
	 */
	public static final int DEFAULT_QUANTITY = 1;
	/** Used as the default upper bound of the random numbers that shall be
	 * generated if no upper bound was specified by the caller. 
	 */
	public static final int DEFAULT_BOUND = 2;
	 
	private static Random random = new Random();

	/**
	 * 
	 * @return generate(DEFAULT_QUANITTY, DEFAULT_BOUND)
	 */
	public static List<Integer> generate(){
		return generate(DEFAULT_QUANTITY, DEFAULT_BOUND);
	}
	
	/**
	 * 
	 * @param quantity The number of random numbers to generate.
	 * @param bound The highest possible random number, inclusive.
	 * @return A List containing [quantity] number of random numbers.
	 */
	public static List<Integer> generate(String quantity, String bound){
		Integer quantityInteger = convertToIntegerOrReturnNull(quantity);
		Integer boundInteger = convertToIntegerOrReturnNull(bound);
		return generate(quantityInteger, boundInteger);
	}

	/**
	 * @param candidate The String we wish to convert to an Integer.
	 * @return The corresponding Integer, or null if it couldn't be parsed.
	 */
	public static Integer convertToIntegerOrReturnNull(String candidate){
		try{
			Integer returnInteger = Integer.parseInt(candidate);
			return returnInteger;
		}catch(NumberFormatException nfe){
			return null;
		}
	}

	/**
	 * 
	 * @param quantity The number of random numbers to generate.
	 * @param bound The highest possible random number, inclusive.
	 * @return A List containing [quantity] number of random numbers.
	 */
	public static List<Integer> generate(Integer quantity, Integer bound){
		if(quantity == null){
			quantity = DEFAULT_QUANTITY;
		}
		
		if(bound == null){
			bound = DEFAULT_BOUND;
		}
		
		List<Integer> returnList = new ArrayList<>(quantity);
		for(int i=0; i<quantity; i++){
			returnList.add(random.nextInt(bound));
		}
		
		return returnList;
	}
	
	/**
	 * 
	 * @param candidate The List of Integers to be pretty-stringified.
	 * @return The pretty string version of the given candidate.
	 */
	public static String derivePrettyString(List<Integer> candidate){
		if(candidate == null || candidate.size() == 0){
			return "{}";
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{");
		for(int i=0; i<candidate.size(); i++){
			stringBuilder.append("\"").append(i).append("\"")
				.append(" : ")
				.append("\"").append(candidate.get(i)).append("\"");
			if(i+1 < candidate.size()){
				stringBuilder.append(", ");
			}
		}
		stringBuilder.append("}");
		return stringBuilder.toString();
	}
}
