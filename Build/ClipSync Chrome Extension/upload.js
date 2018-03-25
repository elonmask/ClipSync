var db = firebase.database().ref();
var childData;
var data = "";
var storage;
var opt = {
	type: "basic",
	title: "ClipBoard had been synced",
	message: "ClipSync just resived new clipboard data",
	priority: 1,
	iconUrl: "http://www.google.com/favicon.ico"
	}
	
	getData();

  	var ID = 5;

document.addEventListener('copy', function(event) {
			event.clipboardData.setData('text/plain', childData);
			event.preventDefault();
			chrome.notifications.create(ID, opt, function() {});
		});

db.child(data).on('value', function(snapshot) {
	 
	childData = snapshot.val();
	document.execCommand('copy');

});

function makeid() {
	var text = "";
	var possible = "abcdefghijklmnopqrstuvwxyz0123456789";
  
	for (var i = 0; i < 5; i++)
	  text += possible.charAt(Math.floor(Math.random() * possible.length));
  
	return text;
  }

  function setData(dat) {
	chrome.storage.sync.set({'key': dat}, function() {});
  }

  function getData() {
	chrome.storage.sync.get('key', function (obj) {
		var string = obj.key;
		if( string == undefined || string == null) {

		   data = makeid();
		   setData(data);
		   alert('Your code is: ' + data);
		} else {

			data = string;
			alert('Your saved code is: ' + data);
		}
	});
  }