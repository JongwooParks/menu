

var menu = {

    init : function(){
        this.beforeBind();
        this.bind();
    },

    beforeBind : function(){
        this.fn.modalClose();
        menu.fn.showAllMenu();
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
        $(".tb_col").on("click",".menu_delete_btn",function (){
            menu.fn.delete(this);
        })
        $("#selectTodayMenu").on("click",function (){
            menu.fn.selectTodayMenu();
        })
    },

    fn : {
        modalClose : function(){
            $("#modal").hide();
        },
        modalOpen : function(){
            $("#modal").show();
        },
        leftPad : function (value) {
            if (value >= 10) {
                return value;
            }
            return `0${value}`;
        },
        parseFormatDate: function(obj){
            date = new Date(obj);
            delimiter = '-';
            year =  date.getFullYear();
            month = menu.fn.leftPad(date.getMonth()+1);
            day = menu.fn.leftPad(date.getDate());

            return [year, month, day].join(delimiter);
        },
        showAllMenu : function(){
            $.ajax({
                url:'/menu/show',
                type:'get',
                success : function (data, statusText, jqXHR){
                    if(data){
                        let text = '';
                        text += '<tbody>'
                        text +=`<tr class="tb_head">`
                        text +=`<th>이름</th>`
                        text +=`<th>카테고리</th>`
                        text +=`<th>최근 선택 날짜</th>`
                        text +=`<th>제외 대상</th>`
                        text +=`<th>등록 날짜</th>`
                        text +=`<th></th>`
                        text +=`</tr>`
                        $(data).each((i,item)=>{

                            text += `<tr class="tb_list">`;
                            text += `<td>`+item.menuName+`</td>`
                            text += `<td>`+item.menuCategory+`</td>`
                            if(!item.selectDate | item.selectDate == null | item.selectDate =='null'){
                                text += `<td></td>`
                            }else{
                                text += `<td>`+menu.fn.parseFormatDate(item.selectDate)+`</td>`
                            }
                            if(!item.exceptSelect | item.exceptSelect == null | item.exceptSelect =='null'){
                                text += `<td></td>`
                            }else{
                                text += `<td>`+item.exceptSelect+`</td>`
                            }
                            text +=`<td>`+item.regDt+`</td>`
                            text +=`<td><input type="button" class="menu_delete_btn" data-id="`+item.menuId+`" value="삭제"></td>`
                            text += `</tr>`;
                        })
                        text += '</tbody>'
                        $(".tb_col").html(text);
                    }
                },
                error : function (jqXHR, textStatus, errorThrown){
                    alert('오류가 발생하였습니다. 해당 원인 : '+jqXHR.responseText);
                }

            })
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
                    if(data==1){
                        $("input[name='menuName']").val("");
                        $('#menuCategory').val('none').prop("selected",true);
                        menu.fn.modalClose();
                        menu.fn.showAllMenu();
                    }
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
        },
        delete : function(obj){
            console.log(obj);
            let id = $(obj).data("id");
            $.ajax({
                url:"/menu/"+id,
                type:'delete',
                success : function (data, statusText, jqXHR){
                    if(data==0){
                        alert("삭제가 완료되었습니다.")
                        menu.fn.showAllMenu();
                    }
                },
                error : function (jqXHR, textStatus, errorThrown){
                    alert('오류가 발생하였습니다. 해당 원인 : '+jqXHR.responseText);
                }
            })

        },

        selectTodayMenu : function(){
            $.ajax({
                url:"/menu/choice",
                type:"get",
                success : function (data, statusText, jqXHR){
                    if(data[0]){
                        alert("로직 오류 발생... 신속히 해결하겠습니다.")
                        return;
                    }
                    if(data[1]){
                        $("#choiceMenuName").html(data[1].menuName);
                        menu.fn.showAllMenu();
                    }

                },
                error : function (jqXHR, textStatus, errorThrown){
                        alert('오류가 발생하였습니다. 해당 원인 : '+jqXHR.responseText);
                }
            })
        }
    }

}

/*
success : function (data, statusText, jqXHR){
},
error : function (jqXHR, textStatus, errorThrown){
    alert('오류가 발생하였습니다. 해당 원인 : '+jqXHR.responseText);
}*/
