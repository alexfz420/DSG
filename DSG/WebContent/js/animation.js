$(document).ready(function() {
  $(function() {
  $( ".toggle" ).click(function() {
      $( this ).toggleClass( "disabled");
      if( $( this ).hasClass('disabled') ){
          $( this ).html('Enable');
      } else{
          $( this ).html('Disable');
      }
    return false;
  });
  });

  $(function() {
  $( "#toggleone" ).click(function() {
      if( $("#navone").css("display") == "block") {
          $("#navone").css("display", "none");
          $( "#toggleone" ).removeClass( "open");
          $( "#toggleone" ).addClass( "close" );
      }else {
          $("#navone").css("display", "block");
          $( "#toggleone" ).removeClass( "close");
          $( "#toggleone" ).addClass( "open" );
      }
    return false;
  });

  $( "#toggletwo" ).click(function() {
      if( $("#navtwo").css("display") == "block") {
          $("#navtwo").css("display", "none");
          $( "#toggletwo" ).removeClass( "open");
          $( "#toggletwo" ).addClass( "close" );
      }else {
          $("#navtwo").css("display", "block");
          $( "#toggletwo" ).removeClass( "close");
          $( "#toggletwo" ).addClass( "open" );
      }
    return false;
  });

  $( "#togglethree" ).click(function() {
      if( $("#navthree").css("display") == "block") {
          $("#navthree").css("display", "none");
          $( "#togglethree" ).removeClass( "open");
          $( "#togglethree" ).addClass( "close" );
      }else {
          $("#navthree").css("display", "block");
          $( "#togglethree" ).removeClass( "close");
          $( "#togglethree" ).addClass( "open" );
      }
    return false;
  });
  });


  $(function(){
           
      // add multiple select / deselect functionality
      $("#selectall").click(function () {
    	  alert("selectall");
            $('.case').attr('checked', this.checked);
      });
   
      // if all checkbox are selected, check the selectall checkbox
      // and viceversa
      $(".case").click(function(){
    	  alert("deselectall");
          if($(".case").length == $(".case:checked").length) {
              $("#selectall").attr("checked", "checked");
          } else {
              $("#selectall").removeAttr("checked");
          }
       
      });
  });
  
  
  $(function(){
      
      // add multiple select / deselect functionality
      $("#selectalltwo").click(function () {
            $('.case').attr('checked', this.checked);
      });
   
      // if all checkbox are selected, check the selectall checkbox
      // and viceversa
      $(".case").click(function(){
   
          if($(".case").length == $(".case:checked").length) {
              $("#selectalltwo").attr("checked", "checked");
          } else {
              $("#selectalltwo").removeAttr("checked");
          }
   
      });
});

});