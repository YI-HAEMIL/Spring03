/**
**** AjaxTest01
=> axMList , id별로 board조회, delete기능, ImageDownload
 
=> axBList
 
*/
// *** Ajax MemberList
$(function(){
	$('#amlist').click(function(){
		$.ajax({
			type:'Get',
			url:'amlist',
			success:function(resultPage) {
			$('#resultArea1').html(resultPage);
			},
			error: function() {
				alert('~~ 서버 오류 !!! 잠시후 다시 하세요 ~~');
			}
		}); //ajax
	}); //amlist_click 
	
// *** 반복문에 이벤트 적용하기 
// test 2) JQ id, class
	$('.ccc').click(function(){
		var id = $(this).html();
		console.log('id1 html() => '+id);
		id = $(this).text();
		console.log('id2 text() => '+id);
		id = $(this).attr('id');
		console.log('id3 attr => '+id);
		$.ajax({
			type:'Get',
			url:'idblist',
			data: {
				id:id
			},
			success:function(resultPage) {
			$('#resultArea2').html(resultPage);
			},
			error: function() {
				alert('~~ 서버 오류 !!! 잠시후 다시 하세요 ~~');
			}
		}); //ajax
	}); //amlist_click 
	
// *** Delete ( JQ, id, class)
	$('.ddd').click(function(){
		var id = $(this).attr('id');  // banana
		$.ajax({
			type:'Get',
			url:'jsdelete',
			data:{
				id:id
			},
			success:function(resultData){
				if (resultData.success=='T') {
					alert("~~ 삭제 성공 ~~");
					// $(this).html('Deleted').removeClass('ddd');
					// ajax 내에서 this 는 이미 변경 되었기 때문에 적용안됨 
					$('#'+id).html('Deleted'); // $('#banana')
					$('#'+id).off();  // 이벤트 제거
					console.log('class test1 => '+ $('#'+id).attr('class'));
				} else {
					alert("~~ 삭제 실패 ~~");
				}
			},
			error:function(){
				alert('서버 오류 발생 !! 잠시후 다시 하세요 ~~');
			}
		}); // ajax
	}) // ddd
	
	
	
}); //ready

// *** 반복문에 이벤트 적용하기
// test 1) JS function 
function aidbList(id) {
	$.ajax({
			type:'Get',
			url:'idblist',
			data: {
				id:id
			},
			success:function(resultPage) {
			$('#resultArea2').html(resultPage);
			},
			error: function() {
				alert('~~ 서버 오류 !!! 잠시후 다시 하세요 ~~');
			}
		}); //ajax
} //aidbList 
