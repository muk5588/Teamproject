<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: c
  Date: 24. 5. 2.
  Time: 오전 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script>
        var x =
        ${x}
        var y =
        ${y}
        var url = "https://api.openweathermap.org/data/2.5/weather?lat=" + y +
            "&lon=" + x +
            "&units=metric&appid=e776c451f21037ecc76b1a9ecf704f77";
        $.getJSON(url,
            function (result) {
                var now = (result.main.temp).toString().split('.')[0] + "℃";
                var low = (result.main.temp_min).toString().split('.')[0] + "℃";
                var high = (result.main.temp_max).toString().split('.')[0] + "℃";

                //기온출력
                $('.Nowtemp').append(now);
                $('.Lowtemp').append(low);
                $('.Hightemp').append(high);
                $('.Icon').append(result.weather[0].icon);


                //날씨아이콘출력
                //WeatherResult.weater[0].icon
                var weathericonUrl =
                    '<img src= "http://openweathermap.org/img/wn/'
                    + result.weather[0].icon +
                    '.png" alt="' + result.weather[0].description + '"' +
                    'style="width: 100px; height: 100px"/>'

                $('.Icon').html(weathericonUrl);
            });
    </script>
</head>
<body>
<div class="weather">
    <div class="text">
        <h4>날씨</h4>
    </div>
    <div class="icon">
        <h3 class="Icon"></h3>
    </div>
    <div class="temp">
        <h5 class="text2">${address}</h5>
        <h3 class="Nowtemp">현재기온:</h3>
        <span class="Lowtemp" style="color: blue"></span> / <span class="Hightemp" style="color: red"></span>
    </div>
</div>
</body>
</html>
