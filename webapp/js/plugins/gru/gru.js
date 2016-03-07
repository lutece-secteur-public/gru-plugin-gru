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