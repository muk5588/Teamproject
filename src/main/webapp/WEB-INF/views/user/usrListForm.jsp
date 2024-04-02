<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" scope="application" value="${pageContext.request.contextPath}"/>
<c:set var="currDate" value="<%=java.util.Calendar.getInstance() %>"/>
<c:set var="UserCode" value="<%=common.CommonUtils.getUserId(request)%>"/>
<c:set var="AuthCode" value="<%=common.CommonUtils.getAuthCode(request)%>"/><%--수정예정--%>


<!-- 0217 designer -->
<div class="sub_content01">
	<div class="page_title">Users</div>
	<div class="search_div02">
		<div class="schFmWrap">
			<div class="row">
				<ul class="schFmCon">
					<li class="fxCon">
						<label class="schLab">Authority</label>
						<div>
							<select id="AUTHCODE_SEARCH" name="AUTHCODE_SEARCH" style="width:130px;">
								<option value="ALL">ALL</option>
								<c:forEach var="result" items="${ROLELIST}" varStatus="status">
									<option value='<c:out value="${result.ROLEID}"/>'><c:out value="${result.ROLEID}" /></option>
								</c:forEach>
							</select>
						</div>
					</li>
					<li class="fxCon">
						<label class="schLab">Classified</label>
						<div>
							<select id="SEARCH_TYPE_SELECT" name="SEARCH_TYPE_SELECT" style="width:100px; float:left; margin-right:5px">
						<option value="USERNAME">Korean Name</option> 
						<option value="ENGNAME">English Name</option>
						<option value="USERCODE">Id</option>

							</select>
							<input type="text" id="SEARCH_VALUE_INPUT" name="SEARCH_VALUE_INPUT" onkeydown="javascript:fn_SearchKeyDown();" class="input200"/>
							<select id="DIVCODE_SEARCH" name="DIVCODE_SEARCH" style="display: none; float:left" class="select200">
								<c:forEach var="result" items="${ROLELIST1}" varStatus="status">
									<option value='<c:out value="${result.ROLENAME}"/>'><c:out value="${result.ROLENAME}" /></option>
								</c:forEach>
							</select>
						</div>
					</li>
				</ul>
				<ul class="schFmCon rgt">
			        <li class="fxCon rgt">
						<button class="btn_type btn_02" onclick="fn_Search();return false;">Search</button>
						<button class="btn_type btn_03 WRT" onclick="fn_showJobDetail({});return false;">New</button>
						<button class="btn_type btn_01 WRT" onclick="fn_deleteJob();return false;">Delete</button>
						<button class="btn_type btn_01" onclick="fn_toggleFilter();return false;">Filter</button>
					</li>
				</ul>
			</div>
		</div>
	
		<div class="contents">
			<div id="panelGrid">
				<table id="grid_UserList" style="width: 100%;"></table>
			</div>
		</div>
		<div id="manualPop" name="manualPop" class="layerBRF SDW">
	<p class="tit">User registration</p>
			
			<input id="CMD_TYPE" name="CMD_TYPE" type="hidden"/>
			 	<fieldset>    
					<form id="updateForm" name="updateForm">
						<div>
							<ul>
								<li>
									<div>ID</div>
									<input class="popBtnIpt" type="text" id="USERCODE" name="USERCODE" maxlength="10" maxbyte="10" required userid fname="ID" class="w90">
									<input id="v_USERCODE" name="v_USERCODE" type="text" size="13" disabled="disabled"/>
									<button class="btn_type btn_02" id="duplCheck" onclick="fn_duplCheck($('#USERCODE').val());return false;">Duplicate check</button>
									<input id="DUPLICATION" name="DUPLICATION" type="hidden" value="N" size="13" />
									<input id="DUPLICATION_ID" name="DUPLICATION_ID" type="hidden"/>
								</li>
								<li>
									<div>EmployeeNo.</div>
									<input class="popBtnIpt" type="text" id="EMPLOYEENO" name="EMPLOYEENO" maxlength="10" maxbyte="10" required userid fname="EMPLOYEENO" class="w90">
									<input id="v_EMPLOYEENO" name="v_EMPLOYEENO" type="text" size="13" disabled="disabled"/>
									<button class="btn_type btn_02" id="duplCheckEMP" onclick="fn_EMPduplCheck($('#EMPLOYEENO').val());return false;">Duplicate check</button>
									<input id="DUPLICATIONEMP" name="DUPLICATIONEMP" type="hidden" value="N" size="13" />
									<input id="DUPLICATION_EMP" name="DUPLICATION_EMP" type="hidden" />
								</li>
								<li>
							<div>User Division</div>
									<input type="text" id="DIVCODE" name="DIVCODE">
								</li>
								<li>
									<div>Authority</div>
									<select id="AUTHCODE" name="AUTHCODE"  maxlength="20" onchange="insertDivcode();">
										<c:forEach var="result" items="${ROLELIST}" varStatus="status">
											<option value='<c:out value="${result.ROLEID}"/>'><c:out value="${result.ROLENAME}" /></option>
										</c:forEach>
									</select>
									<input id="USERPSWD" name="USERPSWD" type="hidden" value="" size="13" />
									<input id="PSWDDATE" name="PSWDDATE" type="hidden" size="13" />
								</li>
								<li>
									<div>WORK Y/N<span class="asterisk">*</span></div>
									<div class="height20">
										<input class="input_exception" type="radio" name="WORKYN" value="Y" id="workYnY">
										<label class="label_text" for="workYnY">Y</label>
										<input class="input_exception" type="radio" name="WORKYN" value="N" id="workYnN">
										<label class="label_text" for="workYnN">N</label>
									</div>									
								</li>
								<li>
									<div>Approval Y/N<span class="asterisk">*</span></div>
									<div class="height20">
										<input class="input_exception" type="radio" name="STTYN" value="Y" id="sttYnY">
										<label class="label_text" for="sttYnY">Y</label>
										<input class="input_exception" type="radio" name="STTYN" value="N" id="sttYnN">
										<label class="label_text" for="sttYnN">N</label>
									</div>									
								</li>
						<li><div>Korean Name</div><input type="text" id="USERNAME" name="USERNAME" required  maxlength="16" maxbyte="50" fname="Korean Name" class="w130"></li>
						<li><div>Tel.</div><input id="HPNO" name="HPNO" type="text" maxlength="20" size="20" /></li>
						<li><div>E-Mail</div><input id="EMAIL" name="EMAIL" type="text" maxlength="100" size="25" /></li>
						<li><div>English Name</div><input id="ENGNAME" name="ENGNAME" type="text" maxlength="100" size="25" /></li>
						<li><div>Position</div><input id="POSNAME" name="POSNAME" type="text" maxlength="16" size="25" /></li>
						<li><div>Department</div><input id="TEAMNAME" name="TEAMNAME" type="text" maxlength="16" size="25" /></li>
							</ul>
						</div>
					</form>
		  		</fieldset> 
			
			<div class="btn_div">
				<button id = "pwResetBtn" class="btn_type btn_01" onclick="fn_passwordReset();return false;">Password Reset</button>
				<button class="btn_type btn_03" id="btnSave" >Save</button>
				<button class="btn_type btn_02 cbtn" id="btnClose" >Cancel</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" charset="utf-8">

