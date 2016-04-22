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