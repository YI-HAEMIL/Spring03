/**
** 이클립스 자바스크립트 파일 내용이 흑백으로 나올때... 컬러로 고치기;;
=> https://creampuffy.tistory.com/66

윈도우 - 프레퍼런스 - 제네럴 - 에디터스 - 파일 어소시에이션 - 
add - *.js - 밑에 제네릭 텍스트 에디터 디폴트

********************************************
**** AjaxTest01
=> MousePounter, axLogin, jsLogin 
=> axJoin , formData 처리
 */
//$(document).ready(function(){
$(function(){	
	
// ** MousePounter
// => $('.textLink').hover(function(){...},function(){...})
	$('.textLink').hover(function(){
		$(this).css({
			fontWeight: "bold",
			color: "DeepSkyBlue",
			cursor: "pointer"}) // css
	}, function(){
		$(this).css({
			fontWeight: "",
			color: "gray",
			cursor: "default"}) // css
	}); //hover
	
// ** Ajax BoardList
	$('#ablist').click(function(){
		$.ajax({
			type:'Get',
			url:'ablist',
			success:function(resultPage){
				$('#resultArea1').html(resultPage);
				},
			error:function(){
				$('#resultArea1').html("~~ 오류발생 ~~");
			}	
		}); //ajax
	}); //ablist_click 
	
// ** Ajax Login Test
	$('#aloginf').click(function(){
		$.ajax({
			type:'Get',
			url:'mloginf',
			success:function(resultPage){
				$('#resultArea').html(resultPage);
				},
			error:function(){
				$('#resultArea').html("~~ 오류발생 ~~");
			}	
		}); //ajax
	}); //click
	
	$('#alogin').click(function(){
		$.ajax({
			type:'Get',
			url:'mlogin',
			data:{
				id:$('#id').val(),
				password:$('#password').val()
			},
			success:function(){
				//console.log('test r=>'+r);
				$('#resultArea').html('');
				location.reload(); // home 새로고침
				},
			error:function(){
				$('#resultArea').html("~~ AxLogin 오류발생 ~~");
			}	
		}); //ajax
	}); //click
	
// ** Json Login Test _ jsonView
	$('#jlogin').click(function(){
		$.ajax({
			type:'post',
			url:'jlogin',
			data : {
				id:$('#id').val(),
				password:$('#password').val()
			},
			success : function(resultData) {
				// loginSuccess == 'T':성공 / 'F':실패
				// 성공 => alert "성공" , resultArea Clear or 새로고침
				// 실패 => 재로그인 할 수 있도록 alert message 출력, id 에 focus 
				if (resultData.loginSuccess=='T') {
					location.reload();
					alert("~~ 로그인 성공 ~~");
				}else {
					alert(resultData.message);
					$('#id').focus();
				} 
			},
			error: function() {
				$('#resultArea').html("~~ JsonLogin 오류발생 ~~");
			}
		}); //ajax 
	}); //jlogin_click
	
// ** Ajax Join Test
	$('#ajoinf').click(function(){
		$.ajax({
			type:'Get',
			url:'mjoinf',
			success:function(resultPage){
				$('#resultArea1').html(resultPage);
				},
			error:function(){
				$('#resultArea1').html("~~ 오류발생 ~~");
			}	
		}); //ajax
	}); //click
	
	
	$('#ajoin').click(function() {
	
	// 1) Form 의 serialize()
	// => 직렬화 : multipart 타입은 제외 시켜야 함.	
	//	var formData = $('#myForm').serialize();
		
	// 2) 객체화 : 특별한 자료형(fileType: UpLoadFile) 적용안됨.	
	/*	var formData = {
			id:$('#id').val(),
			password:$('#password').val(),
			name:$('#name').val(),
			lev:$('#lev').val(),
			birthd:$('#birthd').val(),
			point:$('#point').val(),
			weight:$('#weight').val(),
			rid:$('#rid').val()
			}
	*/		
	// 3) FormData() 객체 I
	// => append 메서드로...
	// => 모든 자료형의 특성에맞게 적용가능 (fileType: UpLoadFile 적용가능)   		
	/* 	var formData = new FormData();
		formData.append('id',$('#id').val());
		formData.append('password',$('#password').val());
		formData.append('name',$('#name').val());
		formData.append('lev',$('#lev').val());
		formData.append('birthd',$('#birthd').val());
		formData.append('point',$('#point').val());
		formData.append('weight',$('#weight').val());
		formData.append('rid',$('#rid').val());
		
		// => Ajax 의  FormData 는  이미지를 선택하지 않으면 append시 오류 발생
		//    하기 때문에 이를 확인 후 append 하도록 함
		//    이때 append 를 하지 않으면  서버의 vo.uploadfilef 에는  null 값이 전달됨.
		if ($('#uploadfilef')[0].files[0] !=null) {
			formData.append('uploadfilef',$('#uploadfilef')[0].files[0]);
		}
		*/ 
		// ** 관련속성	
		// => enctype: 'multipart/form-data', // 생략 가능
		// => processData:false, // false로 선언 시 formData를 string으로 변환하지 않음
		// => contentType:false, // false로 선언 시 content-type 헤더가 multipart/form-data로 전송되게 함
	
	// 3.1) serialize() + FormData() 객체 I	
	// => 불가능
	
	// 4) all append
	// => 특별한 자료형(fileType: UpLoad) 취급가능.
	// 4.1) 생성시 인자로 form 사용
	//	var formData = new FormData(document.getElementById('myForm')) ;
		//var formData = new FormData($('#myForm')) ; => Error 인식안됨
	// 4.2) 생성시 인자로 form[0] 사용
		var formData = new FormData($('#myForm')[0]);

		alert('input Test => 4.2) FormData() ');
		$.ajax({
			type:'post',
			url: 'mjoin',
			enctype: 'multipart/form-data',
			processData:false,
			contentType:false,
			data:formData,
			success:function(resultPage){
				$('#resultArea1').html(resultPage);
				},
			error:function(){
				$('#resultArea1').html("~~ 오류발생 ~~");
			}
		}); //ajax 
	}); //ajoin click 
	
}); //ready

