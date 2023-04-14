

var menu = {

    init : function(){
        this.beforeBind();
        this.bind();
    },

    beforeBind : function(){
        this.fn.modalClose();
    },

    bind : function(){
        $("#register_menu").on("click",function (){
           menu.fn.modalOpen();
        });

        $(".close-area").on("click",function(){
            $("input[name='menuName']").val("");
            $('#menuCategory').val('none').prop("selected",true);
            menu.fn.modalClose();
        })
        $("#menuRegisterBtn").on("click",function(){
            menu.fn.registerMenu();
        })
        $("input[name='menuName']").on('change',function(){
            menu.fn.validate();
        })
    },

    fn : {
        modalClose : function(){
            $("#modal").hide();
        },
        modalOpen : function(){
            $("#modal").show();
        },
        registerMenu: function(){
            let menuName = $("input[name='menuName']").val().trim();
            let menuCategory = $("#menuCategory").val().trim();
            if(menuCategory == 'none'){
                alert('카테고리를 선택해주세요.')
                return;
            }
            $.ajax({
                url:'/menu/register',
                type:'post',
                data:{menuName:menuName,menuCategory:menuCategory},
                success : function (data, statusText, jqXHR){

                },
                error : function (jqXHR, textStatus, errorThrown){
                    alert('오류가 발생하였습니다. 해당 원인 : '+jqXHR.responseText);
                }
            })
        },
        validate: function(){
            let menuName = $("input[name='menuName']").val().trim();
            if(menuName.trim()=='' | menuName.trim() == undefined){
                alert('메뉴 이름은 빈칸일 수 없습니다.');
                return;
            }
            $.ajax({
                url:'/menu/validate',
                data:{menuName:menuName},
                type: 'put',
                success : function (data, statusText, jqXHR){
                    if(data==2){
                        alert('같은 이름의 메뉴가 이미 등록되어 있습니다.')
                        $("input[name='menuName']").focus();
                        return;
                    }
                },
                error : function (jqXHR, textStatus, errorThrown){
                    alert('오류가 발생하였습니다. 해당 원인 : '+jqXHR.responseText);
                }
            })
        }

    }

}
