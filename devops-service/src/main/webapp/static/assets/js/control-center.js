function rightTriangle(c){
    var ctx = c.getContext("2d");
    ctx.lineWidth=3;
    ctx.beginPath();
    ctx.moveTo(c.width, c.height/2);
    ctx.lineTo(c.width-10 , c.height/2+10);
    ctx.lineTo(c.width-10 , c.height/2-10);
    ctx.fillStyle="#8c8c8c";
    ctx.fill();
}
function downTriangle(c){
    var ctx = c.getContext("2d");
    ctx.lineWidth=3;
    ctx.beginPath();
    ctx.moveTo(c.width/2, c.height);
    ctx.lineTo(c.width/2+10, c.height-10);
    ctx.lineTo(c.width/2-10, c.height-10);
    ctx.fillStyle="#8c8c8c";
    ctx.fill();
}
function horizontalLine(){
    var c = document.getElementsByClassName("horizontal-line");
    for(i=0; i< c.length; i++)
    {
        var ctx = c[i].getContext("2d");
        ctx.lineWidth=3;
        ctx.beginPath();
        ctx.moveTo(0, c[i].height/2);
        ctx.lineTo(c[i].width, c[i].height/2);
        ctx.strokeStyle="#8c8c8c";
        ctx.stroke();
        rightTriangle(c[i]);
    }
}
function verticalLine(){
    var c = document.getElementsByClassName("vertical-line");
    for(var i=0; i<= c.length; i++)
    {
        var ctx = c[i].getContext("2d");
        ctx.lineWidth=3;
        ctx.beginPath();
        ctx.moveTo(c[i].width/2, 0);
        ctx.lineTo(c[i].width/2,c[i].height);
        ctx.strokeStyle="#8c8c8c";
        ctx.stroke();
        downTriangle(c[i]);
    }
}
function clickFunction() {
	window.location.href = window.location.href;
}
window.onload = function() {
    horizontalLine();
    verticalLine();
    document.getElementById("refresh-button").addEventListener("click", clickFunction);
}
window.setInterval(function(){
	if(document.getElementById("refresh-button")){
		clickFunction();
	}
},5000);
