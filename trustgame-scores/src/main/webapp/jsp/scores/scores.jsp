<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1">
    <title>TrustGame Score Overview</title>

    <link rel="stylesheet" type="text/css" href="/trustgame-scores/css/scores.css" />
    <script src="/trustgame-scores/js/scores.js"></script>

    <style>
    table, th, td {
    	border: 1px solid gray;
    	border-spacing: 0px;
    	border-collapse: collapse;
    	padding: 5px;
    	vertical-align: top;
    }
    
    body {
    	line-height: 1.2;
    }
    </style>

  </head>

  <body onload="initPage()">
    <div class="tg-page">
      <div class="tg-header">
        <span class="tg-freightbooking">TransSonic FreightBooking Game</span>
        <span class="tg-slogan">Score Overview</span>
      </div>
      <div class="tg-header-right">
        <img src="images/nwo.png" />
        <img src="images/tudelft.png" />
        <p><a href="/trustgame-scores">LOGOUT</a></p>
      </div>
      <div class="tg-header-game-user">
        <p>&nbsp;</p>
        <p>User:&nbsp;&nbsp;&nbsp; ${scoreData.getUser().getName()}</p>
      </div>

      <div class="tg-body">
      
        <div class="tg-scores" id="tg-scores">
          ${scoreData.getContentHtml()}
        </div>
        
      </div> <!-- tg-body -->
      
    </div> <!-- tg-page -->
    
    <!-- modal window for the client information within an order -->
    
    ${scoreData.getModalWindowHtml()}

    <form id="clickForm" action="/trustgame-scores/scores" method="POST" style="display:none;">
      <input id="click" type="hidden" name="click" value="tobefilled" />
      <input id="recordNr" type="hidden" name="recordNr" value="0" />
    </form>

  </body>

</html>