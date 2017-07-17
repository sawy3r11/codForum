$('.hamburger').click(function() {
   $('.navbar').toggleClass('nav-opened'); 
});

$('#btn-signin').click(function() {
    $('.login-panel').fadeToggle(250); 
    $(this).toggleClass('list-opened');
});

$('#btn-search').click(function() {
    $('.search-input').fadeToggle(250);
    $(this).toggleClass('list-opened');
});

$(document).mouseup(function(e) 
{
    var login_panel = $(".login-panel");
    var btn_signin = $('#btn-signin')

    // if the target of the click isn't the container nor a descendant of the container
    if (!login_panel.is(e.target) && login_panel.has(e.target).length === 0 && !btn_signin.is(e.target) && btn_signin.has(e.target).length === 0) 
    {
        login_panel.fadeOut(250);
        btn_signin.removeClass('list-opened');
    }
    
    var btn_search = $('#btn-search');
    var search_input = $('.search-input');
    
    if(!search_input.is(e.target) && search_input.has(e.target).length === 0 && !btn_search.is(e.target) && btn_search.has(e.target).length === 0) {
        search_input.fadeOut(250);
        btn_search.removeClass('list-opened');
    }
    
    var main_nav = $('.main-nav');
    var hamburger = $('.box-hamburger');
    
    if(!main_nav.is(e.target) && main_nav.has(e.target).length === 0 && !hamburger.is(e.target) && hamburger.has(e.target).length === 0) {
        $('#navbar').removeClass('nav-opened');
    }
});

//$(document).ready(function() {
//var stickyNavTop = $('.navbar').offset().top;
// 
//var stickyNav = function(){
//var scrollTop = $(window).scrollTop();
//      
//if (scrollTop > stickyNavTop) { 
//    $('.navbar').addClass('stickyTop');
//} else {
//    $('.navbar').removeClass('stickyTop'); 
//}
//};
// 
//stickyNav();
// 
//$(window).scroll(function() {
//  stickyNav();
//});
//});

