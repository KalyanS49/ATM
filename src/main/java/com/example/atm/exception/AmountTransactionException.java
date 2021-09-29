package com.example.atm.exception;

public class AmountTransactionException extends Exception {
	 
    private static final long serialVersionUID = -3128681006635769411L;
    
    public AmountTransactionException(String message) {
        super(message);
    }

}