function fn_SearchKeyDown(){
	if(event.keyCode==13){
		fn_Search();
	}
	
}

function fn_Search() {
var username;

}

$(document).ready(function () 
{
	$("#btnSave").click(function( event ) { 
		event.preventDefault();
	
		var formUtil = new FormUtil(document.updateForm);
		//if(formUtil.success())		fn_saveJob();		
		fn_saveJob();

	});
	
	initGrid();
	
	$( "#SEARCH_TYPE_SELECT" ).change(function() {
		var SEARCH_TYPE = $( "#SEARCH_TYPE_SELECT" ).val();
		if(SEARCH_TYPE != 'DIVCODE'){
			$("#SEARCH_VALUE_INPUT").css("display","block"); 	
			$("#DIVCODE_SEARCH").css("display","none"); 	
		}else{
			$("#SEARCH_VALUE_INPUT").css("display","none"); 	
			$("#DIVCODE_SEARCH").css("display","block"); 					
		}			
	});
	
	fn_GridResize();
});

$(window).resize(function(){
	fn_GridResize();
}).resize();


var grid_filter = false;
function fn_toggleFilter() {
	if (grid_filter) {
		$(".ui-search-toolbar").hide();
	} else {
		$(".ui-search-toolbar").show();
	}
	grid_filter = !grid_filter;
}
function fn_saveExcel() {
	var SEARCH_VALUE = '';
	var arrayList = "";
	 
	if($("#SEARCH_TYPE_SELECT").val() == "DIVCODE"){
		SEARCH_VALUE = $("#DIVCODE_SEARCH").val();
	}else{
		SEARCH_VALUE = $("#SEARCH_VALUE_INPUT").val();
	}
	
	var idArry = $("#" + grid_id).jqGrid('getDataIDs');
	
	var selRowIds = $("#" + grid_id).jqGrid('getGridParam','selarrrow');
	
	for (var i = 0; i < idArry.length; i++) {
		if($("input:checkbox[id='jqg_"+grid_id+"_"+idArry[i]+"']:checked").length>0){
		var rowdata = $("#" + grid_id).getRowData(idArry[i]); // 해당 id의 row 데이터를 가져옴
			
			if(arrayList == ""){
				arrayList = rowdata.USERCODE;
			} else {
				arrayList = arrayList+","+rowdata.USERCODE;
			}
		}
	}
	$("#selectArray1").val(arrayList);

	$("#AUTHCODE").val($("#AUTHCODE_SEARCH").val());
	$("#SEARCH_TYPE").val($("#SEARCH_TYPE_SELECT").val());
	$("#SEARCH_VALUE").val(SEARCH_VALUE);
	$("#WORK_EXCEL").val($(':radio[name="WORKYN_SR"]:checked').val());
	
}

