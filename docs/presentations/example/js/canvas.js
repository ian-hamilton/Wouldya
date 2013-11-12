var context;
var canvas;
$(document).ready(function() {

	canvas = document.getElementById("paintCanvas");
	context = canvas.getContext("2d");

	canvas.onmousedown = startDrawing;
	canvas.onmouseup = stopDrawing;
	canvas.onmouseout = stopDrawing;
	canvas.onmousemove = draw;

});

function startDrawing( e) {
	// Start drawing.
	isDrawing = true;
	// Create a new path (with the current stroke color and stroke thickness).
	context.beginPath();
	// Put the pen down where the mouse is positioned.
	context.moveTo( e.pageX - canvas.offsetLeft, e.pageY - canvas.offsetTop);

}

function draw(e) {
	if (isDrawing == true) {
		// Find the new position of the mouse.
		var x = e.pageX - canvas.offsetLeft;
		var y = e.pageY - canvas.offsetTop;
		// Draw a line to the new position.
		context.lineTo( x, y); context.stroke();
	}
}

function stopDrawing() {
	isDrawing = false;
}


