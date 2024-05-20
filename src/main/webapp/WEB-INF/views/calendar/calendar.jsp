<%--
  Created by IntelliJ IDEA.
  User: Seungjin
  Date: 2024-05-07
  Time: 오후 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.11/index.global.min.js'></script>
    <script type="text/javascript">

        var calendar = null;
        var initialLocaleCode = 'ko';
        var localeSelectorEl = document.getElementById('locale-selector');

        $(document).ready(function (){

            $(function () {
                var request = $.ajax({
                    url: "/calendar/calendarList",
                    method: "GET",
                    dataType: "json"
                });
                request.done(function (data) {
                    console.log(data); // log 로 데이터 찍어주기.
                    var calendarEl = document.getElementById('calendar');
                    calendar = new FullCalendar.Calendar(calendarEl, {
                        initialView: 'dayGridMonth',
                        headerToolbar: {
                            left: 'prev,next today',
                            center: 'title',
                            right: 'dayGridMonth,timeGridWeek,listWeek'
                        },
                        allDaySlot:false,
                        navLinks: true,
                        editable: true,
                        selectable: true,
                        droppable: true, // this allows things to be dropped onto the calendar


                        /**
                         * 드래그로 이벤트 수정하기
                         */
                        eventDrop: function (info){

                            if(confirm("'"+ info.event.title +"' 일정을 수정하시겠습니까 ?")){

                                var events = new Array(); // Json 데이터를 받기 위한 배열 선언
                                var obj = new Object();

                                obj.title = info.event._def.title;
                                obj.start = info.event._instance.range.start;
                                obj.end = info.event._instance.range.end;

                                obj.oldTitle = info.oldEvent._def.title;
                                obj.oldStart = info.oldEvent._instance.range.start;
                                obj.oldEnd = info.oldEvent._instance.range.end;

                                events.push(obj);

                                console.log(events);
                            }else{
                                location.reload();
                            }
                            $(function modifyData() {
                                $.ajax({
                                    url: "/calendar/calendarUpdate",
                                    method: "POST",
                                    dataType: "json",
                                    data: JSON.stringify(events),
                                    contentType: 'application/json',
                                })
                            })


                        },


                        /**
                         * 드래그로 이벤트 추가하기
                         */
                        select: function (arg) { // 캘린더에서 이벤트를 생성할 수 있다.

                            var title = prompt('일정 이름을 입력해주세요.');
                            if (title) {
                                var content = prompt('일정 내용을 입력하세요');
                                if(content){
                                    calendar.addEvent({
                                        title: title,
                                        content : content,
                                        start: arg.start,
                                        end: arg.end,
                                        allDay: arg.allDay

                                    })
                                }else {
                                    alert("일정 내용을 입력하세요");
                                    return false;
                                }
                            }else {
                                alert("일정 이름을 입력하세요");
                                return false;
                            }

                            console.log(arg);

                            var events = new Array(); // Json 데이터를 받기 위한 배열 선언
                            var obj = new Object();     // Json 을 담기 위해 Object 선언

                            obj.title = title; // 이벤트 명칭  ConsoleLog 로 확인 가능.
                            obj.content = content;
                            obj.start = arg.start; // 시작
                            obj.end = arg.end; // 끝
                            events.push(obj);
                            var jsondata = JSON.stringify(events);
                            console.log("jsondata" + jsondata);


                            $(function saveData(jsondata) {
                                $.ajax({
                                    url: "/calendar/calendarInsert",
                                    method: "POST",
                                    dataType: "json",
                                    data: JSON.stringify(events),
                                    contentType: 'application/json',
                                })
                                calendar.unselect()
                            });
                        },

                        /**
                         * 이벤트 선택해서 삭제하기
                         */
                        eventClick: function (info){
                            if(confirm("'"+ info.event.title +"' 일정을 삭제하시겠습니까 ?")){
                                // 확인 클릭 시
                                info.event.remove();


                                console.log(info.event);
                                var events = new Array(); // Json 데이터를 받기 위한 배열 선언
                                var obj = new Object();
                                obj.title = info.event._def.title;
                                obj.start = info.event._instance.range.start;
                                obj.end = info.event._instance.range.end;
                                events.push(obj);

                                console.log(events);
                            }
                            $(function deleteData(){
                                $.ajax({
                                    url: "/calendar/calendarDelete",
                                    method: "POST",
                                    dataType: "json",
                                    data: JSON.stringify(events),
                                    contentType: 'application/json',
                                })
                            })
                        },
                        locale: 'ko',
                        // eventRemove: function (obj) { // 이벤트가 삭제되면 발생하는 이벤트
                        //
                        // },
                        events: data
                    });
                    calendar.render();
                });

            });

        });


    </script>
    <style>
        body {
            font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
            font-size: 14px;
            margin:0 auto;
        }

        table {
            text-align: left;
        }


        .access_user {
            width: 300px;
            height: 100px;
            float: right;
            margin-right: 100px;
            padding-top: 50px;
        }

        #external-events {
            position: absolute;
            /*left: 100 px;*/
            /*top: 100px;*/
            width: 150px;
            overflow: hidden;
            padding: 0 10px;
            margin-top: 80px;
            border: 1px solid #ccc;
            background: #eee;
            text-align: left;
        }

        #external-events h4 {
            font-size: 16px;
            margin-top: 0;
            padding-top: 1em;
        }

        #external-events .fc-event {
            margin: 3px 0;
            cursor: move;
        }

        #external-events p {
            margin: 1.5em 0;
            font-size: 11px;
            color: #666;
        }

        #external-events p input {
            margin: 0;
            vertical-align: middle;
        }


        #calendar {
            max-width: 1100px;
            margin: 40px auto;
            padding: 0 10px;
        }
    </style>
</head>
<body>

<th:block layout:fragment="ground-wrap">
    <div class="ground">
        <div class="main">
            <div id='calendar-wrap'>
                <div id='calendar'></div>
            </div>
        </div>
    </div>
    </div>



    <script>
        // 기본 위치(top)값
        var floatPosition = parseInt($("#external-events").css('top'))

        // scroll 인식
        $(window).scroll(function () {
            // 현재 스크롤 위치
            var currentTop = $(window).scrollTop();
            var bannerTop = currentTop + floatPosition + "px";

            //이동 애니메이션
            $("#external-events").stop().animate({
                "top": bannerTop
            }, 500);
        }).scroll();
    </script>
</th:block>
</body>
</html>