function fn_showJobDetail(rowdata) {
 
	if(rowdata.USERCODE == null) {
		
		$("#USERCODE").show();
		$("#EMPLOYEENO").show();
		$("#v_USERCODE").hide();
		$("#v_EMPLOYEENO").hide();
		
		$('#CMD_TYPE').val('INSERT');
	    $("#USERCODE").val('');
	    $("#v_USERCODE").val('');
	    $("#v_EMPLOYEENO").val('');

	    $("#EMPLOYEENO").val('');
		$("#DIVCODE").val('');
		$("#AUTHCODE").val('');
		$("#WORKYN").val('');
		$("#USERNAME").val('');
		$("#PSWDDATE").val('');
		$("#HPNO").val('');
		$("#EMAIL").val('');
		$("#ENGNAME").val('');
		$("#POSNAME").val('');
		$("#TEAMNAME").val('');
		
		$("#duplCheck").show();
		$("#duplCheckEMP").show();
		$("#pwResetBtn").hide();

		defalutRadioCheck();
		
	} else { 
		$('#CMD_TYPE').val('UPDATE');
		$("#USERCODE").hide();
		$("#EMPLOYEENO").hide();
		$("#duplCheck").hide();
		$("#v_USERCODE").show();
		$("#v_EMPLOYEENO").show();
		
	    $("#USERCODE").val(rowdata.USERCODE);
		$("#v_USERCODE").val(rowdata.USERCODE);

		$("#v_EMPLOYEENO").val(rowdata.EMPLOYEENO);
		$("#EMPLOYEENO").val(rowdata.EMPLOYEENO);
		$("#DIVCODE").val(rowdata.DIVCODE);
		$("#AUTHCODE").val(rowdata.AUTHCODE);
		$("#USERNAME").val(rowdata.USERNAME);
		//$("#USERPSWD").val(rowdata.USERPSWD);
		$("#PSWDDATE").val(rowdata.PSWDDATE);
		$("#HPNO").val(rowdata.HPNO);
		$("#EMAIL").val(rowdata.EMAIL);
		$("#ENGNAME").val(rowdata.ENGNAME);
		$("#POSNAME").val(rowdata.POSNAME);
		$("#TEAMNAME").val(rowdata.TEAMNAME);
		
		if(rowdata.WORKYN == "Y")	
			$("#workYnY").click();
		else
			$("#workYnN").click();
		
		if(rowdata.STT == "Y")	{
			$("#sttYnY").click();
		} else{
			$("#sttYnN").click();
		}
		
		$("#duplCheck").hide();
		$("#duplCheckEMP").hide();
		$("#pwResetBtn").show();

	}
	
	
	layer_open('manualPop');
}

