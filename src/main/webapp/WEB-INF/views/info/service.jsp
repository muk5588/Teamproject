<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 운영정책</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/img/20191208094528217881320965.png">
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<link href="/resources/css/info/service.css" rel="stylesheet" type="text/css">

<script>
$(function(){
	  $('#back-to-top').on('click',function(e){
	      e.preventDefault();
	      $('html,body').animate({scrollTop:0},600);
	  });
	  
	  $(window).scroll(function() {
	    if ($(document).scrollTop() > 100) {
	      $('#back-to-top').addClass('show');
	    } else {
	      $('#back-to-top').removeClass('show');
	    }
	  });
	});
</script>


</head>
<body>
<h1>Travel Square 게시물 운영정책</h1>
<hr>

<ul class="rules">
    <li>
      <a href="/info/service" id="service">게시물 운영정책</a>
    </li>
    <li>
      <a href="/info/service2" id="service2">계정 운영정책</a>
    </li>
</ul>

<div class="serviceSection">
	<a id="back-to-top"></a>
<p>Travel Square는 다양한 정보와 의견이 담긴 여러분의 게시물이 다른 분들에게 전달되어 우리 모두의 삶을 더욱 풍요롭게 해 줄 것을 기대합니다. Travel Square는 여러분이 자유롭게 작성한 게시물을 소중히 다룰 것입니다.</p>
<p>게시물이란 이용자가 타인 또는 자신이 보게 할 목적으로 Travel Square 서비스 상에 게재한 부호, 문자, 음성, 음향, 그림, 사진, 동영상, 링크 등으로 구성된 각종 콘텐츠 자체 또는 파일을 말합니다.</p>
<p>본 게시물 운영정책을 통해 회원 가입 이후 Travel Square 서비스 이용 과정에서 자신의 게시물을 어떻게 관리해야 하는지 확인해 주세요.</p>

<p class="bold-text">부득이 일정한 내용의 게시물은 게재가 제한될 수 있습니다.</p>
<p>여러분의 다양한 생각이나 경험 또는 의견이 담긴 게시물은 최대한 게재가 유지되어 다른 이용자들에게 전파될 것입니다. 다만 여러분이 Travel Square 서비스를 보다 안전하게 이용할 수 있도록, Travel Square 서비스에서 여러분과 타인의 권리가 서로 존중되고 보호받을 수 있도록, 그리고 Travel Square 서비스가 신뢰 있는 서비스로서 안정적으로 제공될 수 있도록 부득이 아래와 같은 경우 여러분의 게시물 게재가 제한될 수 있으므로, 이에 대한 확인 및 준수를 요청 드립니다.</p>
<p>관련 법령을 위반하거나 타인의 권리를 침해하는 내용의 게시물은 명백한 법령 위반 또는 권리 침해의 내용이 아닌 한, 원칙적으로 법원의 판결, 결정 등 또는 법률에 따라 관련 권한을 보유한 행정기관의 행정처분, 명령 등에 의해 법령 위반 또는 권리 침해가 확인된 경우 게재가 제한됨을 알려 드립니다.</p>

