dwr.engine.synremoter = {};
/**
 * 为减少每一个class文件大小,将所有方法设置为一个公用的call方法。
 */
dwr.engine.synremoter.call = function(script,scriptName,mname,args,callback){
	var _errorHandler = function (ex) {
		$.debug("errorHandler for " + scriptName+"."+mname+" :\n<br>");
		$.debug("args:"+dwr.util.toDescriptiveString(args,20)+"\n<br>");
		$.debug("exception:"+dwr.util.toDescriptiveString(ex,20)+"\n<br>");
	};
	if (callback != null) {
		/**
		 * jsdebug时开启 异常日志答应功能
		 */
		if(window.JsDebug){
			var tmpcallback = callback;
			callback = function(data){
				if(dwr.isException(data)){
					$.debug(data.message+":</br>"+data.trace);
				}
				//modify by kfzx-hexm01 on 2014-12-10,修复jsdebug模式下保存参数报错的问题
				if(typeof tmpcallback == "function") {
					tmpcallback(data);
				}else{
					tmpcallback.callback(data);
				}
			};
		}

		if (typeof callback == "function") {
			callback = { 
					"callback": callback,
					errorHandler: _errorHandler 
			};
		}else {
			callback = $.extend({ 
				errorHandler: _errorHandler 
			},
			callback); 
		} 

		args[args.length - 1] = callback;
		return dwr.engine._execute(script._path, scriptName, mname, args);
	}

	//synchronized
	var dwr_result = null;

	var dwr_callback;
	if(window.JsDebug) {
		dwr_callback = { 
				callback: function (data) {
			//jsdebug时开启 异常日志答应功能
			if(dwr.isException(data)){
				$.debug(data.message+":</br>"+data.trace);
			}
			dwr_result = data; 
		}, 
		errorHandler: _errorHandler 
		}; 
	}else{
		dwr_callback = { 
				callback: function (data) {
			dwr_result = data; 
		}, 
		errorHandler: _errorHandler 
		}; 
	}

	//add call back funciton
	var dwr_arguments = [];
	var len = args.length;
	for(var i=0; i<len; i++) {
		dwr_arguments[i] = args[i];
	}
	dwr_arguments[len] = dwr_callback;
 
	//为同步调用建立单独批量 保证不受干扰
	var batch = dwr.engine.batch.create();
	// All the paths MUST be to the same servlet
	if (batch.path == null) {
		batch.path = script._path;
	}
	dwr.engine.batch.addCall(batch, scriptName, mname, dwr_arguments);
	batch.map.callCount++;
	batch.async = false;
	dwr.engine.transport.send(batch);
	dwr.engine.batch.remove(batch);

	return dwr_result;
};

/**
 * 判断dwr函数返回结果是否发生了异常。
 */
dwr.isException = function(data){
	if(data == null) return false;
	if(data.hasDwrExecption_){
		return true;
	}
	return false;
};

function setDwrEngineHeaders(vacpUserId,dse_sessionId,vacpRoleId,vacpAreaId,vacpAppId,vacpEnvId){
	if(vacpUserId==undefined || vacpUserId==null){
		vacpUserId = '-1';
	}
	if(dse_sessionId==undefined || dse_sessionId==null){
		dse_sessionId = '-1';
	}
	if(vacpRoleId==undefined || vacpRoleId==null || vacpRoleId=='' ||  isNaN(vacpRoleId)==true){
		vacpRoleId = '-1';
	}
	if(vacpAreaId==undefined || vacpAreaId==null || vacpAreaId=='' ||  isNaN(vacpAreaId)==true){
		vacpAreaId = '-1';
	}
	if(vacpAppId==undefined || vacpAppId==null || vacpAppId=='' || isNaN(vacpAppId)==true){
		vacpAppId = '-1';
	}
	if(vacpEnvId==undefined || vacpEnvId==null || vacpEnvId==''){
		vacpEnfId = '-1';
	}
	dwr.engine.setHeaders({"vacpUserId":vacpUserId,"dse_sessionId":dse_sessionId,"vacpRoleId":vacpRoleId,"vacpAreaId":""+vacpAreaId,"vacpAppId":""+vacpAppId,"vacpEnvId":""+vacpEnvId});
};
