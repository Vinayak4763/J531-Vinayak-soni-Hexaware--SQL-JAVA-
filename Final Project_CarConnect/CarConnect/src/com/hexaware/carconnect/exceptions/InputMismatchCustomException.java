/* Romi Kumar Singh and Vinayak Soni
 * CarConnect Input mismatch exception
 * 21/10/2024
 */

package com.hexaware.carconnect.exceptions;

public class InputMismatchCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InputMismatchCustomException(String message) {
        System.out.println(message);  // Print user-friendly message
    }
}