function fn_saveJob() {

	if($('#CMD_TYPE').val() == 'INSERT' && $('#DUPLICATION').val() == 'N'){
		alert("Please check for the availability of the ID");
		$("#USERCODE").focus();
		return false;
	}else if($('#CMD_TYPE').val() == 'INSERT' && $('#DUPLICATION_ID').val() != $('#USERCODE').val()){
		alert("Please check for the availability of the ID");
		$("#USERCODE").focus();
		return false;
	}
	if($('#CMD_TYPE').val() == 'INSERT' && $('#DUPLICATIONEMP').val() == 'N' ){
		alert("Please check for the availability of the Employee Number");
		$("#EMPLOYEENO").focus();
		return false;
	}else if($('#CMD_TYPE').val() == 'INSERT' && $('#DUPLICATION_EMP').val() != $('#EMPLOYEENO').val()){
		alert("Please check for the availability of the Employee Number");
		$("#EMPLOYEENO").focus();
		return false;
	}
	
	var data = {'CMD_TYPE': $('#CMD_TYPE').val()
			,'USERCODE':$("#USERCODE").val()
			,'v_USERCODE':$("#v_USERCODE").val()
			,'EMPLOYEENO':$("#EMPLOYEENO").val()
			,'v_EMPLOYEENO':$("#v_EMPLOYEENO").val()
			,'USERPSWD':$("#USERPSWD").val()
			,'DIVCODE':$("#DIVCODE").val()
			,'AUTHCODE':$("#AUTHCODE").val()
			,'WORKYN':$(':radio[name="WORKYN"]:checked').val()
			,'USERNAME':$("#USERNAME").val()
			,'PSWDDATE':$("#PSWDDATE").val()
			,'HPNO':$("#HPNO").val()
			,'EMAIL' :$("#EMAIL").val()
			,'ENGNAME':$("#ENGNAME").val()
			,'POSNAME':$("#POSNAME").val()
			,'TEAMNAME':$("#TEAMNAME").val()
			,'STT':$(':radio[name="STTYN"]:checked').val()
			
	};
	var url = '<c:url value="/user/saveUser"/>';

	$.ajax({
	    type: "POST",
        url: url,
        dataType : 'json',
        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
      	type: "POST",
      	//data : $("#editData").serialize(),
      	data : data,
      	success: function(data) {
      		fn_alert("INFO", "Was added.");
      		fn_Cancel('manualPop');
			fn_Search();
    	},
		error:function(e){  
      		fn_alert("ERROR", "An error has occurred.", e.responseText);  
  		}
	});
}

function defalutRadioCheck(){
	$("#workYnN").click();
};


function fn_deleteJob() {
	
	if(!confirm("Are you sure you want to delete the user?")){
		return;
	}
	
	var deletedCount = 0;
	var idArry = $("#" + grid_id).jqGrid('getDataIDs'); 
	for (var i = 0; i < idArry.length; i++) { 
    	if($("input:checkbox[id='jqg_"+grid_id+"_"+idArry[i]+"']").is(":checked")){
    		var rowdata = $("#" + grid_id).getRowData(idArry[i]); // 해당 id의 row 데이터를 가져옴
    		$.ajax({
    		    type: "POST",
    	        url: '<c:url value="/user/deleteUser"/>',
    	        dataType : 'json',
    	        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
    	      	data : {USERCODE: rowdata.USERCODE},
    	      	success: function(data) {
    	      		//deletedCount += 1;
    	    	},
    			error:function(e){  
    				fn_alert("ERROR", "An error has occurred.", e.responseText);   
    	  		}
    		});
    		deletedCount += 1;
    	}
	}
	
	console.log(deletedCount);
	
	if (deletedCount > 0) {
		//alert("선택된 [" + deletedCount + "]건이 삭제되었습니다. ");
		fn_alert("INFO", "" + deletedCount + "selected items have been deleted.");  
		fn_Search();
	} else {
		fn_alert("WARNING", "Please select a row to delete.");
	}
}

function fn_duplCheck(DUPLCHECK){
	
	if(DUPLCHECK == "" || DUPLCHECK == undefined ){
		alert("Enter your ID.");
		return false;
	} else {
		$.ajax({
		    type: "POST",
		    url:'<c:url value="/user/getUserListCount" />',
	        dataType : 'json',
	        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
	      	data : {DUPLCHECK: DUPLCHECK},
	      	success: function(data) {
	      		var count = data.code;
	      		if(count == 0){
	      			alert("This ID is available.");
	      			$("#DUPLICATION").val("Y");
	      			$("#DUPLICATION_ID").val(DUPLCHECK);
	      		} else {
	      			alert("This ID is already in use.");
	      			$("#USERCODE").focus();
	      			$("#DUPLICATION").val("N");	
	      		}
	    	},
			error:function(e){  
				fn_alert("ERROR", "An error has occurred.", e.responseText);   
	  		}
		});
	}
}


/* function insertDivcode(){
	$("#DIVCODE").val($("#AUTHCODE option:selected").text());
} */


</script>