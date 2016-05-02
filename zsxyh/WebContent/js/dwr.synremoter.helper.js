dwr.engine.synremoter = {};
/**
 * Ϊ����ÿһ��class�ļ���С,�����з�������Ϊһ�����õ�call������
 */
dwr.engine.synremoter.call = function(script,scriptName,mname,args,callback){
	var _errorHandler = function (ex) {
		$.debug("errorHandler for " + scriptName+"."+mname+" :\n<br>");
		$.debug("args:"+dwr.util.toDescriptiveString(args,20)+"\n<br>");
		$.debug("exception:"+dwr.util.toDescriptiveString(ex,20)+"\n<br>");
	};
	if (callback != null) {
		/**
		 * jsdebugʱ���� �쳣��־��Ӧ����
		 */
		if(window.JsDebug){
			var tmpcallback = callback;
			callback = function(data){
				if(dwr.isException(data)){
					$.debug(data.message+":</br>"+data.trace);
				}
				//modify by kfzx-hexm01 on 2014-12-10,�޸�jsdebugģʽ�±���������������
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
			//jsdebugʱ���� �쳣��־��Ӧ����
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
 
	//Ϊͬ�����ý����������� ��֤���ܸ���
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
 * �ж�dwr�������ؽ���Ƿ������쳣��
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
