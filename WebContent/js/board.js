/**
 * 
 */

function readServer(){
	

$.ajax({
		url:'/board/List.do',
		type:'get',
		dataType:'json',
		success:function(res){
			code = '<div class="panel-group" id="accordion">';
			$.each(res,function(i){
				
			code += '<div class="panel panel-default">';
			code += '  <div class="panel-heading">';
			code += '   <h4 class="panel-title">';
			code += '    <a data-toggle="collapse" data-parent="#accordion" href="#collapse'+ res[i].seq +'">' +res[i].subject+'</a>';
			code += '   </h4>';
			code += '  </div>';
			code += '  <div id="collapse'+ res[i].seq +'" class="panel-collapse collapse">';
			code += '	 <div class="panel-body">';

			code += '<p style="width:80%; float:left;">';
			code += '작성자 : ' + res[i].writer+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'; 
			code += '메일 : ' + res[i].mail+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'; 
			code += '조회수 : ' + res[i].hit+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'; 
			code += '조회날짜 : ' + res[i].wdate+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'; 
			code +='</p>';
			
			code += '<p style="width:20%; float:right;">';
			code += '<button idx="' +res[i].seq+ '" type="button" name="modify" class="action">수정</button>&nbsp;&nbsp;';
			code += '<button idx="' +res[i].seq+ '" type="button" name="delete" class="action">삭제</button>';
			code += '</p><hr style="clear:both;">';
			code += '<p style="width:100%;">';
			code += res[i].content;
			code += '</p>';
			code += '<p style="width:100%;">';
			code += '<textarea class="area" cols="60"></textarea>';
			code += '<button idx="' +res[i].seq+ '" style="height:45px;vertical-align:top;;" type="button" name="reply" class="action">등록</button>';
			code += '</p>';
			code += '  </div>';
			code += ' </div>';
			code += '</div>';

								});
			code+='</div>';
			
			$('.box').html(code);

		},
		error : function(req) {
			alert('상태 : ' + req.status);
		}
	});

}