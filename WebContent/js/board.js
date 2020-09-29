/**
 * 
 */
var currentpage = 1;

var readPageServer = function(cpage){
	
	$.ajax({
		url : '/board/List.do',
		type: 'post',
		data : {'page':cpage},
		dataType : 'json',
		success : function(res){
			code = '<div class="panel-group" id="accordion">';
			$.each(res.datas,function(i){
				
			code += '<div class="panel panel-default">';
			code += '  <div class="panel-heading">';
			code += '   <h4 class="panel-title">';
			code += '    <a data-toggle="collapse" data-parent="#accordion" href="#collapse'+ res.datas[i].seq +'">' +res.datas[i].subject+'</a>';
			code += '   </h4>';
			code += '  </div>';
			code += '  <div id="collapse'+ res.datas[i].seq +'" class="panel-collapse collapse">';
			code += '	 <div class="panel-body">';

			code += '<p style="width:80%; float:left;">';
			code += '작성자 : ' + res.datas[i].writer+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'; 
			code += '메일 : ' + res.datas[i].mail+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'; 
			code += '조회수 : ' + res.datas[i].hit+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'; 
			code += '조회날짜 : ' + res.datas[i].wdate+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'; 
			code +='</p>';
			
			code += '<p style="width:20%; float:right;">';
			code += '<button idx="' +res.datas[i].seq+ '" type="button" name="modify" class="action">수정</button>&nbsp;&nbsp;';
			code += '<button idx="' +res.datas[i].seq+ '" type="button" name="delete" class="action">삭제</button>';
			code += '</p><hr style="clear:both;">';
			code += '<p style="width:100%;">';
			code += res.datas[i].content;
			code += '</p>';
			code += '<p style="width:100%;">';
			code += '<textarea class="area" cols="60"></textarea>';
			code += '<button idx="' +res.datas[i].seq+ '" style="height:45px;vertical-align:top;;" type="button" name="reply" class="action">등록</button>';
			code += '</p>';
			code += '  </div>';
			code += ' </div>';
			code += '</div>';

								});
			code+='</div>';
			
			$('.box').html(code);
			
			$('#pagelist').empty();
			
			pager = "";
			//이전버튼 출력하기
			if(res.startp > 1){
				pager+= '<ul class="pager">';
				pager+= '<li><a class="prev" href="#">Previous</a></li>';
				pager+= '</ul>';
				$('#pagelist').append(pager);
			}
			
			//페이지버튼 출력하기
			pager = '<ul class="pagination pager">';
			for(var i=res.startp; i <= res.endp; i++){
				if(currentpage == i){
					pager += '<li class="active"><a href="#" >' +i+'</a></li>';
				}else{
					pager += '<li><a href="#">' +i+'</a></li>';
				}
			}
			pager += '</ul>';
			$('#pagelist').append(pager);
			
			// 다음버튼 출력하기
			pager = "";
			if(res.endp < res.totalp){
				pager+= '<ul class="pager">';
				pager +=  '<li><a class="next" href="#">Next</a></li>';
				pager+= '</ul>';
				$('#pagelist').append(pager);
			}
			
		
		},
		error : function(req){
			alert("상태  : " + req.status);
		}
	})
};







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