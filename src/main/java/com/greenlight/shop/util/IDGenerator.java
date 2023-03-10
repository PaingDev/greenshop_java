package com.greenlight.shop.util;

import org.hashids.Hashids;

public class IDGenerator {
	
	private static Hashids hashids = new Hashids("fr3shmy@nmar123", 6);

	public static String generateId(int id) {
		String hash = hashids.encode(id);
		return hash;
	}
}
