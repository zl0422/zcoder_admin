package com.zcoder.admin.core.exception;

/**
 * Created by lin on 2016-05-19.
 */
public class GlobalException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4425626583033519834L;

	public GlobalException() {

    }

    public GlobalException(String msg) {
        super(msg);
    }

}
