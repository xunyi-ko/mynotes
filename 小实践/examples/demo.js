var layer
var $sigdiv
var thisId = 1;
(function($) {
	layui.use(['layer'],function(){
		layer = layui.layer;
	})
})(jQuery);

$(document).ready(function() {
	
	// 初始化
    var content = $("#content")[0];
    var width = content.clientWidth;
    var height = width * $(document).width() / $(document).height();
    
	$sigdiv = $("#signature").jSignature({'UndoButton':true,height:height + "px",width:width + "px"});
	
	var commit = $("#commit");
	commit.bind("click",function(e){
		var data = $sigdiv.jSignature('getData', "default")
		
		if (typeof data === 'string'){
			data = data;
		} else if($.isArray(data) && data.length === 2){
			data = data.join(',');
		} else {
			try {
				data = JSON.stringify(data);
			} catch (ex) {
				data = "";
			}
		}
		
	});
	var canvas = $sigdiv.jSignature("getCanvas")[0];
    var context = canvas.getContext("2d");
	// 重置按钮
	$('#reset').bind('click', function(e){
		$sigdiv.jSignature('reset')
	});
	
	// 全屏
	$("#full-screen").bind("click",function(e){
		width = $(window).width();
		height = $(window).height();
		/*$("#canvas").css("style","height:"+height + "px;" + "width:" + width + "px;" );
		
		var canvas = $("#canvas");*/
		
		// window.location.href = "../examples/fullScreen.html";
		var formData = new FormData();
		formData.append("dataUrl",canvas.toDataURL());
		formData.append("thisId",thisId);
		formData.append("fileType","png");
		
//		var index = layer.load({
//		    
//		})
		
		$.ajax({
            type: "POST",
		    url:"../../file/savePhoto",
		    data: formData,
		    processData : false, // 告诉jQuery不要去处理发送的数据
	        contentType : false, // 告诉jQuery不要去设置Content-Type请求头
	        cache:false,
	        dataType:"json",
	        async:false,
	        success:function(res){
	            if(res.code == 0){
//	                window.location.href = "../examples/fullScreen.html";
	                layer.open({
	                    type : 2,
	                    area : [width+"px",height + "px"],
	                    content : "../examples/fullScreen.html?thisId="+thisId,
	                    closeBtn : 0,
	                    title: false,
	                    end : function(){
	                        if($("#flag").val()){
    	                        var src = "../../images/photos/_photo" + thisId + ".png";
    	                        
    	                        drawImage($sigdiv,src)
	                        }
	                    }
	                })
	            }else{
	                
	            }
	        }
		})
	});
})

function drawImage($sigdiv,src){
    // 清空画布
    $sigdiv.jSignature("reset");
    // 获取组件的canvas
    var c = $sigdiv.jSignature("getCanvas")[0];
    var context = c.getContext("2d");
    // 旋转角度
    var angle =  - 90 * Math.PI/180;
    var img = new Image()
    img.src = src;
    img.onload = function () {
        var ctx = c.getContext("2d");
        
        // 背景
        var oldShadowColor = ctx.shadowColor;
        ctx.shadowColor = "transparent";
        var iw = img.width,ih = img.height,cw = c.width,ch = c.height, ratio = iw / ih;;
        var imgLength = {width : iw, height: ih}, canvasLength = {width : cw, height : ch};
        scale = calcScale(imgLength, canvasLength);
        
        ctx.scale(scale,scale);
        // 移动画布中心
        ctx.translate((cw/2),(ch/2));
        
        // 旋转
        ctx.rotate(angle);
        ctx.drawImage(
            img, -(1*cw)/scale, -(ch/2)/scale
            , ( img.width < c.width) ? img.width : c.width
            , ( img.height < c.height) ? img.height : c.height
        );
        ctx.rotate(-angle);
        ctx.translate(-(cw/2),(-ch/2));
        ctx.scale(1/scale,1/scale);
        ctx.shadowColor = oldShadowColor;
    };
}

function getDataurl(formattype){
    return $sigdiv.jSignature("getData",formattype);
}

