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
      <div style="text-align:center;">
      	<input type="submit" value="Cancel" class="button cancel_b" name="_eventId_cancel" />
      	<input type="submit" value="Back" class="button back_b" name="_eventId_back" disabled="disabled" />
      	<input type="submit" value="Next" class="button" name="_eventId_next" />
      	<input type="submit" value="Finish" class="button finish_b" name="_eventId_confirm" disabled="disabled" />
      </div>
    </form>
  </div>
</div>

<h1>${concretePerson}</h1>

<a href="${flowExecutionUrl}&_eventId=logout" class="ti-angle-double-left"></a></li>
<a href="${flowExecutionUrl}&_eventId=server" class="ti-back-left"></a></li>
<a href="${flowExecutionUrl}&_eventId=servlet" class="ti-arrow-circle-up"></a></li>
<a href="${flowExecutionUrl}&_eventId=context" class="ti-layout-slider-alt"></a></li>

<br/><br/><br/>