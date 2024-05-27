<%--
  Created by IntelliJ IDEA.
  User: Seungjin
  Date: 2024-05-07
  Time: 오후 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />
<html>
<head>
    <title>캘린더</title>
<%--    <script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.js"></script>--%>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/index.global.min.js'></script>
    <script type="text/javascript">

        var diaLogOpt={
            modal:true
            ,resizable:false
            ,width:"570"
            ,height:"470"
        };
        var calFunc={
            calcDate: function(arg,calendar){
                var rObj = new Object();
                var start = null;
                var end= null;
                var allDay = arg.allDay;
                var startDisp =null;
                var endDisp = null;
                // var id = null;
                var xcontent = null;
                var title = null;


                // if(arg.id!=""&& arg.id!=null&&arg.id!=undefined) id=arg.id;
                if(arg.title!=""&& arg.title!=null&&arg.title!=undefined)title=arg.title;
                if(arg.extendedProps!=undefined){
                    if(arg._def.extendedProps.xcontent!=""&&arg._def.extendedProps.xcontent!=null&&arg._def.extendedProps.xcontent!=undefined){
                        xcontent=arg._def.extendedProps.xcontent;
                    }
                }


                start = arg.start.toISOString();
                if(arg.end!=""&& arg.end!=null && arg.end!=undefined){
                    end= arg.end.toISOString();
                }
                startDisp = returnCdate(calendar,arg.start);
                if(end!=null) endDisp = returnCdate(calendar,arg.end);

                rObj.start=start;
                rObj.end=end;
                rObj.start=start;
                rObj.startDisp=startDisp;
                rObj.endDisp=endDisp;
                // rObj.id=id;
                rObj.xcontent=xcontent;
                rObj.title=title;

                return rObj;
            },


//등록초기
            setDateRangeView :function(xobj){
                var dispStr = xobj.startDisp;
                if(xobj.endDisp!=null) dispStr+="~"+xobj.endDisp;

                $("form#diaForm").find("input[name='xdate']").val(dispStr);
                $("form#diaForm").find("input[name='start']").val(xobj.start);
                $("form#diaForm").find("input[name='end']").val(xobj.end);
                $("form#diaForm").find("input[name='actType']").val("C");
            },


            getFormValue :function(){
                var $dForm =$("form#diaForm");
                var $obj = new Object();
                $("form#diaForm").find("input,textarea").each(function(){
                    var xval = $(this).val();
                    $obj[$(this).attr("name")]=xval;
                });
                return $obj;
            },

            //모든 태그 비활성화
            formDsbTrue :function(){
                $("form#diaForm").find("input,textarea").each(function(){
                    $(this).attr("disabled",true);
                });
            },


            //모든 태그 활성화
            formDsbFalse :function(){
                $("form#diaForm").find("input,textarea").each(function(){
                    $(this).attr("disabled",false);
                });
            },

            //데이터 조회
            setDataForm:function(xobj){
                var dispStr = xobj.startDisp;
                if(xobj.endDisp!=null) dispStr+="~"+xobj.endDisp;

                $("form#diaForm").find("input[name='xdate']").val(dispStr);
                $("form#diaForm").find("input[name='start']").val(xobj.start);
                $("form#diaForm").find("input[name='end']").val(xobj.end);
                $("form#diaForm").find("input[name='actType']").val("U");


                // $("form#diaForm").find("input[name='id']").val(xobj.id);
                $("form#diaForm").find("input[name='title']").val(xobj.title);
                $("form#diaForm").find("textarea[name='xcontent']").val(xobj.xcontent);
            }
        };
        //calFunc[e]




        //등록 액션
        function createClnd(cal,xobj){
            if(!confirm("일정을 등록 하시겠습니까?")) return false;
            var $obj = calFunc.getFormValue();

            $.ajax({
                url:"/calendar/calendarInsert",
                type: "POST",
                dataType:"json",
                data: JSON.stringify($obj),
                contentType : 'application/json; charset=utf-8'
            }).done(function(data) {
                var result = JSON.stringify(data);
                cal.refetchEvents();
            }).fail(function() {
                cal.refetchEvents();

            });


        }


        //수정액션
        function updateClnd(cal,xobj,event){
            if(!confirm("해당일정을 정말로 수정 하시겠습니까?")){
                if(event!=undefined)event.revert();
                return false;
            }
            var $obj = calFunc.getFormValue();

            $.ajax({
                url:"/calendar/calendarUpdate",
                type:"POST",
                dataType:"json",
                data:JSON.stringify($obj),
                contentType : 'application/json; charset=utf-8'
            }).done(function(data) {
                var result = JSON.stringify(data);
                cal.refetchEvents();
            }).fail(function() {

                cal.refetchEvents();

            });
        }

        //삭제액션
        function deleteClnd(cal,xobj){
            if(!confirm("해당일정을 정말로 삭제 하시겠습니까?")) return false;

            var $obj = calFunc.getFormValue();
            $.ajax({
                url:"/calendar/calendarDelete",
                type: "POST",
                dataType:"json",
                data:JSON.stringify($obj),
                contentType : 'application/json; charset=utf-8'
            }).done(function(data) {
                var result =JSON.stringify(data);
                cal.refetchEvents();
            }).fail(function(){
                cal.refetchEvents();
            });
        }




        //달력 생성

        document.addEventListener('DOMContentLoaded', function() {
            var calendarEl = document.getElementById('calendar');

            var calendar = new FullCalendar.Calendar(calendarEl,{
                headerToolbar: {
                    left: 'prev next today',
                    center:'title',
                    right:'dayGridMonth,timeGridWeek,listWeek'
                },
                // allDay:false,
                // theme: true,
                themeSystem:'bootstrap',
                locale:'ko',
                timeZone:'Asia/Seoul',
                navLinks: true,
                selectable:true,
                selectMirror:true,
                allDaySlot:false,

                select: function(arg){


                    var xObj = calFunc.calcDate(arg,calendar);

                    var btnOpt ={
                        "저장":function(){
                            createClnd(calendar,xObj);
                            $(this).dialog("close");
                        },"취소":function(){
                            $(this).dialog("close");
                        }
                    }
                    var dOpt = diaLogOpt;
                    dOpt.buttons = btnOpt;
                    $("#name").val("");
                    $("#comment").val("");

                    //================dialog 옵션 추가===================
                    calFunc.formDsbFalse();
                    $('#dialog').dialog(dOpt);
                    calFunc.setDateRangeView(xObj);

                    calendar.unselect();
                },


                //클릭 함수
                eventClick: function(calEvent, jsEvent){

                    var xObj= calFunc.calcDate(calEvent.event,calendar);
                    var btnOpt ={
                        "삭제":function(){
                            deleteClnd(calendar,xObj);
                            $(this).dialog("close");
                        },"수정":function(){
                            updateClnd(calendar,xObj);
                            $(this).dialog("close");
                        },"닫기":function(){
                            $(this).dialog("close");
                        }
                    }

                    calFunc.formDsbFalse();


                    var dOpt=diaLogOpt;
                    dOpt.buttons = btnOpt;

                    $('#dialog').dialog(dOpt);
                    calFunc.setDataForm(xObj);


                },

                //클릭 함수[e]



                dayMaxEvents:true,


                events: function(fetchInfo, successCallback, failureCallback){
                    var start=fetchInfo.start.toISOString().slice(0,10);
                    var end =fetchInfo.end.toISOString().slice(0,10);
                    var param ="";
                    param+="start="+start;
                    param+="&end="+end;
                    $.ajax({
                        url:"/calendar/calendarList",
                        type:"GET",
                        dataType:"json",
                        data:param
                    }).done(function(data){

                        successCallback(data);
                    }).fail(function(){
                        alert("로그인 후 이용해주세요");
                        location.href = '/login';
                    }).always(function(){

                    });

                },

                eventDrop:function(info){
                    var xObj=calFunc.calcDate(info.event,calendar);
                    calFunc.setDataForm(xObj);
                    updateClnd(calendar,xObj,info);
                },
                eventResize: function(info){
                    var xObj=calFunc.calcDate(info.event,calendar);
                    calFunc.setDataForm(xObj);
                    updateClnd(calendar,xObj,info);
                },
                eventTimeFormat:{
                    hour:'2-digit',
                    minute:'2-digit',
                    hour12:false
                },

            });

            calendar.render();


            $("span.fa-chevron-left").html("이전달");
            $("span.fa-chevron-right").html("다음달");
        });
        //달력생성[e]


        //특정일자하루전
        function dateRel(date){
            var selectDate =date.split("-");
            var changeDate = new Date();
            changeDate.setFullYear(selectDate[0], selectDate[1]-1, selectDate[2]-1);

            var y = changeDate.getFullYear();
            var m = changeDate.getMonth() +1;
            var d=changeDate.getDate();

            if(m < 10){
                m = "0" + m;
            }

            if(d < 10){
                d = "0" + d;
            }
            var resultDate = y + "-" + m +"-" +d;
            return resultDate;
        }

        function returnCdate(cal,time){
            return cal.formatDate(time,{month: 'long',year:'numeric',day:'numeric',hour:'numeric',minute:'numeric',timeZone:'Asia/Seoul',locale:'ko'});
        }


    </script>
    <style>
        body {
            font-size: 14px;
            margin:0 auto;
        }

        table {
            text-align: left;
        }


        #calendar {
            max-width: 1100px;
            margin: 40px auto;
            padding: 0 10px;
        }

        #dialog {
            font-size: 14px;
        }

        /* 폼 컨테이너 스타일 */
        #form-div {
            padding: 20px;
        }

        /* 입력 필드 및 텍스트 영역 스타일 */
        input[type="text"],
        input[type="hidden"],
        textarea {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }

        /* 필드의 placeholder 스타일 */
        input::placeholder,
        textarea::placeholder {
            color: #aaa;
        }

        /* 텍스트 영역 스타일 */
        textarea {
            height: 100px;
            resize: vertical;
        }

        /* 폼 필드 그룹 스타일 */
        p {
            margin: 0 0 15px 0;
        }

        /* 필드 컨테이너 클래스 스타일 */
        .name, .email, .text {
            margin-bottom: 15px;
        }

        /* 입력 필드에 포커스가 갔을 때 스타일 */
        input:focus,
        textarea:focus {
            border-color: #66afe9;
            outline: none;
            box-shadow: 0 0 8px rgba(102, 175, 233, 0.6);
        }

        /* 버튼 스타일 */
        .ui-dialog-buttonpane .ui-dialog-buttonset button {
            padding: 6px 12px;
            font-size: 14px;
            margin: 5px;
        }

    </style>