// ** Ajax 의  input Tag 의  Data 처리방법
// 1) Form 의 serialize()
// => input Tag 의 name 과 id 가 같아야 함.	
// => 직렬화 : multipart 타입은 제외 시켜야 함.	

// var formData = $('#myForm').serialize();


// 2) 객체화	
// => 특정 변수 (객체형) 에 담기
//	     {...}
	
// 3) FormData() 객체 1 : append ...
// => FormData는 Ajax를 통해 이미지등이 업로드가 가능하도록 지원하는 폼 데이터 객체	
// => 자료 특성에 맞게 적용 가능  : fileType (UpLoad) 가능
	
// 4) FormData() 객체 2 : all append ...	

	
/* *****************************
=> 필요없게됨
{
	id:$('#id').val(),
	password:$('#password').val(),
	name:$('#name').val(),
	lev:$('#lev').val(),
	birthd:$('#birthd').val(),
	point:$('#point').val(),
	weight:$('#weight').val(),
	rid:$('#rid').val()
	//uploadfilef:$('#uploadfilef').val()
}

*********************************
*** $.ajax 메서드   ******************
 *  
 * 1. 기본형식
 * $.ajax({옵션속성:값}); 
 * => $.ajax 함수는 XMLHttpRequest 객체를 반환함.
 * 
 * 2. 옵션속성 
 * => https://hsj0511.tistory.com/205 참고
 * 
 * url:문자열 - 요청url 설정

=> type:문자열 - GET/POST설정
   data:객체,문자열 - 요청 매개변수 설정 
   dataType: return Data Type - xml, html, json, jsonp, text, script
   success:함수
 		=> 성공시 호출할 함수 설정
 		=> 매개변수가 응답 결과를 받아줌 
   error:함수 - 실패시 호출할 함수 설정
   async:불리언 - 동기/비동기 설정 (True/False)
   beforeSend:HTTP 요청전에 발생하는 이벤트 핸들러
***************************************
/*
 * * JQ 로 이벤트 핸들러 작성시 주의 사항 
=> jQuery 로 이벤트를 처리하기 위해 지금처럼 ready 이벤트를 사용하는 경우
   본구문이 포함된 axTest03.js 를 부모창에도 추가하고, 
   결과로 불리워지는 loginForm.jsp 에도 포함 하게되면
   마치 callBack 함수처럼 실행 할 때마다 이중으로 불리워지면서
   2의 자승으로 늘어나게 됨. 
=> 해결방법
  1) ~.js 를 각각 분리한다. 
  -> $('#jlogin').click( .....) 부분 axTest04.js 로 독립 
  2) JS의 함수 방식 으로 이벤트 처리
  
** ** 이벤트 전달
=> Tag 들이 겹겹이 포함관계에 있을때 이벤트가 전달되어 여러번 발생가능함. 
=> 이것을 이벤트 Propagation 이라함.
=> 해결 : return false
  -> e.preventDefault() + e.stopPropagation()

** ** JS & JQ 에서 익명함수의 매개변수역할 
=> 이벤트 핸들러 의 첫 매개변수 : 이벤트 객체를 전달 
=> ajax 메서드의 success 속성 : 서버의 response 결과 
=> css 속성값 : 0부터 순차적으로 대입됨 (jq03_attr.html 참고)

 */
