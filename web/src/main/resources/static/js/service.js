var errorFlag = 0;
var INVALID_MENU_CODE = 10003;

function Service(url) {
    this.rootUrl = url;

    this.errorHandler = function (jqXHR) {
        if (jqXHR.responseJSON != null) {
            var msg = jqXHR.responseJSON.msg;
            if (jqXHR.responseJSON.errCode == INVALID_MENU_CODE) {
                showEventChoose("该套餐已经失效，删除重新购买", "deleteOrderById()");
                return;
            }
            if (msg != null) {
                showModalAlert(msg);
            }
            else {
                showModalAlert("服务器异常");
            }
        }
        else {
            showModalAlert("服务器异常");
        }
    }
    this.errorAlert = function (jqXHR) {
        showModalAlert(jqXHR.responseJSON.msg);
    }
};
Service.prototype = {

    constructor: Service,

    //get jsonP data
    getJsonP: function (param, callback) {
        $.ajax({
            async: false,
            jsonpCallback: 'jsonCallback',
            cache: false,
            type: 'GET',
            url: this.rootUrl + '?' + $.param(param),
            contentType: "application/json",
            dataType: 'jsonp',
            success: callback,
            error: this.errorHandler
        });
    },

    get: function (param, callback, errorCallBack) {
        var errorHandler = this.errorHandler;
        if (errorCallBack) {
            errorHandler = errorCallBack;
        }
        $.ajax({
            cache: false,
            type: 'GET',
            url: this.rootUrl + '?' + $.param(param),
            dataType: "json",
            success: callback,
            error: errorHandler
        });
    },
    //find
    getAll: function (param, callback) {
        $.ajax({
            cache: false,
            type: 'GET',
            url: this.rootUrl + '?' + $.param(param),
            dataType: "json",
            success: callback,
            error: this.errorHandler
        });
    },

    //find
    getAllAsync: function (param, callback) {
        $.ajax({
            cache: false,
            type: 'GET',
            url: this.rootUrl + '?' + $.param(param),
            dataType: "json",
            success: callback,
            async: false,
            error: this.errorHandler
        });
    },

    //find data by id
    getById: function (id, data, callback, errorCallBack) {
        if (typeof data === "function") {
            callback = data;
            data = null;
        }
        $.ajax({
            cache: false,
            type: 'GET',
            url: this.rootUrl + '/' + id,
            data: data,
            dataType: "json",
            success: callback,
            error: this.errorHandler
        });
    },

    getByIdAsync: function (id, data, callback) {
        if (typeof data === "function") {
            callback = data;
            data = null;
        }
        $.ajax({
            cache: false,
            type: 'GET',
            url: this.rootUrl + '/' + id,
            data: data,
            async: false,
            dataType: "json",
            success: callback,
            error: this.errorHandler
        });
    },

    //add
    add: function add(param, callback) {
        $.ajax({
            cache: false,
            type: 'POST',
            contentType: 'application/json',
            url: this.rootUrl,
            dataType: "json",
            data: JSON.stringify(param),
            success: callback,
            error: this.errorHandler
        });
    },

    //update
    update: function (param, callback) {
        return $.ajax({
            cache: false,
            type: 'PUT',
            contentType: 'application/json',
            url: this.rootUrl + '/' + param.id,
            dataType: "json",
            data: JSON.stringify(param),
            success: callback,
            error: this.errorHandler
        });
    },

    //update
    updateEx: function (urlparam, param, callback) {
        return $.ajax({
            cache: false,
            type: 'PUT',
            contentType: 'application/json',
            url: this.rootUrl + '/' + urlparam,
            dataType: "json",
            data: JSON.stringify(param),
            success: callback,
            error: this.errorHandler
        });
    },

    //delete
    remove: function (id, callback) {
        $.ajax({
            cache: false,
            type: 'DELETE',
            url: this.rootUrl + '/' + id,
            success: callback,
            error: this.errorHandler
        });
    },

    /**
     *    invoiceService
     */
    getInvoiceByAccountMenuId: function (id, callback) {
        $.ajax({
            cache: false,
            type: 'GET',
            url: this.rootUrl + '/accountmenu/' + id,
            dataType: "json",
            success: callback,
            error: this.errorHandler
        });
    },
    invoiceExpressUpdate: function (param, callback) {
        $.ajax({
            cache: false,
            type: 'POST',
            contentType: 'application/json',
            url: this.rootUrl + '/' + "update",
            dataType: "json",
            data: JSON.stringify(param),
            success: callback,
            error: this.errorHandler
        });
    },

    /**
     * signLogService
     */
    getReport: function (param, callback) {
        $.ajax({
            cache: false,
            type: 'POST',
            contentType: 'application/json',
            url: this.rootUrl + "/report",
            dataType: "json",
            data: JSON.stringify(param),
            success: callback,
            error: this.errorHandler
        });
    },
    getReportByGroup: function (param, callback) {
        $.ajax({
            cache: false,
            type: 'GET',
            contentType: 'application/json',
            url: this.rootUrl + "/report/usergroup" + '?' + $.param(param),
            dataType: "json",
            success: callback,
            error: this.errorHandler
        });
    },
    getAccountTags: function (callback) {
        $.ajax({
            cache: false,
            type: 'GET',
            contentType: 'application/json',
            url: this.rootUrl + "/getTags",
            dataType: "json",
            success: callback,
            error: this.errorHandler
        });
    }
};
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

(function ($) {
    $.getUrlVars = function () {
        var vars = {},
            hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for (var i = 0; i < hashes.length; i++) {
            hash = hashes[i].split('=');
            vars[hash[0]] = hash[1];
        }
        return vars;
    }
})(jQuery);