</head>
<body>

<jsp:include page="/WEB-INF/views/layout/boardmenu.jsp"/>

    <div id="contents">
        <div id="dialog" title="일정 관리" style="display:none;">
            <div id="form-div">
                <form class="diaForm" id="diaForm">
                    <input type="hidden" name="actType" value="C"/>
                        <%--                <input type="hidden" name="id" value=""/>--%>
                    <input type="hidden" name="start" value=""/>
                    <input type="hidden" name="end" value=""/>

                    <p class="name">
                        <input name="title" type="text"
                               class="validate[required,custom[onlyLetter],length[0,100]] feedback-input"
                               placeholder="일정타이틀" id="name"/>
                    </p>

                    <p class="email">
                        <input name="xdate" type="text" readonly="readonly"
                               class="validate[required,custom[email]] feedback-input" placeholder="선택된날짜 및 시간"/>
                    </p>

                    <p class="text">
                    <textarea name="xcontent" class="validate[required,length[6,300]] feedback-input" id="comment"
                              placeholder="일정내용"></textarea>
                    </p>
                </form>
            </div>
        </div>
        <div id="calendar"></div>
    </div>

    <script>
        // 기본 위치(top)값
        var floatPosition = parseInt($("#external-events2").css('top'))

        // scroll 인식
        $(window).scroll(function () {
            // 현재 스크롤 위치
            var currentTop = $(window).scrollTop();
            var bannerTop = currentTop + floatPosition + "px";

            //이동 애니메이션
            $("#external-events2").stop().animate({
                "top": bannerTop
            }, 500);
        }).scroll();
    </script>
</body>
</html>
