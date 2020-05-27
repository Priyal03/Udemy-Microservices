package com.dheeru;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionsDemo {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("\nInside main ...");
		try {
			share();
			System.out.println("After invoking share ...");
		} catch (FileNotFoundException e) {

			System.out.println("main: filenotfoundexception ...");
		} finally {
			System.out.println("Inside main's finally ...");
		}
		System.out.println("\nEnd of main ...");
	}

	private static void share() throws FileNotFoundException {
		System.out.println("\nInside share ...");

		try {
			String response = HttpConnect.send(0, "hello", "http://www.goodsnips.com");
			System.out.println("After invoking send ...");
			// APIParser.parseSendResponseCode(response, "hello",
			// "http://www.goodsnips.com");
		} catch (FileNotFoundException e) {
			System.out.println("Share: filenotfoundexception ...");
		//	e.printStackTrace();
			throw e;
		} catch (IOException e) {
			System.out.println("Connecting to a different server ...");
		}
//		catch (APIFormatChangeException e) {
//			// Item 65: Donít ignore exceptions
//			e.printStackTrace();
//			
//			// Item 63: Include failure-capture information in detail messages
//			//System.out.println("e.toString(): " + e);
//			System.out.println("e.getMessage(): " + e.getMessage());
//			
//			// Item 63
//			System.out.println("e.getElement(): " + e.getElement());
//			
//			// Item 61: Throw exceptions appropriate to the abstraction
//			e.getCause().printStackTrace();
//		}
		finally {
			System.out.println("Inside share's finally ...");
		}

		System.out.println("\nEnd of share ...");
	}

}