<p class="bold-text">다른 이용자의 안전 확보</p>
<p>다른 이용자 또는 공공의 안전에 직접적인 위험을 일으키고 있다고 의심되는 다음과 같은 내용의 게시물은 게재가 제한됩니다.</p>
<p>타인에 대한 신체적 위협을 사실적이고 구체적으로 표현하여 그의 생명 또는 신체의 안전에 직접적이고 심각한 위험을 일으키는 내용의 게시물</p>
<p>타인의 자해 행위 또는 자살을 적극적이고 구체적으로 부추기거나 권장하여 그의 생명 또는 신체의 안전에 직접적이고 심각한 위험을 일으키는 내용의 게시물</p>
<p>타인의 신상정보, 사생활 등 비공개 개인정보를 드러내거나 거래하여 그의 사생활의 비밀과 안전에 직접적이고 명백한 위험을 일으키는 내용의 게시물</p>
<p>타인에게 공포심, 불안감 또는 불쾌감을 일으키는 내용의 게시물로서 지속하여 반복적으로 게재함으로써 그의 사생활의 안전에 직접적이고 명백한 위험을 일으키는 게시물</p>
<p>타인에게 금품 등을 대가로 성매매를 제안, 알선, 유인 또는 강요하여 그 또는 제3자의 신체의 안전에 직접적이고 심각한 위험을 일으키는 내용의 게시물</p>
<p>범죄, 범죄인 또는 범죄단체 등을 미화하거나 지지하여 범죄를 용인하거나 조장할 우려가 있어 공공의 안전에 직접적이고 심각한 위험을 일으키는 내용의 게시물</p>
<p>코로나19의 존재, 치료, 예방 및 진단, 감염, 사회적 거리두기 및 자가격리와 관련한 내용으로서, 세계보건기구(WHO) 또는 질병관리청에 의해 허위조작정보임이 명백히 확인된 게시물의 경우(이와 관련해서는 한국인터넷자율정책기구(KISO)의 정책결정을 참고하시기 바랍니다.)</p>
<p>도박 등 관련 법령상 금지되거나 형사처벌의 대상이 되는 행위를 직접 수행하거나, 혹은 이를 수행하도록 타인을 부추기거나(교사) 이를 수행하는 타인을 돕는(방조) 등 범죄 관련 직접적인 위험이 확인된 게시물은 게재가 제한됩니다.</p>

<p class="bold-text">다른 이용자의 권리 보호</p>
<p>자신의 권리가 소중한 만큼 타인의 명예, 사생활, 저작권 등의 권리도 존중되어야 하며, 이를 보호하기 위한 관련 법령은 준수되어야 합니다. 자신의 경험, 의견 등을 자유로이 표현한 게시물이라도 법원의 판결, 결정 등이나 권한이 있는 행정기관의 처분, 명령 등을 통해 타인의 명예, 사생활, 저작권 등의 권리를 침해하였음이 확인된 게시물은 게재가 제한됩니다.</p>
<p>저작권자가 자신의 권리를 보호하기 위해 마련한 기술적 보호조치를 무력화시키는 내용이 포함되어 있거나, 그 내용상 저작권자의 의사에 반하고 ‘저작권법’ 상의 예외적 이용 허용 범위에 해당하지 않음이 명백히 추정되는 다음과 같은 게시물은 게재가 제한됩니다.</p>
<p>자사 프로그램에 대한 공유를 허용하지 않은 방송사의 로고가 포함되어 있는 동영상 등의 게시물</p>
<p>유료로 판매되는 이미지, 방송물, 영상, 음원, 만화(웹툰 포함), 소설(웹소설 포함), 게임, 소프트웨어 등에 대한 무료 다운로드 등 불법 복제를 권유하거나 불법 다운로드 링크 경로를 제공하는 게시물</p>
<p>정품 소프트웨어 CD의 시리얼 번호를 공유하거나 CD키 자동생성 프로그램을 제공하는 게시물</p>
<p>게임 프리서버를 홍보하거나 또는 그 사이트 주소를 공유하는 게시물</p>
<p>권리자의 기술적 보호조치를 무력화시키는 버그나 핵 프로그램 등을 공유하는 게시물</p>
<p>한편, 여러분이 자유롭게 작성하여 게재한 게시물에 대해 상대방은 그 내용이 자신의 명예, 사생활, 저작권 등의 등 권리를 침해하고 있다고 생각할 수 있습니다. Travel Square는 이에 대한 문제 해결을 위해, ‘정보통신망 이용촉진 및 정보보호 등에 관한 법률’ 및 ‘저작권법’에 따른 권리보호센터를 운영하고 있습니다.</p>
<p>가령 자신의 명예, 초상권, 사생활 등의 인격적 권리나 저작권 침해를 주장하며 그 정보의 삭제 등 또는 복제·전송의 중단 등을 요청해 올 경우, Travel Square는 그 요청에 따라 해당 게시물의 게시를 중단합니다. 그리고 만약 게시중단 조치에 대해 원 게시물 게시자가 자신의 게시물 게재가 명예 등 권리 침해행위에 해당하지 않음을 소명하거나 자신의 복제·전송이 정당한 권리에 의한 것임을 소명하며 이의 신청을 해올 경우엔 소정의 기간 경과 후 해당 게시물의 게시 중단을 해제하거나 그 복제·전송을 재개합니다.</p>
<p>다만, 정무직 공무원 등 공인이 자신의 명예훼손 등 권리침해를 주장하며 게시중단을 요청해 온 경우 (사)한국인터넷자율정책기구(KISO)의 정책규정에 따라 처리가 제한될 수 있음을 알려 드립니다.</p>

