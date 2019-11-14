$(document).ready(function() {
    layui.use(['layer'],function(){
        layer = layui.layer;
    })
    
    
    // 初始化
    var $sigdiv = $("#signature").jSignature({'UndoButton':true})
//    var args = {
////        rotate : -90 * Math.PI / 180,
////        scale : [1.5,1.5],
//    };
//    
    // 用户是否存在
    var thisId = GetUrlParam("thisId");
    if(!thisId){
        layer.ready(function(){
            layer.msg("用户不存在");
        })
    }
    
    // 重绘签名
//    var img = new Image();
//    img.src = "../../images/photos/_photo"+thisId+".png";
//    img.onload = function(){
//        var thisCanvas = document.createElement("canvas");
//        var thisContext = thisCanvas.getContext("2d");
//        thisContext.drawImage(img,0,0,img.width,img.height);
//        
//        var dataurl = thisCanvas.toDataURL();
//    $sigdiv.jSignature("setData", "../../images/photos/_photo"+thisId+".png");
//    }
    var args = {
        angle : 90 * Math.PI / 180,
    };
    drawImage($sigdiv,"../../images/photos/_photo"+thisId+".png",args);
    // 确定
    $("#compelete").bind("click",function(e){
        var formData = new FormData();
        var dataurl = $sigdiv.jSignature("getData","default");
        formData.append("dataUrl",dataurl)
        formData.append("thisId",thisId);
        formData.append("fileType","png");
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
                    var dom = window.parent.document.getElementById('flag')
                    dom.value=1;
                    parent.layer.closeAll();
                }else{
                    layer.msg("签名保存错误");
                }
            }
        })
    })
    
    // 重置按钮
    $('#reset').bind('click', function(e){
        $sigdiv.jSignature('reset')
    });
    
})

function drawImage($sigdiv, src, args){
    $sigdiv.jSignature("reset");
    var c = $sigdiv.jSignature("getCanvas")[0];
    var context = c.getContext("2d");
    var angle = args.angle;
    var img = new Image()
    // this = Canvas DOM elem. Not jQuery object. Not Canvas's parent div.
//  img.src = 'data:' + formattype + ',' + data;
    img.src = src;
    img.onload = function () {
        var ctx = c.getContext("2d");
        var oldShadowColor = ctx.shadowColor;
        ctx.shadowColor = "transparent";
//      ctx.scale(1.5,1.5);
//      debugger;
        var iw = img.width,ih = img.height,cw = c.width,ch = c.height, ratio = iw / ih, scale = 1;
//        if(iw < cw){
//            iw = cw;
//            ih = iw / ratio;
//        }
//        if(ih < ch){
//            ih = ch;
//            iw = ih * ratio;
//        }
        var imgLength = {width : iw, height: ih}, canvasLength = {width : cw, height : ch};
        scale = calcScale(imgLength, canvasLength);
        
//        ctx.scale(scale,scale);
        
        // 移动画布中心
        ctx.translate((cw/2),(ch/2));
        
        // 旋转
        ctx.rotate(angle);
        ctx.drawImage(
            img, -(cw/4), -(ch/4)
            , ( img.width < c.width) ? img.width : c.width
            , ( img.height < c.height) ? img.height : c.height
        );
        ctx.rotate(-angle);
        ctx.translate(-(cw/2),(-ch/2));
//        ctx.scale(1/scale,1/scale);
        ctx.shadowColor = oldShadowColor;
    };
}
