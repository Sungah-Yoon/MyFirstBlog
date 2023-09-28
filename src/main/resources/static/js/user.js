let index = {
    init: function () {
        $("#btn-save").on("click", ()=>{  // function(){} 대신, ()=>{}를 사용한 이유는 this를 바인딩하기 위함
            this.save();
        });   // 첫번째 param(click) -> event, 두번째 param -> action
        $("#btn-login").on("click", ()=>{
            this.login();
        });
    },

    save: function (){
        //alert('user의 save 함수 호출됨');

        // 1. 사용자 정보 요청
        let data ={
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        //console.log(data);

        // 2. ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
        // ajax 호출 시, default는 비동기 호출
        // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌 -> dataType 생략 가능!
        $.ajax({
            // 회원가입 수행 요청
            type:"POST",
            url:"/blog/api/user",
            data: JSON.stringify(data),    // http body 데이터
            contentType: "application/json; charset=utf-8",  // body 데이터가 어떤 타입인지(MIME)
            dataType: "json",    // 요청을 서버로 해서 응답이 왔을 때, 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript 오브젝트로 변경해줌
        }).done(function (resp){
            alert("회원가입이 완료되었습니다.");
            //console.log(resp);
            location.href = "/blog";  // 응답 제대로 받으면, 메인 화면으로 연결
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    login: function (){

        // 1. 사용자 정보 요청
        let data ={
            username: $("#username").val(),
            password: $("#password").val(),
        };

        $.ajax({
            // 로그인 수행 요청
            type:"POST",
            url:"/blog/api/user/login",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
        }).done(function (resp){
            alert("로그인이 완료되었습니다.");
            location.href = "/blog";       // 응답 제대로 받으면, 메인 화면으로 연결
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();