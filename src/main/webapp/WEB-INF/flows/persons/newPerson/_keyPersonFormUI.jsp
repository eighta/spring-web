<div id="bg" style="background:url('../images/climb/004.jpg') center no-repeat;">
  <div class="module">
    <ul>
      <li class="tab"><a href="${flowExecutionUrl}&_eventId=mainInfo" class="ti-user"></a></li>
      <li class="tab"><a href="${flowExecutionUrl}&_eventId=locationInfo" class="ti-location-pin"></a></li>
      <li class="tab activeTab"><span class="ti-key"></span></li>
      <li class="tab"><a href="${flowExecutionUrl}&_eventId=reviewInfo" class="ti-check"></a></li>
    </ul>
    
    <form class="form" method="post"> 
      <input type="text" placeholder="Password" class="textbox" />
      <input type="text" placeholder="Confirm Password" class="textbox" />
      <input type="text" placeholder="Security Question: (Zero-Factorial is equal to?)" class="textbox" />
      <div style="text-align:center;">
      	<input type="submit" value="Cancel" class="button cancel_b" name="_eventId_cancel" />
      	<input type="submit" value="Back" class="button back_b" name="_eventId_back" />
      	<input type="submit" value="Next" class="button" name="_eventId_next" />
      	<input type="submit" value="Finish" class="button finish_b" name="_eventId_confirm" disabled="disabled" />
      </div>
    </form>
  </div>
</div>
