function newLoad(){
	
	document.getElementById("listOfNotes").innerHTML = "ЗАМЕТКА1";
	document.getElementById("test").value = "ТЕКСТ1";
	document.getElementById("shownNote").innerHTML = "ЗАМЕТКА1";
	localStorage.setItem('note', "1");
	localStorage.setItem('newNote', "1");
	var oldNotesList;
	const request = new XMLHttpRequest();
	const url = "http://localhost:8081/C4C/NotesList";
	const params = "";
	request.open("POST", url, true);
	request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	request.addEventListener("readystatechange", () => {
    if(request.readyState === 4 && request.status === 200) { 
	if(request.responseText == "НЕТ ЗАПИСЕЙ"){
		alert("НЕТ ЗАПИСЕЙ");
	}else {
		oldNotesList = request.responseText.split("&");
		var list = "";
		for(var i=0;i<oldNotesList.length;i++){
			list = list + "<div onClick = 'setNote("+ oldNotesList[i] +")'> >> ЗАМЕТКА" + oldNotesList[i] +"</div><br>";
			var newNote = i+1;
			localStorage.setItem('newNote', newNote);			
		}
	document.getElementById("listOfNotes").innerHTML = list;
	getText(1);
	}
    }
	});
	request.send(params);
	
}

function setNote(id){
	
	getText(id);
	localStorage.setItem('note', id);
	document.getElementById("shownNote").innerHTML = "ЗАМЕТКА"+id;
	
}

function getText(id){

	const request = new XMLHttpRequest();
	const url = "http://localhost:8081/C4C/GetText";
	const params = "id=" + id;
	request.open("POST", url, true);
	request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	request.addEventListener("readystatechange", () => {
    if(request.readyState === 4 && request.status === 200) { 
	if(request.responseText == "НЕТ ТЕКСТА"){
	}else {
		document.getElementById("test").value = request.responseText;
		document.getElementById("shownNote").innerHTML = "ЗАМЕТКА"+id;
		}
    }
});
request.send(params);
	
}

function saveNote(){
		
	const request = new XMLHttpRequest();
	const url = "http://localhost:8081/C4C/SaveNote";
	const params = "id=" + localStorage.getItem('note')+ "&text=" + document.getElementById("test").value;
	request.open("POST", url, true);
	request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	request.addEventListener("readystatechange", () => {
    if(request.readyState === 4 && request.status === 200) { 
		alert("ДАННЫЕ СОХРАНЕНЫ");
    }
	});
	request.send(params);

	
}


function newNote(){
	
	var newNote = localStorage.getItem('newNote')*1 + 1;
	const request = new XMLHttpRequest();
	const url = "http://localhost:8081/C4C/NewNote";
	const params = "id=" + newNote + "&text=ТЕКСТ" + newNote;
	request.open("POST", url, true);
	request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	request.addEventListener("readystatechange", () => {
    if(request.readyState === 4 && request.status === 200) { 
		var list = document.getElementById("listOfNotes").innerHTML;
		document.getElementById("listOfNotes").innerHTML = list + "<div onClick = 'setNote("+ newNote +")'> >> ЗАМЕТКА" + newNote +"</div><br>";
		localStorage.setItem('note', newNote);
		localStorage.setItem('newNote', newNote);
		document.getElementById("shownNote").innerHTML = "ЗАМЕТКА"+newNote;
		document.getElementById("test").value = "ТЕКСТ"+newNote;
    }
	});
	request.send(params);
	
}