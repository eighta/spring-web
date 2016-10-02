<div id="bg">
  <div class="module">
    <ul>
      <li class="tab activeTab"><span class="ti-user"></span></li>
      <li class="tab"><a href="${flowExecutionUrl}&_eventId=locationInfo" class="ti-location-pin"></a></li>
      <li class="tab"><a href="${flowExecutionUrl}&_eventId=keyInfo" class="ti-key"></a></li>      
      <li class="tab"><a href="${flowExecutionUrl}&_eventId=reviewInfo" class="ti-check"></a></li>
    </ul>
    
    <form class="form" id="newPersonForm" method="post">
      <input type="text" placeholder="First Name" class="textbox" />
      <input type="text" placeholder="Last Name" class="textbox" />
      <input type="text" placeholder="Email Address" class="textbox" />
      <div>
      	<input type="submit" value="Back" class="button back_b" name="_eventId_back" disabled="disabled" />
      	<input type="submit" value="Next" class="button" name="_eventId_next" />
      </div>
    </form>
  </div>
</div>
