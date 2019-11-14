function calcScale(imgLength,canvasLength){
    var iw = imgLength.width, ih = imgLength.height;
    var cw = canvasLength.width, ch = canvasLength.height;
    var scaleWidth = iw / cw, scaleHeight = ih / ch;
    
    return scaleWidth > scaleHeight ? scaleWidth : scaleHeight;
}

function GetUrlParam(paraName) {
    var url = document.location.toString();
    var arrObj = url.split("?");
    
    if (arrObj.length > 1) {
        var arrPara = arrObj[1].split("&");
        var arr;

        for (var i = 0; i < arrPara.length; i++) {
            arr = arrPara[i].split("=");
            if (arr != null && arr[0] == paraName) {
                return arr[1];
            }
        }
        return "";
    }
    else {
        return "";
    }
}

function cancel(){
    window.history.back(-1);
}