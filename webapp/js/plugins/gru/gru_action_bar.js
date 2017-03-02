$(document).ready(function() {
	var marginSup = 10;
	var emptyWidth = 0;
	
	// Get the width of the menu bar
	var sidebarMenuWidth;
	$('.sidebar-menu').each(function(){
		sidebarMenuWidth = $(this).width();
	});
	
	$('.sidebar-toggle').on("click", function(){
		if( !($("body").hasClass("sidebar-collapse"))) {
			$('li.treeview').each(function() {
				$(this).removeClass("active");
				$(this).children('a').each(function(){
					$(this).css('width', emptyWidth + "px");
				});
				$(this).children('ul').each(function(){
					$(this).removeClass("menu-open");
					$(this).hide();
				});
			});
		}
	});
	
	// Determine the max width of each element in menu
	$('li.treeview').each(function() {
		var maxWidth = 0;
		$(this).on("mouseover", function(){
			if( $("body").hasClass("sidebar-collapse") ){
				// Get the max width of the current treeview
				$(this).children('a').each(function(){
					$(this).children('span').each(function(){
						maxWidth = updateMaxWidth(maxWidth, this);
					});
				});
				$(this).children('ul').each(function(){
					$(this).children('li').each(function(){
						maxWidth = updateMaxWidth(maxWidth, this);
					});
				});
			
				// Set the width of elements of the current treeview
				$(this).children('a').each(function(){
					$(this).css('width', maxWidth + sidebarMenuWidth + marginSup + "px");
				});
				$(this).children('ul').each(function(){
					$(this).css('width', maxWidth + marginSup + "px");
				});
			}
			else{
				var globalMaxWidth = $('.main-sidebar').width();
				$(this).children('a').each(function(){
					$(this).css('width', globalMaxWidth + "px");
				});
				$(this).children('ul').each(function(){
					$(this).css('width', globalMaxWidth + "px");
				});
			}
		});
		
		// Update the width of all hover elements for prevent scrolling error
		$(this).on("mouseout", function(){
			if( $("body").hasClass("sidebar-collapse") ){
				$(this).children('a').each(function(){
					$(this).css('width', emptyWidth + "px");
				});
				$(this).children('ul').each(function(){
					$(this).css('width', emptyWidth + "px");
				});
			}
			else{
				var globalMaxWidth = $('.main-sidebar').width();
				$(this).children('a').each(function(){
					$(this).css('width', globalMaxWidth + "px");
				});
				$(this).children('ul').each(function(){
					$(this).css('width', globalMaxWidth + "px");
				});
			}
		});
		
	});
});

/**
 * Update the max width in function of the current element 
 * 
 * @param maxWidth
 * @param element
 * @returns the new value of the max width
 */
function updateMaxWidth( maxWidth, element ){
	var fullWidth = computeElementFullwidth(element);
	if (fullWidth > maxWidth) {
		maxWidth = fullWidth;
	}
	return maxWidth;
}

/**
 * Compute the width of an element (width of element + vertical padding + vertical margin)
 * 
 * @param element
 * @returns the computed width of current element
 */
function computeElementFullwidth( element ){
	var currentSpanWidth = element.offsetWidth;
	var style = window.getComputedStyle(element);
	var marginLeft = parseInt(style.marginLeft);
	var marginRight = parseInt(style.marginRight);
	return (currentSpanWidth + marginLeft + marginRight);
}