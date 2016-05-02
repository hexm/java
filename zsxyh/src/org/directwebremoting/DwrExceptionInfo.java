package org.directwebremoting;

import com.icbc.hexm.util.MyUtil;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 当dwr方法调用过程发生异常的时候 返回对象，用户页面查证。
 * @author kfzx-niebo
 *
 */
public class DwrExceptionInfo {
	final private boolean hasDwrExecption_ = true;
	private String message ;
	private String trace;
	public DwrExceptionInfo(Throwable throwable) {
		if(throwable == null) return;
		message = throwable.getMessage();
		trace = MyUtil.Execption2String(throwable);
	}

 
	public String getMessage() {
		return message;
	}
	public String getTrace() {
		return trace;
	}


	public boolean isHasDwrExecption_() {
		return hasDwrExecption_;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public void setTrace(String trace) {
		this.trace = trace;
	}


	@Override
	public String toString() {
		return new ToStringBuilder(this).append("message", message).append("trace", trace).toString();
	}
}
