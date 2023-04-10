

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
    },

    fn : {
        modalClose : function(){
            $("#modal").hide();
        },
        modalOpen : function(){
            $("#modal").show();
        }

    }

}
