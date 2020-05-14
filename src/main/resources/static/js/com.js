/**
 * Created by 书一 on 2019/7/28.
 */
$.extend({
    url:{
        base:"http://localhost:8087"
    },
    method:{
        A:"POST",
        R:"DELETE",
        M:"PUT",
        F:"GET"
    },
    axcall(url,method,param,callback){
        $.ajax({
            url:$.url.base+url,
            method:method,
            data:param,
            dataType:"json",
            success:function (data) {
                // console.log(data.code);
                callback(data);
            },
            error:function () {
                // alert("请求失败");
            }
        })
    },
    axupload(url,param,callback){
        $.ajax({
            url:$.url.base+url,
            method:$.method.A,
            contentType:false,
            processData:false,
            cache:false,
            data:param,
            success:function (data) {
                // console.log(data.code);
                callback(data);
            },
            error:function () {
                alert("文件上传失败");
            }
        })
    },
    getUuid(){
        let uuid = localStorage.getItem("uuid");
        if(null == uuid){
            alert("请登录");
            window.location.href="/page/login";
        }else{
            return uuid;
        }
    },
    getVipid(callback){
        let uuid = $.getUuid();
        $.axcall(`/vip/vipInfo/uuid/${uuid}`,$.method.A,{},function (data) {
            let phone = data.phone;
            if("fail"==phone){
                alert("请登录");
                window.location.href="/page/login";
                return;
            }
            callback(phone);
        })
    }
})