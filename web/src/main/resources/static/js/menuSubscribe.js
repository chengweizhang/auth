$(document).ready(function () {
    var menuId = "";
    var subscribeService = new Service("../service/bs/accountmenus/subscribe");
    $('#updateDiv').on("click", '#subscribe', function (event) {
        var callback = function (data) {
            window.open("charge-pay.html?id=" + data.data, "_self");
        }
        subscribeService.update({id: menuId}, callback);
    });

    $('#target').on("click", '#aliPay', function (event) {
        menuId = $(event.currentTarget).parent().data("id");
        $("#updateDiv").load("temp.html", function () {
            $('#updateDiv').modal('show');
        });
    });
    $("#searchName").on("blur",function(){
        if($(this).val()){
            var type = $('#typeSelect option:selected') .val();
            if(type == 1 || !type){
                $("#typeSelect").html('<option value ="1" selected="selected">个人</option> <option value="2">企业</option>');
            }else{
                $("#typeSelect").html('<option value ="1">个人</option> <option value="2" selected="selected">企业</option>');
            }
        }else{
            $("#typeSelect").html('<option value="">全部</option>' +  $("#typeSelect").html());
        }
    })
});