package com.hzx.conc.common.exception;


import com.hzx.conc.common.enumeration.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
 * @author zhangzhan
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 2273323447868974326L;

	private Integer code = ResponseCode.error.getCode();

	private Object data;

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Integer code, String message, Object data) {
		super(message);
		this.code = code;
		this.data = data;
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(Integer code, String message) {
		super(message);
		if (code != null) {
			this.code = code;
		}
	}

}
