package org.directwebremoting;

import org.directwebremoting.extend.Call;
import org.directwebremoting.extend.MethodDeclaration;
import org.directwebremoting.extend.Module;
import org.directwebremoting.extend.Reply;
import org.directwebremoting.impl.DefaultRemoter;
import org.directwebremoting.util.JavascriptUtil;
import org.directwebremoting.util.LocalUtil;

/**
 * 支持DWR同步方法调用 支持DWR3.0.0.116.rc1<br>
 * 
 * JS代码 异步调用：<br>
 * test1.sayHello("hello", function(data){ alert(data); } );
 * 
 * JS代码 同步调用：<br>
 * var result = test1.sayHello("hello"); <br>
 * alert(result);
 * 
 * @author kfzx-niebo
 * @createDate 2010-5-12
 */
public class SyncRemoter extends DefaultRemoter  {

	public int getMaxCallCount() {
		return this.maxCallCount;
	}

	 /* (non-Javadoc)
     * @see org.directwebremoting.extend.Remoter#generateInterfaceScript(java.lang.String, java.lang.String, java.lang.String)
     */
	@Override
    public String generateInterfaceJavaScript(String scriptName, String indent, String assignVariable, String contextServletPath) throws SecurityException
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append(indent + assignVariable + " = {};\n");
        buffer.append("var cn = \""+scriptName+"\";\n");
        Module module = moduleManager.getModule(scriptName, false);

        MethodDeclaration[] methods = module.getMethods();
        for (MethodDeclaration method : methods)
        {
            String methodName = method.getName();

            // We don't need to check accessControl.getReasonToNotExecute()
            // because the checks are made by the execute() method, but we do
            // check if we can display it
            try
            {
                accessControl.assertGeneralDisplayable(scriptName, method);
            }
            catch (SecurityException ex)
            {
                if (!allowImpossibleTests)
                {
                    continue;
                }
            }

            // Is it on the list of banned names
            if (JavascriptUtil.isReservedWord(methodName))
            {
                continue;
            }

            Class<?>[] paramTypes = method.getParameterTypes();


            // Create the function definition
            buffer.append(indent + assignVariable + "." + methodName + " = function(");
            for (int j = 0; j < paramTypes.length; j++)
            {
                if ((!LocalUtil.isServletClass(paramTypes[j])))
                {
                    buffer.append("p");
                    buffer.append(j);
                    buffer.append(", ");
                }
            }
            buffer.append("cb) {\n");

            // The method body calls into engine.js
            buffer.append(indent + "  return ");
            buffer.append("dwr.engine.synremoter.call");
            buffer.append("(");
            buffer.append(assignVariable);
            buffer.append(",cn,'");
            buffer.append(methodName);
            buffer.append("\', arguments,cb);\n");
            buffer.append(indent + "};\n");
            
            //set className and methodName
            buffer.append(assignVariable + "." + methodName + ".className = cn;\n");
            buffer.append(assignVariable + "." + methodName + ".methodName = \""+methodName+"\";\n");
            
        }

        return buffer.toString();
    }


	/**
	 * catche 掉所有异常，并将异常作为Reply返回给浏览器
	 * @param call
	 * @return
	 */
	private Reply executeCatchExecption(Call call){
		Reply r;
		try{
			r = super.execute(call);
			if(r.getThrowable() != null){
				r = new Reply(call.getCallId(), new DwrExceptionInfo(r.getThrowable()));
			}
			return r;
		}catch (Exception e) {
			r = new Reply(call.getCallId(), new DwrExceptionInfo(e));
			return r;
		}
		 
	}
	
	@Override
	public Reply execute(final Call call) {
		Reply ry = null;
		boolean ryExec = false;
		try {
			ryExec = true;
			ry = executeCatchExecption(call);
		} catch (Exception e) {
			if(ryExec==false){
				ryExec = true;
				ry = executeCatchExecption(call);
			}
		}
		return ry;
	}
}
