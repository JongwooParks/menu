

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
