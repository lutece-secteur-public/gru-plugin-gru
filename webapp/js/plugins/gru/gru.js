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

//Change the icon of the button when the notification is collapse or not
$(".collapse-notif .collapse-button").click(function(){
	var liParent = $(this).parent();
	var plusMinusIcon = liParent.find("button > i.fa");
	var divItem = liParent.find("div.timeline-item");
	if( plusMinusIcon.hasClass('fa-plus') ){
		plusMinusIcon.removeClass('fa-plus').addClass('fa-minus');
		divItem.slideToggle();
	} else {
		plusMinusIcon.removeClass('fa-minus').addClass('fa-plus');
		divItem.slideToggle();
	}
});