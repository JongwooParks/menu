

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
            menu.fn.insertMenu();
        })
    },

    fn : {
        modalClose : function(){
            $("#modal").hide();
        },
        modalOpen : function(){
            $("#modal").show();
        },
        insertMenu: function(){
            let menuName = $("input[name='menuName']").val().trim();
            let menuCategory = $("#menuCategory").val().trim();

        }

    }

}