<p class="bold-text">다른 이용자의 존중</p>
<p>문학적 또는 예술적 표현의 일환으로, 의학 또는 의료 정보 전달의 필요에서 혹은 교육, 풍자 등의 목적으로 나체 이미지나 기타 성적 표현이 포함된 게시물을 게재할 수 있습니다. 다만, 이러한 내용의 게시물은 상대방의 문화적 배경, 가치관 또는 그 연령에 따라서는 오히려 심한 불쾌감, 성적 수치심, 왜곡된 성 의식 등을 일으킬 수도 있습니다. Travel Square 서비스는 청소년이 자유롭게 접근할 수 있는 공간인 만큼 아래와 같이 성기 또는 성적 행위에 관한 노골적인 묘사, 비정상적인 성적 행위에 대한 적나라한 표현 등이 포함된 게시물은 게재가 제한됩니다.</p>
<p>남녀의 성기, 음모, 항문, 유두가 포함된 여성의 유방 등 특정 성적 부위 또는 성교 등의 성적 행위를 노골적으로 표현 또는 묘사하는 내용의 게시물</p>
<p>강간(强姦), 윤간(輪姦), 성추행 등의 성폭력 행위 또는 수간(獸姦), 시간(屍姦), 혼음(混淫), 근친상간(近親相姦), 가학/피학성 음란증, 관음증(觀淫症) 등의 비정상적인 성적 행위를 미화하거나 구체적으로 표현 또는 묘사하는 내용의 게시물</p>
<p>구강, 항문 등 신체 일부 또는 도구를 이용한 유사성교, 성기 애무 또는 자위행위를 노골적으로 표현 또는 묘사하는 내용의 게시물</p>
<p>아동 또는 청소년을 성적 유희의 대상으로 직접적이고 구체적으로 묘사한 내용의 게시물</p>
<p>그 밖에 남녀의 성기 또는 성교 등 성적 행위에 대한 자극적이고 혐오스러운 성적 표현을 통해 일반인의 성적 수치심을 현저히 해할 우려가 있는 내용의 게시물</p>
<p>사람에 대한 침해행위, 사람의 배설물, 동물에 대한 학대 등을 구체적으로 묘사한 아래와 같은 반인륜적 또는 반인격적인 내용의 게시물은 상대방에게 인격적 불쾌감, 잔혹감 또는 혐오감을 일으킬 수 있으므로 그 게재가 제한될 수 있습니다.</p>
<p>장애인, 노인, 임산부, 아동 등 사회적인 약자 또는 부모, 스승 등에 대한 살상, 폭행, 협박, 학대행위 등을 구체적으로 묘사하는 내용의 게시물</p>
<p>출산(분만), 낙태, 사람에 대한 수술 등의 의료행위를 지나치게 상세히 묘사하여 혐오감을 일으키는 내용의 게시물</p>
<p>흉기 그 밖의 위험한 물건 등을 사용하여 신체 또는 시체를 손상하는 행위를 구체적이고 사실적으로 묘사하여 잔혹감 또는 혐오감을 일으키는 내용의 게시물</p>
<p>구토, 방뇨 또는 배설한 오물, 정액, 여성생리분비물 등을 구체적이고 사실적으로 묘사하여 혐오감을 일으키는 내용의 게시물</p>
<p>동물에 대한 학대 또는 살상, 동물의 사체 등을 구체적이고 사실적으로 묘사하여 잔혹감 또는 혐오감을 일으키는 내용의 게시물</p>
<p>인터넷 공간이 다양한 의견이 존중되는 소통의 장이 되려면 개인이나 집단 사이의 비판적 표현은 폭넓게 허용되어야 하고, 그 표현이 서로 간에 갈등을 야기할 수 있더라도 무조건 억제되고 제한되어서는 안 됩니다. 다만 인터넷 공간의 신뢰성을 심각하게 위협하고, 사회적 의사소통의 합리성을 저해할 뿐만 아니라 다양하고 자유로운 의견의 소통을 오히려 어렵게 할 우려가 있는 게시물은 게재가 제한될 수 있습니다.</p>
<p>다른 이용자를 기만할 목적으로 타인을 사칭하거나 허위사실 을 주장하는 내용의 게시물</p>
<p>언론사의 명의나 언론사의 직책 등을 사칭 또는 도용하여 기사 형태를 갖춘 허위 게시물</p>
<p>과도한 욕설,비속어 등을 계속하여 반복적으로 사용하여 상대방에게 심한 혐오감 또는 불쾌감을 일으키는 내용의 게시물</p>
<p>인종·국가·민족·지역·나이·장애·성별·성적지향이나 종교·직업·질병 등(이하 ‘특정 속성’이라 한다)을 이유로, 특정 집단이나 그 구성원에 대하여 차별을 정당화·조장·강화하거나 폭력을 선전·선동하는 혐오표현을 포함한 게시물</p>
<p>이와 관련하여 Travel Square는(사)한국인터넷자율정책기구(KISO)의 <정책규정>을 준수하고 있습니다. 이른바 '가짜뉴스', '혐오표현'관련한 제한에 대해서는 KISO <정책규정>의 <언론보도 형식의 허위 게시물 관련 정책>, KISO <혐오표현 자율정책 가이드라인> 을 참고하실 수 있습니다.</혐오표현>

