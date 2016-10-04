<div id="bg" style="background:url('../images/climb/007.jpg') center no-repeat;">
  <div class="module">
    <ul>
      <li class="tab"><a href="${flowExecutionUrl}&_eventId=mainInfo" class="ti-user"></a></li>
      <li class="tab activeTab"><span class="ti-location-pin"></span></li>
      <li class="tab"><a href="${flowExecutionUrl}&_eventId=keyInfo" class="ti-key"></a></li>      
      <li class="tab"><a href="${flowExecutionUrl}&_eventId=reviewInfo" class="ti-check"></a></li>
    </ul>
    
    <form class="form" method="post">
      <input type="text" placeholder="Country" class="textbox" />
      <input type="text" placeholder="City" class="textbox" />
      <input type="text" placeholder="Language" class="textbox" />
      <div style="text-align:center;">
      	<input type="submit" value="Cancel" class="button cancel_b" name="_eventId_cancel" />
      	<input type="submit" value="Back" class="button back_b" name="_eventId_back" />
      	<input type="submit" value="Next" class="button" name="_eventId_next" />
      	<input type="submit" value="Finish" class="button finish_b" name="_eventId_confirm" disabled="disabled" />
      </div>
    </form>
  </div>
</div>

<h1>${concretePerson}</h1>