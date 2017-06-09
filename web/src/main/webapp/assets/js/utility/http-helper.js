
oa.web.utility.HttpHelper = {
   
    ajaxGet : function(url) {
        openLoadingScreen();
        return $.ajax({
            method : "GET",
            url : url,
            contentType : "application/json",
            dataType : "json"
        }).always(function(){
            closeLoadingScreen();
        });
    },
      ajaxPost : function(url, data) {
        openLoadingScreen();
        return $.ajax({
            method : "POST",
            url : url,
            data : JSON.stringify(data),
            contentType : "application/json;",
            dataType : "json"
        }).always(function(){
            closeLoadingScreen();
        });
    }
};


oa.web.utility.HtmlDataInjection = function(settings) {
    this.dataObject;
    this.html;
    this.Settings = {
        data : {},
        html : ""
    };
    $.extend(this.Settings, settings);
    this.initialize();
};
oa.web.utility.HtmlDataInjection.prototype = {
   
    initialize : function() {
        this.dataObject = this.Settings.data;
        this.html = this.Settings.html;
    },
    
    inject : function() {
        var inject = this;
        $.each(this.dataObject, function(key, value) {
            // console.log(key + " " + value);
            inject.replace(key, value);
        });
        return this;
    },
  
    replace : function(key, value) {
        this.html = this.html.split("%" + key + "%").join(value);
    },
   
    setData : function(data) {
        this.dataObject = data;
    },
    
    addData : function(setting) {
        var settings = {
            id : "",
            value : ""
        }
        $.extend(settings, setting);
        this.dataObject[settings.id] = settings.value;
    },
   
    toHtml : function() {
        return this.html;
    }
};