$(function(){
	$("a[return-url]").click(function(e) {
		e.preventDefault();
		var action = $(this).attr('href');
		var returnUrl = $(this).attr('return-url');

		var form = $('<form>', 
                { 'action':action,
				  'method':'post'}
           );
		   
		form.append( 
		   $('<input>', 
		        { 'type':'hidden', 
		          'value':returnUrl, 
		          'name':'returnUrl' }
		    )
		);
		
		form.appendTo('body');
		form.submit().remove();

	});
});

function redirectOnClick( element ){
	if ( $(element).attr("data-url") != undefined ){
	    var form = $('<form>', 
	    	{ 'action':$(element).attr("data-url"),
	    	'method':'post'}
       );
       form.appendTo('body');
       form.submit().remove();
    }
}

$( function(){
	  // Set link on whole tr
	  $("tr").on( 'click', function(e){
		  redirectOnClick(this);
	  });
	});

// Change the icon of the button when the notficiation si collapse or not
$(".collapse-button").click(function(){
	var current_id = $(this).attr('id');
	var child_icon = document.getElementById(current_id).firstElementChild;
	if( child_icon.getAttribute('class').indexOf('fa-plus') !== -1 ){
		child_icon.setAttribute('class', 'fa fa-minus');
	} else {
		child_icon.setAttribute('class', 'fa fa-plus');
	}
});

// Click on user icon launch a click on the collapse button
$(".notification-icon").click(function (){
	var current_id = $(this).attr('id');
	var button_icon = document.getElementById('button-' + current_id);
	button_icon.click();
});