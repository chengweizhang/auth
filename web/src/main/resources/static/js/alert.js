// $(function(){
	showAlert = function(str) {
		if(!str){
			str = "网络异常，请稍候再试";
		}
		if (str == "token不存在") {
			window.location.href = "../login/login!index";
			return;
		}
		var boht = document.createElement("span");
		document.body.appendChild(boht);
		boht.id = "boht";
		var alertHtml = '<div id="bg"></div>';
		alertHtml += '<div id="info"><div id="center">';
		alertHtml += '<strong>提示</strong>';
		alertHtml += '<p>' + str + '</p>';
		alertHtml += '<a class="closeAlert" href=javascript:closeAlert();>确认</a>';
		alertHtml += '</div></div>';
		boht.innerHTML = alertHtml;
	}
	
	showModalAlert = function(str) {
        if(!str){
            str = "网络异常，请稍候再试";
        }
        if (str == "token不存在") {
            window.location.href = "../login/login!index";
            return;
        }
        $('#boht').remove();
        var boht = document.createElement("div");
        document.body.appendChild(boht);
        boht.id = "boht";
        var alertHtml = ['<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="errorModalLabel" aria-hidden="true">',
         '<div class="modal-dialog">',
         '<div class="modal-content">',
         '<div class="modal-header">',
         '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>',
         '<h4 class="modal-title" id="errorModalLabel">提示</h4>',
         '</div><div id="errorMsg" class="modal-body text-center" style="font-size: 16px;color:red;">' + str + '</div>',
         '<div class="modal-footer">',
         '<button type="button" class="btn btn-default" data-dismiss="modal">确认</button>',
         '</div></div></div></div>'].join("");
        boht.innerHTML = alertHtml;
        $('#errorModal').modal('show');
    }
	
	showAlert_nobg = function(str) {
		if(!str){
			str = "网络异常，请稍候再试";
		}
		if (str == "token不存在") {
			window.location.href = "../login/login!index";
			return;
		}
		var boht = document.createElement("span");
		document.body.appendChild(boht);
		boht.id = "boht";
		var alertHtml = '<div id="bg"></div>';
		alertHtml += '<div id="info"><div id="center">';
		alertHtml += '<strong>提示</strong>';
		alertHtml += '<p>' + str + '</p>';
		alertHtml += '<a class="closeAlert" href=javascript:closeAlert();>确认</a>';
		alertHtml += '</div></div>';
		boht.innerHTML = alertHtml;
	}
	showWaitMeAlert= function(str) {
		if(!str){
			str = "网络异常，请稍候再试";
		}
		if (str == "token不存在") {
			window.location.href = "../login/login!index";
			return;
		}
		var boht = document.createElement("span");
		document.body.appendChild(boht);
		boht.id = "boht";
		var alertHtml = '<div id="bg"></div>';
		alertHtml += '<div id="info"><div id="center">';
		alertHtml += '<strong>提示</strong>';
		alertHtml += '<p>' + str + '</p>';
		alertHtml += '<a class="closeAlert" href=javascript:closeWaitMeAlert();>确认</a>';
		alertHtml += '</div></div>';
		boht.innerHTML = alertHtml;
	}
	closeWaitMeAlert = function() {
		for (var int = 0; int < 100; int++) {
			if ($("#boht").length > 0) {
				var boht = document.getElementById("boht");
				document.body.removeChild(boht);
			} else {
				break;
			}
		}
		$("#rectangleId div img").click();
	}
	closeAlert = function() {
		for (var int = 0; int < 100; int++) {
			if ($("#boht").length > 0) {
				var boht = document.getElementById("boht");
				document.body.removeChild(boht);
			} else {
				break;
			}
		}
	}
	
	showblockUI = function() {
		if ($("#boht").length > 0) {
			return;
		}
		var boht = document.createElement("span");
		document.body.appendChild(boht);
		boht.id = "boht";
		var alertHtml = '<div id="bg"></div>';
		alertHtml += '<div id="info" style="top:350px"><div id="center" style="background:transparent;border: none;">';
		alertHtml += '<img src="../images_2.0/loading.gif" style="background: transparent;margin-left: 120px;">';
		alertHtml += '</div></div>';
		boht.innerHTML = alertHtml;
	}
	
	showFancyboxBlockUI = function() {
		if ($("#boht").length > 0) {
			return;
		}
		var boht = document.createElement("span");
		document.body.appendChild(boht);
		boht.id = "boht";
		var alertHtml = '<div id="bg" style="z-index:0;></div>';
		alertHtml += '<div id="info" style="top:350px;"><div id="center" style="background:transparent;border: none;">';
		alertHtml += '</div></div>';
		boht.innerHTML = alertHtml;
	}
	
	showUrlChoose = function(str, url) {
		var boht = document.createElement("span");
		document.body.appendChild(boht);
		boht.id = "boht";
		var alertHtml = '<div id="bg"></div>';
		alertHtml += '<div id="info"><div id="center">';
		alertHtml += '<strong>提示</strong>';
		alertHtml += '<p>' + str + '</p>';
		alertHtml += '<ul id="yn"><li class="yes"><a class="closeAlert" href="' + url + '">是</a></li>';
		alertHtml += '<li class="no" onclick="javascript:closeAlert()">否</li></ul>';
		alertHtml += '</div></div>';
		boht.innerHTML = alertHtml;
	}
	
	showUrlMust = function(str, url) {
		if(!str){
			str = "网络异常，请稍候再试";
		}
		var boht = document.createElement("span");
		document.body.appendChild(boht);
		boht.id = "boht";
		var alertHtml = '<div id="bg"></div>';
		alertHtml += '<div id="info"><div id="center">';
		alertHtml += '<strong>提示</strong>';
		alertHtml += '<p>' + str + '</p>';
		alertHtml += '<a class="closeAlert" href="' + url + '">确认</a>';
		alertHtml += '</div></div>';
		boht.innerHTML = alertHtml;
	}
	
	showEventChoose = function(str, event) {
		var boht = document.createElement("span");
		document.body.appendChild(boht);
		boht.id = "boht";
		var alertHtml = '<div id="bg"></div>';
		alertHtml += '<div id="info"><div id="center">';
		alertHtml += '<strong>提示</strong>';
		alertHtml += '<p>' + str + '</p>';
		alertHtml += '<ul id="yn"><li class="yes" onclick="javascript:closeAlert();' + event + ';">是</li>';
		alertHtml += '<li class="no" onclick="javascript:closeAlert()">否</li></ul>';
		alertHtml += '</div></div>';
		boht.innerHTML = alertHtml;
	}
	
	showEventMust = function(str, event) {
		if(!str){
			str = "网络异常，请稍候再试";
		}
		var boht = document.createElement("span");
		document.body.appendChild(boht);
		boht.id = "boht";
		var alertHtml = '<div id="bg"></div>';
		alertHtml += '<div id="info"><div id="center">';
		alertHtml += '<strong>提示</strong>';
		alertHtml += '<p>' + str + '</p>';
		alertHtml += "<a class='closeAlert' onclick='javascript:closeAlert();" + event + ";' style='cursor:pointer;'>确认</a>";
		alertHtml += '</div></div>';
		boht.innerHTML = alertHtml;
	}
	
	showChooseType = function(str, url) {
		var boht = document.createElement("span");
		document.body.appendChild(boht);
		boht.id = "boht";
		var alertHtml = '<div id="bg"></div>';
		alertHtml += '<div id="info"><div id="center">';
		alertHtml += '<strong>提示</strong>';
		alertHtml += '<p>' + str + '</p>';
		alertHtml += '<ul id="yn"><li class="yes"><a class="closeAlert" href="regist!redirect?skip=password&type=1&' + url + '">个人</a></li>';
		alertHtml += '<li class="no"><a class="closeAlert" href="regist!redirect?skip=password&type=2&' + url + '">企业</a></li></ul>';
		alertHtml += '</div></div>';
		boht.innerHTML = alertHtml;
	}
	showWindowClose = function(str) {
		var boht = document.createElement("span");
		document.body.appendChild(boht);
		boht.id = "boht";
		var alertHtml = '<div id="bg"></div>';
		alertHtml += '<div id="info"><div id="center">';
		alertHtml += '<strong>提示</strong>';
		alertHtml += '<p>' + str + '</p>';
		alertHtml += '<a class="closeAlert" href="javascript:closeWin();">确认</a>';
		alertHtml += '</div></div>';
		boht.innerHTML = alertHtml;
	}
	
	closeWin = function(){
		window.opener=null;      
		window.open('','_self');      
		window.close();    
	}
	
	
	checkStream = function(val)
	{
		var json = JSON.parse(val);
		if (null == json)
		{
		    return null;
		}
		
		if (0 != json.errCode)
		{
			showAlert("服务异常:" + json.errCode + "," + json.msg);
		    return null;
		}
		
		return json;
	}
// });