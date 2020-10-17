$(document).ready(function(){
        $("[value=GETsubmit]").click(function(){
            $.ajax({
                type: "POST",
                url: "TestDB?name="+$("[name=name]").val()+"&pwd="+$("[name=pwd]").val()+"&date="+new Date().getTime(),
                dataType: "text",
                success: function(data){
                    if(data=="true"){
                        $("#show").html("===ok==="+"<br/>"+"name="+$("[name=name]").val()+"<br/>"+"pwd="+$("[name=pwd]").val());
                    }else if(data=="false"){
                        $("#show").html("==資訊不符合==")
                        window.onload("index.jsp");
                    }
                },
                error: function(){
                    $("#show").html("Error XMLHttpRequest");
                }
            });
        });
    });
