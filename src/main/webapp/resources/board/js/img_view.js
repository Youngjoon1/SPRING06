function readURL(input) {
		var file = input.files[0] //파일에 대한 정보
		if(file != "") {
			var reader = new FileReader();
			reader.readAsDataURL(file); // 읽고
			reader.onload = function(e) { // 로드한 값을 표현한다
				$('#preview').attr('src',e.target.result);
			}
		}
	}