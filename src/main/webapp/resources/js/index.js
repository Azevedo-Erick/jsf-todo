const check = document.getElementById("switch");

const toggleDisplay= (className, display)=>{
	const data = document.getElementsByClassName(className)
		for(let i = 0; i<data.length;i++){
			data[i].style.display = display
		}
}

const toggleTasksDisplay = ()=>{
	if(!check.checked){
		toggleDisplay("completa", "none");
		toggleDisplay("incompleta", "flex");
	}else{
		toggleDisplay("completa", "flex");
		toggleDisplay("incompleta", "none");
	}
	
}
window.addEventListener('load', toggleTasksDisplay)

check.addEventListener("click" , toggleTasksDisplay)