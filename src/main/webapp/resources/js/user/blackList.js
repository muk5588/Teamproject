function allChecked(target){

        //전체 체크박스 버튼
        const checkbox = document.getElementById('allCheckBox');

        //전체 체크박스 버튼 체크 여부
        const is_checked = checkbox.checked;

        //전체 체크박스 제외한 모든 체크박스
        if(is_checked){
            //체크박스 전체 체크
            chkAllChecked()
        }

        else{
            //체크박스 전체 해제
            chkAllUnChecked()
        }
    }

    //자식 체크박스 클릭 이벤트
    function chkClicked(){

        //체크박스 전체개수
        const allCount = document.querySelectorAll(".chk").length;

        //체크된 체크박스 전체개수
        const query = 'input[name="chk"]:checked'
        const selectedElements = document.querySelectorAll(query)
        const selectedElementsCnt = selectedElements.length;

        //체크박스 전체개수와 체크된 체크박스 전체개수가 같으면 전체 체크박스 체크
        if(allCount == selectedElementsCnt){
             document.getElementById('allCheckBox').checked = true;
        }

        //같지않으면 전체 체크박스 해제
        else{
            document.getElementById('allCheckBox').checked = false;
        }
    }

    //체크박스 전체 체크
    function chkAllChecked(){
        document.querySelectorAll(".chk").forEach(function(v, i) {
            v.checked = true;
        });
    }

    //체크박스 전체 체크 해제
    function chkAllUnChecked(){
        document.querySelectorAll(".chk").forEach(function(v, i) {
            v.checked = false;
        });
    }
    
// 블랙 버튼 클릭 이벤트 처리
document.querySelector('.btn-danger').addEventListener('click', function() {
    var selectedUsers = document.querySelectorAll('input[name="userCheckbox"]:checked');
    var userid = [];
    selectedUsers.forEach(function(user) {
        userid.push(user.value);
    });
    // 서버로 선택된 유저의 아이디들을 전송
    userBlack(userid);
});


// 서버로 유저의 BLACK 상태를 업데이트하는 함수
function userBlack(userid) {
    // AJAX 요청을 사용하여 서버로 데이터 전송
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/user/userBlack', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // 업데이트 성공 시 적절한 처리
            console.log('User black status updated successfully');
        }
    };
    xhr.send(JSON.stringify(userid));
}