<p class="bold-text">아동과 청소년의 특별한 보호</p>
<p>Travel Square는 인터넷 공간에서 아동과 청소년의 보호를 최우선으로 합니다. Travel Square는 아동과 청소년의 신체와 성을 대상화하는 행위를 금지하고, 아동과 청소년을 대상으로 비인격화하는 그 어떤 행위도 허용하지 않습니다. 아동과 청소년이 안전하게 누릴 수 있는 인터넷 환경 조성을 위해서 앞장서는 Travel Square의 노력에 동참해 주세요. Travel Square는 단 한번의 위반 행위에도 단호하게 대처할 것입니다. 위반 게시물을 발견한 경우 누구나 신고센터에 제보할 수 있습니다. 특히 아래에 해당하는 게시물은 확인되는 대로 즉시 삭제되며, 게시자의 계정은 글쓰기 및 로그인 제한, 서비스 이용이 해지될 수 있습니다.</p> 
<p>아동과 청소년의 신체를 촬영하거나 성적 대상화하는 내용의 게시물</p>
<p>아동과 청소년의 일상적 사진을 성적인 사진과 합성한 게시물</p>
<p>아동과 청소년을 대상으로 한 성적 괴롭힘으로 보이는 내용의 게시물</p>
<p>아동과 청소년의 성착취물을 제작하거나 제공, 광고, 소개 등에 이용하는 내용의 게시물</p>
<p>아동과 청소년이 성착취물의 제작에 이용되도록 돕는 내용의 게시물</p>
<p>아동과 청소년에게 음란물 또는 성착취물을 제공하는 내용의 게시물</p>
<p>아동과 청소년의 성을 매매하는 행위 또는 그와 유사해 보이는 내용의 게시물</p>
<p>현행 성폭력처벌법과 정보통신망법 등 관련 법률에서 금지하는 내용의 게시물</p>
<p>아동과 청소년을 과도하게 비인격화하는 내용의 게시물</p>
<p>Travel Square 서비스에서 제공한 기술적 도구와 인터넷 환경을 매개로 아동 또는 청소년을 등장시켜 신체와 성을 대상화하는 내용의 게시물</p>

<p class="bold-text">서비스의 신뢰성 및 안전성 확보</p>
<p>자신만의 특별한 이익을 추구하고자 Travel Square 서비스를 이용하는 행위는 물론 허용되지만, Travel Square 서비스의 기능을 비정상적으로 이용하여 게재했거나 Travel Square 각 개별 서비스의 제공 취지와 부합하지 않는 내용을 가진 아래와 같은 게시물은 다른 이용자들의 정상적인 Travel Square 서비스 이용에 불편을 초래하고 더 나아가 Travel Square의 원활한 서비스 제공을 방해하므로 게재가 제한됩니다.</p>
<p>Travel Square의 사전 허락 없이 자동화된 수단(예: 매크로 프로그램, 로봇(봇), 스파이더, 스크래퍼 등)을 이용하여 게재한 게시물</p>
<p>Travel Square 각 개별 서비스의 제공 취지와 부합하지 않거나 그 제공되는 서비스와 전혀 관련이 없는 내용의 게시물</p>
<p>물건 또는 서비스를 홍보, 광고 또는 판매할 목적으로 동일 또는 유사한 내용의 게시물로서 지속적으로 반복하여 게재함으로써 다른 이용자에게 심각한 불편을 초래하는 게시물</p>

<p class="bold-text">여러분의 신고를 기다립니다.</p>
<p>Travel Square는 서비스 내에서 모두가 안전하고 즐겁게 함께 할 수 있는 환경을 만들기 위해 노력하고 있으며,이를 위해 이용자 여러분의 신고를 받고 있습니다. 관련 법령, Travel Square 서비스 이용약관, 본 게시물 운영정책 등에 위배하는 것으로 보이는 게시물을 발견할 경우 신고해주세요. 담당 부서를 통해 접수되는 신고 내용을 신중히 검토하여 처리하도록 하겠습니다.</p>
<p>신고 시 다음 내용을 참고하세요.</p>
<p>Travel Square는 관련 법령, Travel Square 서비스 이용약관, 본 게시물 운영정책 등에 위배되는 게시물이 확인된 경우 언제라도 조치를 취할 수 있습니다.</p>
<p>신고할 때에는 신고하려는 게시물의 URL과 해당 게시물을 제한해야 하는 사유를 알려주세요. 제출된 게시물 URL과 신고 사유는 담당자에게 전달되고, 담당자는 이를 바탕으로 게시물 제한 여부를 신중히 검토합니다.</p>
<p>신고자의 생각이나 판단과 달리 해당 게시물이 명백히 관련 법령, Travel Square 서비스 이용약관, 본 게시물 운영정책 등을 위반한다고 판단되지 않을 경우엔 제한되지 않을 수 있습니다. 만약 해당 게시물이 관련 법령에 위배되는 불법정보 또는 청소년유해정보에 해당한다고 판단한다면 방송통신심의위원회의 '불법 유해정보 신고' 창구를 통해 신고하는 방법도 있습니다.</p>
<p>이미 검토된 게시물이라도 나중에 숨은 맥락이 발견되거나 법원의 판결 등이나 권한 있는 행정기관의 처분 등이 전달되어 관련 법령, Travel Square 서비스 이용약관, 본 게시물 운영정책 등 위배가 확인될 경우 이전 결정이 번복되어 제한될 수 있습니다.</p>
<p>신고 내용을 검토하는 과정에서 해당 게시물의 제한 여부뿐만 아니라 추가로 해당 회원 계정의 이용제한도 고려되는 경우가 있습니다. 이 경우 해당 회원의 서비스 이용 이력, 관련 법령, Travel Square 서비스 이용약관, 본 게시물 운영정책 등 위반 이력 및 그 경중 등을 고려합니다. 회원 계정의 이용제한에 관해서는 다음의 이용제한 절차 항목을 참고해 주십시오.</p>

<p class="bold-text">합리적 절차에 따라 이용을 제한합니다.</p>
<p>해당 게시물이 관련 법령에 명백히 위배되거나 다른 이용자 또는 권리자 보호를 위해 Travel Square 서비스 이용약관, 본 게시물 운영정책 등에서 금지하는 내용에 명확하게 해당할 경우 Travel Square는 이를 비공개 또는 삭제 처리하거나 게재를 거부할 수 있습니다.</p>
<p>여러분이 관련 법령, Travel Square 서비스 이용약관, 본 게시물 운영정책 등을 준수하지 않을 경우, Travel Square는 여러분의 관련 행위 내용을 확인할 수 있으며, 그 확인 결과에 따라 Travel Square 서비스 이용에 대한 주의를 당부하거나, Travel Square 서비스의 이용을 일부 또는 전부, 잠시 또는 계속 정지시키는 등 그 이용을 제한할 수 있습니다. 한편, 이러한 이용 제한에도 불구하고 더 이상 Travel Square 서비스 이용계약의 온전한 유지를 기대하기 어려운 경우엔 부득이 여러분과의 이용계약을 해지할 수 있습니다.</p>







<hr>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>
</body>
</html>